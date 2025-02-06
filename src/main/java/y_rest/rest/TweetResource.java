package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import y_rest.models.dto.tweet.TweetFormData;
import y_rest.service.TweetLikeService;
import y_rest.service.TweetService;

@RestController
@RequestMapping("/tweet")
public class TweetResource {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetLikeService tlService;

    @PostMapping("/post_tweet")
    public ResponseEntity<?> postTweet(@RequestBody TweetFormData formData) {
        return tweetService.postTweet(formData);
    }

    @GetMapping("/{tweet_id}")
    public ResponseEntity<?> getTweet(@PathVariable("tweet_id") String id) {
        return tweetService.getTweetDtoById(id);
    }

    @PostMapping("/{tweet_id}/post_like")
    public ResponseEntity<?> postTweetLike(@PathVariable("tweet_id") String tweetId, @RequestParam("account_handle") String accountHandle) {
        return tlService.postLike(tweetId, accountHandle);
    }

    @DeleteMapping("/{tweet_id}/delete_like")
    public ResponseEntity<?> deleteTweetLike(@PathVariable("tweet_id") String tweetId, @RequestParam("account_handle") String accountHandle) {
        return tlService.deleteLike(tweetId, accountHandle);
    }

    @DeleteMapping("/{tweet_id}/delete")
    public ResponseEntity<?> deleteTweet(@PathVariable("tweet_id") String tweetId) {
        return tweetService.deleteTweet(tweetId);
    }
}
