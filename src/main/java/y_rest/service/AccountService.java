package y_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import y_rest.models.dto.account.AccountDto;
import y_rest.models.dto.account.AccountFormData;
import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.models.dto.account.LoginResponse;
import y_rest.models.dto.tweet.TweetPreviewDto;
import y_rest.models.entity.Account;
import y_rest.models.repository.AccountRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

    public ResponseEntity<?> getUserById(String id) {
        var account = repo.findById(UUID.fromString(id));  // never tried var before but lets give it a go
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user with id %s does not exist", id));
        }

        return ResponseEntity.ok(account.get());
    }

    public ResponseEntity<?> getUserDtoById(String id) {
        var account = repo.findById(UUID.fromString(id));
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user with id %s does not exist", id));
        }

        return ResponseEntity.ok(AccountDto.fromAccount(account.get()));
    }

    public ResponseEntity<?> getUserByHandle(String handle) {
        var account = repo.findByHandle(handle);
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", handle));
        }

        // we found account
        return ResponseEntity.ok(account.get());
    }

    public ResponseEntity<?> getUserDtoByHandle(String handle) {
        var account = repo.findByHandle(handle);
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", handle));
        }

        // we found account
        return ResponseEntity.ok(AccountDto.fromAccount(account.get()));
    }

    public ResponseEntity<?> registerUser(AccountFormData formData) {
        if (repo.existsByHandle(formData.handle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("cannot create user, handle %s is taken", formData.handle()));
        } else if (repo.existsByEmail(formData.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("cannot create user, email %s is in use", formData.email()));
        }

        // all good to create account
        try {
            Account newAccount = new Account(formData);
            repo.save(newAccount);
            return login(formData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }

    public ResponseEntity<?> postPfp() {
        return ResponseEntity.ok('a');
    }

    public ResponseEntity<?> patchUser(String handle, AccountFormData formData) {
        var account = repo.findByHandle(handle);

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", handle));
        }

        if (formData.email() != null && repo.existsByEmail(formData.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("email %s is in use", formData.email()));
        }

        if (formData.handle() != null && repo.existsByHandle(formData.handle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("handle %s is in use", formData.handle()));
        }

        var savedAccount = account.get().updateFromFormData(formData);
        repo.save(savedAccount);
        return ResponseEntity.ok(savedAccount);
    }

    // no response entity, not possible to get an error - need to add input validation for injections
    public List<AccountPreviewDto> searchForUser(String query) {
        return repo.findByDisplayNameOrHandleLike(query).stream().map(AccountPreviewDto::fromAccount).toList();
    }

    public ResponseEntity<?> getUserTweets(String handle) {
        if (!repo.existsByHandle(handle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", handle));
        }

        return ResponseEntity.ok(repo.findTweetByAccountHandle(handle).stream().map(TweetPreviewDto::create).toList());
    }

    public ResponseEntity<?> getUserLikes(String handle) {
        if (!repo.existsByHandle(handle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", handle));
        }

        return ResponseEntity.ok(repo.findLikedTweetsByAccountHandle(handle).stream().map(TweetPreviewDto::create).toList());
    }

    public ResponseEntity<?> getUserReplies(String handle) {
        if (!repo.existsByHandle(handle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", handle));
        }

        return ResponseEntity.ok(repo.findRepliesByAccountHandle(handle).stream().map(TweetPreviewDto::create).toList());
    }

    public ResponseEntity<?> postFollow(String shepherdHandle, String sheepHandle) {
        var shepherd_opt = repo.findByHandle(shepherdHandle);

        // if shepherd account didn't exist
        if (shepherd_opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", shepherdHandle));
        }

        var sheep_opt = repo.findByHandle(sheepHandle);

        // if sheep account didn't exist
        if (sheep_opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", sheepHandle));
        }

        // both accounts exist
        var shepherd = shepherd_opt.get();
        var sheep = sheep_opt.get();

        // check if the follow relationship already exists
        if (shepherd.getSheep().contains(sheep)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("%s is already following %s", sheepHandle, shepherdHandle));
        }

        shepherd.getSheep().add(sheep);
        sheep.getShepherds().add(shepherd);

        // Save the updated accounts
        var savedShepherd = repo.save(shepherd);
        repo.save(sheep);

        return ResponseEntity.ok(AccountDto.fromAccount(savedShepherd));
    }

    // ADD AUTHENTICATION
    public ResponseEntity<?> deleteUser(String handle) {
        var account = repo.findByHandle(handle);
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("can not delete, user %s does not exist", handle));
        }

        repo.delete(account.get());
        return ResponseEntity.ok(String.format("user %s successfully deleted", handle));
    }

    public ResponseEntity<?> deleteFollow(String shepherdHandle, String sheepHandle) {
        var shepherdOpt = repo.findByHandle(shepherdHandle);

        // if shepherd account didn't exist
        if (shepherdOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", shepherdHandle));
        }

        var sheepOpt = repo.findByHandle(sheepHandle);

        // if sheep account didn't exist
        if (sheepOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", sheepHandle));
        }

        // both accounts exist
        var shepherd = shepherdOpt.get();
        var sheep = sheepOpt.get();

        // check if the follow relationship does not exist
        if (!shepherd.getSheep().contains(sheep)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("%s is not following %s", sheepHandle, shepherdHandle));
        }

        shepherd.getSheep().remove(sheep);
        sheep.getShepherds().remove(shepherd);

        // Save the updated accounts
        var savedShepherd = repo.save(shepherd);
        repo.save(sheep);

        return ResponseEntity.ok(AccountDto.fromAccount(savedShepherd));
    }

    // this will destroy everything
    public void nuke() {
        repo.deleteAll();
    }


    /*
     * How we are handling login is through:
     *       ensuring form data and password are correct
     *       if so, generate a token, store in account entity.
     *       then, send that token to the frontend
     *       use that token when sending requests to determine
     *       authorization level when viewing content.
     *
     */
    public ResponseEntity<?> login(AccountFormData formData) {
        var accOpt = repo.findByHandle(formData.handle());

        if (accOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("user %s does not exist", formData.handle()));
        }

        var account = accOpt.get();

        if (!account.authenticate(formData.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password was incorrect");
        }

        // gen token and store in db - UUID not the best but more a proof of concept
        var authtoken = UUID.randomUUID();
        account.setAuthtoken(authtoken);
        repo.save(account);

        var response = new LoginResponse(account.getId(), authtoken, account.getHandle());

        // return token
        return ResponseEntity.ok(response);

    }
    // del acc
}
