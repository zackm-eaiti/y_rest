package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import y_rest.models.dto.tweet.TweetDto;
import y_rest.service.TweetService;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetResource {

    @Autowired
    private TweetService service;

    @GetMapping
    public List<TweetDto> getTweets() {
        return service.listTweets().stream()
                .map(TweetDto::fromTweet)
                .toList();
    }

}
