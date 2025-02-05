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
        AccountPreviewDto account, // we aren't using account dto because we don't need all the information
        Instant created,

        TweetPreviewDto parentTweet,
        TweetPreviewDto quoteTweet,
        TweetPreviewDto retweet,
        List<TweetPreviewDto> replies,

        String textContent,

        List<EmbeddedMediaDto> media,
        long likes
//        List<AccountPreviewDto> mentions
) {
    public static TweetDto fromTweetAndReplies(Tweet tweet, List<Tweet> replies) {
        if (tweet == null) return null;

        return new TweetDto(
                tweet.getId(),
                AccountPreviewDto.fromAccount(tweet.getAccount()),
                tweet.getCreated(),

                TweetPreviewDto.create(tweet.getParentTweet()),
                TweetPreviewDto.create(tweet.getQuoteTweet()),
                TweetPreviewDto.create(tweet.getRetweet()),
                replies.stream().map(TweetPreviewDto::create).toList(),

                tweet.getTextContent(),

                tweet.getMedia().stream().map(EmbeddedMediaDto::fromMedia).toList(),
                tweet.getLikes()
//                tweet.getMentions().stream().map(AccountPreviewDto::fromAccount).toList()
        );
    }
}
