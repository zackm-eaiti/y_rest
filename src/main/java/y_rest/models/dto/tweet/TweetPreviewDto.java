package y_rest.models.dto.tweet;

import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.models.dto.media.EmbeddedMediaDto;
import y_rest.models.entity.Tweet;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

// used when we are seeing a tweet as a reply,
public record TweetPreviewDto(
        UUID id,
        AccountPreviewDto account,
        Instant created,
        TweetPreviewDto parentTweet,
        TweetPreviewDto retweet,
        String textContent,
        List<EmbeddedMediaDto> media,
        long likeCount,
        long repostCount
) {
    public static TweetPreviewDto create(Tweet tweet) {
        if (tweet == null) return null;

        return new TweetPreviewDto(
                tweet.getId(),
                AccountPreviewDto.fromAccount(tweet.getAccount()),
                tweet.getCreated(),
                TweetPreviewDto.create(tweet.getParentTweet()),
                TweetPreviewDto.create(tweet.getRetweet()),
                tweet.getTextContent(),
                tweet.getMedia().stream().map(EmbeddedMediaDto::create).toList(),
                tweet.getLikeCount(),
                tweet.getRepostCount()
        );
    }
}
