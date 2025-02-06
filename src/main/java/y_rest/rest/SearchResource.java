package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.models.dto.tweet.TweetPreviewDto;
import y_rest.service.AccountService;
import y_rest.service.HashtagService;
import y_rest.service.MentionService;
import y_rest.service.TweetService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchResource {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private MentionService mentionService;

    @Autowired
    private TweetService tweetService;

    // for now, search by username and search by handle - what if no query request param? its fine for now but edit this?
    @GetMapping("/user")
    public List<AccountPreviewDto> searchForUser(@RequestParam("handleOrName") String handleOrName) {
        return accountService.searchForUser(handleOrName);
    }

    @GetMapping("/hashtag")
    public List<TweetPreviewDto> searchForHashtag(@RequestParam("label") String label) {
        return hashtagService.searchForHashtags(label);
    }

    @GetMapping("/mention")
    public List<TweetPreviewDto> searchForMention(@RequestParam("label") String label) {
        return mentionService.searchForMentions(label);
    }

    @GetMapping("/tweet")
    public List<TweetPreviewDto> searchForTweet(@RequestParam("phrase") String phrase) {
        return tweetService.searchForTweet(phrase);
    }
}
