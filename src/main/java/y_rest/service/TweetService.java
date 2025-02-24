package y_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import y_rest.models.dto.tweet.TweetDto;
import y_rest.models.dto.tweet.TweetFormData;
import y_rest.models.dto.tweet.TweetPreviewDto;
import y_rest.models.entity.*;
import y_rest.models.repository.TweetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// again abusing var because i can

@Service
public class TweetService {

    @Autowired
    private TweetRepository repo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private MentionService mentionService;

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

        return ResponseEntity.ok(TweetDto.create(tweet.get(), replies));
    }

    public ResponseEntity<?> postTweet(TweetFormData formData) {
        UUID id = UUID.randomUUID();

        // account validation
        String accountHandle = formData.accountHandle();
        var accountResponse = accountService.getUserByHandle(accountHandle);
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
        Tweet tweet = new Tweet(id, account, parentTweet, retweet, mediaList, formData.textContent());
        repo.save(tweet);

        // now, parse text for hashtags
        Pattern htPat = Pattern.compile("\\s*#(\\w+)");
        Matcher htMatcher = htPat.matcher(tweet.getTextContent());

        // create and save hashtags
        while (htMatcher.find()) {                            // v label v
            var hashtag = new Hashtag(UUID.randomUUID(), htMatcher.group(1), tweet);
            hashtagService.save(hashtag);
        }

        // parse for mentions
        Pattern mPat = Pattern.compile("\\s*@(\\w+)");
        Matcher mMatcher = mPat.matcher(tweet.getTextContent());

        // create and save mentions
        while (mMatcher.find()) {                            // v label v
            var mention = new Mention(UUID.randomUUID(), mMatcher.group(1), tweet);
            mentionService.save(mention);
        }

        // return tweet
        return ResponseEntity.ok(TweetDto.create(tweet, new ArrayList<>()));
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

    public ResponseEntity<?> deleteTweet(String tweetId) {
        var tweet = repo.findById(UUID.fromString(tweetId));
        if (tweet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("can not delete, tweet %s does not exist", tweetId));
        }

        repo.delete(tweet.get());
        return ResponseEntity.ok(String.format("tweet %s successfully deleted", tweetId));
    }

    public List<TweetPreviewDto> searchForTweet(String phrase) {
        return repo.findByPhraseLike(phrase).stream().map(TweetPreviewDto::create).toList();
    }

    public List<TweetPreviewDto> findByUserIds(List<String> userIds) {
        return repo.findByAccountIn(
                userIds.stream().map(UUID::fromString).toList()
        ).stream().map(TweetPreviewDto::create).toList();
    }
}
