package y_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import y_rest.models.dto.account.AccountDto;
import y_rest.models.dto.account.AccountFormData;
import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.models.dto.tweet.TweetPreviewDto;
import y_rest.models.entity.Account;
import y_rest.models.repository.AccountRepository;

import java.util.List;
import java.util.UUID;

// we're playing fast and loose with var for funsies

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public ResponseEntity<?> getUserById(String id) {
        var account = repo.findById(UUID.fromString(id));  // never tried var before but lets give it a go
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user with id %s does not exist", id));
        }

        // we found account
        return ResponseEntity.ok(account.get());
    }

    public ResponseEntity<?> getUserDtoById(String id) {
        var account = repo.findById(UUID.fromString(id));  // never tried var before but lets give it a go
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user with id %s does not exist", id));
        }

        // we found account
        return ResponseEntity.ok(AccountDto.fromAccount(account.get()));
    }

    public ResponseEntity<?> getUserByHandle(String handle) {
        var account = repo.findByHandle(handle);
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user %s does not exist", handle));
        }

        // we found account
        return ResponseEntity.ok(AccountDto.fromAccount(account.get()));
    }

    public ResponseEntity<?> registerUser(AccountFormData formData) {
        if (repo.existsByHandle(formData.handle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(String.format("cannot create user, handle %s is taken", formData.handle()));
        } else if (repo.existsByEmail(formData.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(String.format("cannot create user, email %s is in use", formData.email()));
        }

        // all good to create account
        var newAccount = Account.fromFormData(formData);
        repo.save(newAccount);
        return ResponseEntity.ok(newAccount);
    }

    // currHandle is currently logged-in user's handle
    // how will changes to user cascade? if im mentioned in a tweet, but I change my handle, does the mention work?
    public ResponseEntity<?> patchUser(String currHandle, String targHandle, AccountFormData formData) {
        var account = repo.findByHandle(currHandle);

        // could remove this and still be logically sound, but I want to have better error messages
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user %s does not exist", targHandle));
        }

        // is user attempting to edit their own account
        if (!currHandle.equals(targHandle)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to view this content");
        }

        var savedAccount = account.get().updateFromFormData(formData);
        repo.save(savedAccount);
        return ResponseEntity.ok(savedAccount);
    }

    // no response entity, not possible to get an error - need to add input validation for injections
    public List<AccountPreviewDto> searchForUser(String query) {
        return repo.findByDisplayNameOrHandleLike(query)
                .stream()
                .map(AccountPreviewDto::fromAccount)
                .toList();
    }

    public ResponseEntity<?> getUserTweets(String handle) {
        if (!repo.existsByHandle(handle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user %s does not exist", handle));
        }

        return ResponseEntity.ok(repo.findTweetByAccountHandle(handle)
                .stream()
                .map(TweetPreviewDto::create)
                .toList());
    }

    public ResponseEntity<?> getUserLikes(String handle) {
        if (!repo.existsByHandle(handle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user %s does not exist", handle));
        }

        return ResponseEntity.ok(repo.findLikedTweetsByAccountHandle(handle)
                .stream()
                .map(TweetPreviewDto::create)
                .toList());
    }

    public ResponseEntity<?> getUserReplies(String handle) {
        if (!repo.existsByHandle(handle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user %s does not exist", handle));
        }

        return ResponseEntity.ok(repo.findRepliesByAccountHandle(handle)
                .stream()
                .map(TweetPreviewDto::create)
                .toList());
    }

    public ResponseEntity<?> postFollow(String shepherdHandle, String sheepId) {
        var shepherd_opt = repo.findByHandle(shepherdHandle);

        // if shepherd account didn't exist
        if (shepherd_opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user %s does not exist", shepherdHandle));
        }

        var sheep_opt = repo.findById(UUID.fromString(sheepId));

        // if sheep account didn't exist
        if (sheep_opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("user with id %s does not exist", sheepId));
        }

        // both accounts exist
        var shepherd = shepherd_opt.get();
        var sheep = sheep_opt.get();

        // check if the follow relationship already exists
        if (shepherd.getSheep().contains(sheep)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(String.format("%s is already following %s", sheep.getHandle(), shepherdHandle));
        }

        shepherd.getSheep().add(sheep);
        sheep.getShepherds().add(shepherd);

        // Save the updated accounts
        var savedShepherd = repo.save(shepherd);
        repo.save(sheep);

        return ResponseEntity.ok(AccountDto.fromAccount(savedShepherd));
    }


    // del acc
}
