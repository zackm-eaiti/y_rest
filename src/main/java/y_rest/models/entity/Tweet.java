package y_rest.models.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Tweet {

    @Id
    @Column
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @OneToMany(mappedBy = "parentTweet", fetch = FetchType.LAZY)
    private List<Tweet> replies;

    @Column(name = "text_content")
    private String textContent;

    @OneToMany(mappedBy = "tweet")
    private List<Media> media;

    // switch to tweetlikes
    @ManyToMany
    @JoinTable(
            name = "tweetlike",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> likes;

    @ManyToMany
    @JoinTable(
            name = "hashtag",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Hashtag> hashtags;

    @ManyToMany
    @JoinTable(
            name = "mention",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> mentions;

    public Tweet() {
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

    public List<Tweet> getReplies() {
        return replies;
    }

    public void setReplies(List<Tweet> replies) {
        this.replies = replies;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<Account> getLikes() {
        return likes;
    }

    public void setLikes(List<Account> likes) {
        this.likes = likes;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Account> getMentions() {
        return mentions;
    }

    public void setMentions(List<Account> mentions) {
        this.mentions = mentions;
    }
}
