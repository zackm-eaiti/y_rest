package y_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import y_rest.models.entity.Account;
import y_rest.models.entity.Tweet;
import y_rest.models.entity.TweetLike;
import y_rest.models.repository.TweetLikeRepository;

import java.util.UUID;

@Service
public class TweetLikeService {

    @Autowired
    private TweetLikeRepository repo;

    @Autowired AccountService accountService;

    @Autowired TweetService tweetService;

    public ResponseEntity<?> postLike(String tweetId, String accountHandle) {
        // validate tweet exists
        var tweetResponse = tweetService.getTweetById(tweetId);
        if (tweetResponse.getStatusCode().isError()) {
            return tweetResponse;
        }
        var tweet = (Tweet) tweetResponse.getBody();

        // validate user exists
        var accountResponse = accountService.getUserByHandle(accountHandle);
        if (accountResponse.getStatusCode().isError()) {
            return accountResponse;
        }
        var account = (Account) accountResponse.getBody();

        // check for duplicate
        if (repo.existsByAccountAndTweet(account, tweet)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("user / tweet pairing already exists");
        }

        TweetLike tl = new TweetLike(UUID.randomUUID(), tweet, account);
        repo.save(tl);
        return ResponseEntity.ok("Liking was successful");
    }

    public ResponseEntity<?> deleteLike(String tweetId, String accountHandle) {
        // validate tweet exists
        var tweetResponse = tweetService.getTweetById(tweetId);
        if (tweetResponse.getStatusCode().isError()) {
            return tweetResponse;
        }
        var tweet = (Tweet) tweetResponse.getBody();

        // validate user exists
        var accountResponse = accountService.getUserByHandle(accountHandle);
        if (accountResponse.getStatusCode().isError()) {
            return accountResponse;
        }
        var account = (Account) accountResponse.getBody();

        // ensure tweetlike exists. is this really necessary? idk - just adding for detailed error messages
        var tl = repo.findByAccountAndTweet(account, tweet);
        if (tl.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("account %s has not liked tweet %s", accountHandle, tweetId));
        }

        repo.delete(tl.get());
        return ResponseEntity.ok("like was removed");
    }
}
