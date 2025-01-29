package y_rest.models.dto;

import y_rest.models.entity.Hashtag;

import java.util.UUID;

// used within tweets, for so we can easily look up all tweets with a hashtag when we
// click on a hashtag within a tweet
public record HashtagDto(
        UUID id,
        String name
) {
    public static HashtagDto fromHashtag(Hashtag hashtag) {
        return new HashtagDto(
                hashtag.getId(),
                hashtag.getName()
        );
    }
}
