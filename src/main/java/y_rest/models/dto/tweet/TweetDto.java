package y_rest.models.dto.tweet;

import y_rest.models.dto.HashtagDto;
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
        TweetPreviewDto quoteTweet,
        TweetPreviewDto retweet,
        List<TweetPreviewDto> replies,

        String textContent,

        List<EmbeddedMediaDto> media,
        long likes,
        List<HashtagDto> hashtags,
        List<AccountPreviewDto> mentions
) {


    public static TweetDto fromTweet(Tweet tweet) {
        if (tweet == null) return null;

        return new TweetDto(
                tweet.getId(),
                AccountPreviewDto.fromAccount(tweet.getAccount()),
                tweet.getCreated(),

                TweetPreviewDto.fromTweet(tweet.getParentTweet()),
                TweetPreviewDto.fromTweet(tweet.getQuoteTweet()),
                TweetPreviewDto.fromTweet(tweet.getRetweet()),
                tweet.getReplies().stream().map(TweetPreviewDto::fromTweet).toList(),

                tweet.getTextContent(),

                tweet.getMedia().stream().map(EmbeddedMediaDto::fromMedia).toList(),
                tweet.getLikes(),
                tweet.getHashtags().stream().map(HashtagDto::fromHashtag).toList(),
                tweet.getMentions().stream().map(AccountPreviewDto::fromAccount).toList()
        );
    }
}
