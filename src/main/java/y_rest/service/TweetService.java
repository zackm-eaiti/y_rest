package y_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import y_rest.models.dto.tweet.TweetDto;
import y_rest.models.dto.tweet.TweetFormData;
import y_rest.models.entity.Account;
import y_rest.models.entity.Media;
import y_rest.models.entity.Tweet;
import y_rest.models.repository.TweetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TweetService {

    @Autowired
    private TweetRepository repo;

    @Autowired
    private AccountService accountService;

    public ResponseEntity<?> getTweetById(String id_str) {
        var tweet = repo.findById(UUID.fromString((id_str)));

        if (tweet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("tweet with id %s does not exist", id_str));
        }

        return ResponseEntity.ok(tweet.get());
    }

    public ResponseEntity<?> getTweetDtoById(String id_str) {
        UUID id = UUID.fromString(id_str);
        var tweet = repo.findById(id);

        if (tweet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("tweet with id %s does not exist", id_str));
        }

        var replies = repo.findRepliesByParentId(id);

        return ResponseEntity.ok(TweetDto.fromTweetAndReplies(tweet.get(), replies));
    }

    public ResponseEntity<?> postTweet(TweetFormData formData) {
        UUID id = UUID.randomUUID();

        // account validation
        UUID accountId = formData.accountId();
        var accountResponse = accountService.getUserById(accountId.toString());
        if (accountResponse.getStatusCode().isError()) {
            return accountResponse;
        }
        Account account = (Account) accountResponse.getBody();

        // parent validation
        var parentValidation = validateTweet("parent", formData.parentTweetId());
        if (parentValidation.getStatusCode().isError()) {
            return parentValidation;
        }
        Tweet parentTweet = (Tweet) parentValidation.getBody();

        // quote validation
        var quoteValidation = validateTweet("quoted", formData.quoteTweetId());
        if (quoteValidation.getStatusCode().isError()) {
            return quoteValidation;
        }
        Tweet quoteTweet = (Tweet) quoteValidation.getBody();

        // retweet validation
        var retweetValidation = validateTweet("retweeted", formData.retweetId());
        if (retweetValidation.getStatusCode().isError()) {
            return retweetValidation;
        }
        Tweet retweet = (Tweet) retweetValidation.getBody();

        // create media entity list
        List<Media> mediaList = new ArrayList<>();
        for (int i = 0; i < formData.mediaUrls().size(); i++) {
            var mediaUrl = formData.mediaUrls().get(i);
            var mediaType = formData.mediaTypes().get(i);

            mediaList.add(new Media(UUID.randomUUID(), id, mediaType, mediaUrl));
        }

        // create and save tweet
        Tweet tweet = new Tweet(id, account, parentTweet, quoteTweet, retweet, mediaList, formData.textContent());
        repo.save(tweet);

        // return tweet
        return ResponseEntity.ok(TweetDto.fromTweetAndReplies(tweet, new ArrayList<>()));
    }

    private ResponseEntity<?> validateTweet(String tweetType, UUID id) {
        Tweet ret;
        // are we given an id?
        if (id == null) {
            // if not, return null
            ret = null;

            // if so, check if id is valid
        } else {
            var retOpt = repo.findById(id);

            if (retOpt.isEmpty()) {
                // if it does not exist, return error
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("%s tweet with id %s does not exist", tweetType, id));
            }
            // if not, return tweet
            ret = retOpt.get();
        }
        return ResponseEntity.ok(ret);
    }
}
