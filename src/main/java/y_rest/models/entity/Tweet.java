package y_rest.models.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Tweet {

    @Id
    @Column
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private Instant created;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_tweet_id")
    private Tweet parentTweet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_tweet_id")
    private Tweet quoteTweet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retweet_id")
    private Tweet retweet;

    @Column(name = "text_content")
    private String textContent;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tweet_id")
    private List<Media> media;

    @Formula("(SELECT COUNT(tl.id) FROM tweetlike tl WHERE tl.tweet_id = id)")
    private long likes;

    public Tweet() {
    }

    public Tweet(
            UUID id,
            Account account,
            Tweet parentTweet,
            Tweet quoteTweet,
            Tweet retweet,
            List<Media> media,
            String textContent
    ) {
        this.setId(id);
        this.setAccount(account);
        this.setCreated(Instant.now());
        this.setParentTweet(parentTweet);
        this.setQuoteTweet(quoteTweet);
        this.setRetweet(retweet);
        this.setMedia(media);
        this.setTextContent(textContent);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Tweet getParentTweet() {
        return parentTweet;
    }

    public void setParentTweet(Tweet parentTweet) {
        this.parentTweet = parentTweet;
    }

    public Tweet getQuoteTweet() {
        return quoteTweet;
    }

    public void setQuoteTweet(Tweet quoteTweet) {
        this.quoteTweet = quoteTweet;
    }

    public Tweet getRetweet() {
        return retweet;
    }

    public void setRetweet(Tweet retweet) {
        this.retweet = retweet;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }
}
