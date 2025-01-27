package y_rest.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;
import y_rest.models.dto.TweetLikeDto;

import java.time.Instant;

import java.util.UUID;

@Entity
@Table
@IdClass(AccountTweetCompositeKey.class)
public class TweetLike {

    @Id
    @Column(name = "account_id")
    private UUID accountId;

    @Id
    @Column(name = "tweet_id")
    private UUID tweetId;

    @Column
    private Instant created;

    public TweetLike(UUID accountId, UUID tweetId, Instant created) {
        this.accountId = accountId;
        this.tweetId = tweetId;
        this.created = created;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getTweetId() {
        return tweetId;
    }

    public void setTweetId(UUID tweetId) {
        this.tweetId = tweetId;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public TweetLikeDto convertToDto() {
        return new TweetLikeDto(
                getAccountId(),
                getTweetId(),
                getCreated()
        );
    }
}
