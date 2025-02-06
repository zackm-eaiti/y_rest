package y_rest.models.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tweetlike")
public class TweetLike {

    @Id
    @Column
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public TweetLike() {
    }

    public TweetLike(UUID id, Tweet tweet, Account account) {
        this.id = id;
        this.tweet = tweet;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}