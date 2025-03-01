package y_rest.models.dto.tweet;

import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.models.dto.media.EmbeddedMediaDto;
import y_rest.models.entity.Tweet;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

// used when you click on a tweet
public record TweetDto(
        UUID id,
        AccountPreviewDto account,
        Instant created,
        TweetPreviewDto parentTweet,
        List<ReplyDto> replies,
        TweetPreviewDto retweet,
        String textContent,
        List<EmbeddedMediaDto> media,
        long likeCount,
        long repostCount
) {
    public static TweetDto create(Tweet tweet, List<Tweet> replies) {
        if (tweet == null) return null;

        return new TweetDto(
                tweet.getId(),
                AccountPreviewDto.fromAccount(tweet.getAccount()),
                tweet.getCreated(),

                TweetPreviewDto.create(tweet.getParentTweet()),
                replies.stream().map(ReplyDto::create).toList(),
                TweetPreviewDto.create(tweet.getRetweet()),

                tweet.getTextContent(),

                tweet.getMedia().stream().map(EmbeddedMediaDto::create).toList(),
                tweet.getLikeCount(),
                tweet.getRepostCount()
        );
    }
}
