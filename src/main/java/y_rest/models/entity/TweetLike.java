package y_rest.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tweetlike")
public class TweetLike {

    @Id
    @Column
    private UUID id;

    @Column(name = "tweet_id")
    private UUID tweetId;

    @Column(name = "user_id")
    private UUID userId;

    public TweetLike() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTweetId() {
        return tweetId;
    }

    public void setTweetId(UUID tweetId) {
        this.tweetId = tweetId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
