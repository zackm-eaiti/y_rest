package y_rest.models.dto.tweet;


import java.util.List;
import java.util.UUID;

public record TweetFormData (
        // account info
        String accountHandle,

        // tweet info
        UUID parentTweetId,
        UUID retweetId,
        String textContent,
        List<String> mediaUrls,
        List<String> mediaTypes
) {
}

