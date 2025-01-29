package y_rest.models.dto.tweet;

import y_rest.models.dto.HashtagDto;
import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.models.dto.media.EmbeddedMediaDto;
import y_rest.models.entity.Tweet;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

// used when we are seeing a tweet as a reply,
// and to avoid circular references with parent / and replies
public record TweetPreviewDto(
        UUID id,
        AccountPreviewDto account,
        Instant created,
        TweetPreviewDto parentTweet,
        TweetPreviewDto quoteTweet,
        TweetPreviewDto retweet,
        String textContent,
        List<EmbeddedMediaDto> media,
        long likes,
        List<HashtagDto> hashtags
) {
    public static TweetPreviewDto fromTweet(Tweet tweet) {
        if (tweet == null) return null;

        return new TweetPreviewDto(
                tweet.getId(),
                AccountPreviewDto.fromAccount(tweet.getAccount()),
                tweet.getCreated(),
                TweetPreviewDto.fromTweet(tweet.getParentTweet()),
                TweetPreviewDto.fromTweet(tweet.getQuoteTweet()),
                TweetPreviewDto.fromTweet(tweet.getRetweet()),
                tweet.getTextContent(),
                tweet.getMedia().stream().map(EmbeddedMediaDto::fromMedia).toList(),
                tweet.getLikes(),
                tweet.getHashtags().stream().map(HashtagDto::fromHashtag).toList()
        );
    }
}
