package y_rest.models.dto.tweet;

import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.models.dto.media.EmbeddedMediaDto;
import y_rest.models.entity.Tweet;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

// just to make the json cleaner
public record ReplyDto(
        UUID id,
        AccountPreviewDto account,
        Instant created,
        String textContent,
        List<EmbeddedMediaDto> media,
        long likeCount,
        long repostCount
) {
    public static ReplyDto create(Tweet tweet) {
        if (tweet == null) return null;

        return new ReplyDto(
                tweet.getId(),
                AccountPreviewDto.fromAccount(tweet.getAccount()),
                tweet.getCreated(),
                tweet.getTextContent(),
                tweet.getMedia().stream().map(EmbeddedMediaDto::create).toList(),
                tweet.getLikeCount(),
                tweet.getRepostCount()
        );
    }
}
