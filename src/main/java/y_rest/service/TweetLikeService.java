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

    public ResponseEntity<?> postLike(String tweet_id, String account_id) {
        // validate tweet exists
        var tweetResponse = tweetService.getTweetById(tweet_id);
        if (tweetResponse.getStatusCode().isError()) {
            return tweetResponse;
        }
        var tweet = (Tweet) tweetResponse.getBody();

        // validate user exists
        var accountResponse = accountService.getUserById(account_id);
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
}
