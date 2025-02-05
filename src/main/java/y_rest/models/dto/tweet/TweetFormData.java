package y_rest.models.dto.tweet;


import java.util.List;
import java.util.UUID;

public record TweetFormData (
        // account info
        UUID accountId,

        // tweet info
        UUID parentTweetId,
        UUID quoteTweetId,
        UUID retweetId,
        String textContent,
        List<String> mediaUrls,
        List<String> mediaTypes,
        List<String> mentions // strings of handles
) {
}

