package y_rest.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import y_rest.models.dto.TweetDto;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
public class Tweet {
    @Id
    @Column
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column
    private Instant created;

    @Column(name = "parent_tweet_id")
    private UUID parentTweetId;

    @Column(name = "quote_tweet_id")
    private UUID quoteTweetId;

    @Column(name = "retweet_id")
    private UUID retweetId;

    @Column(name = "text_content")
    private String textContent;

    public Tweet(UUID id, UUID accountId, Instant created, UUID parentTweetId, UUID quoteTweetId, UUID retweetId, String textContent) {
        this.id = id;
        this.accountId = accountId;
        this.created = created;
        this.parentTweetId = parentTweetId;
        this.quoteTweetId = quoteTweetId;
        this.retweetId = retweetId;
        this.textContent = textContent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public UUID getParentTweetId() {
        return parentTweetId;
    }

    public void setParentTweetId(UUID parentTweetId) {
        this.parentTweetId = parentTweetId;
    }

    public UUID getQuoteTweetId() {
        return quoteTweetId;
    }

    public void setQuoteTweetId(UUID quoteTweetId) {
        this.quoteTweetId = quoteTweetId;
    }

    public UUID getRetweetId() {
        return retweetId;
    }

    public void setRetweetId(UUID retweetId) {
        this.retweetId = retweetId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public TweetDto convertToDto() {
        return new TweetDto(
                getId(),
                getAccountId(),
                getCreated(),
                getParentTweetId(),
                getQuoteTweetId(),
                getRetweetId(),
                getTextContent()
        );
    }
}

/*
CREATE TABLE tweet (

    id UUID NOT NULL,
    account_id UUID NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    parent_tweet_id UUID,
    quote_tweet_id UUID,
    retweet_id UUID,

    text_content varchar(280),

    CONSTRAINT tweet_pk PRIMARY KEY (id),

    -- if tweet you replied to gets deleted, reply is NOT deleted
    CONSTRAINT parent_fk FOREIGN KEY (parent_tweet_id) REFERENCES tweet (id),

    -- if original tweet gets deleted, quote tweet is NOT deleted
    CONSTRAINT quote_fk FOREIGN KEY (quote_tweet_id) REFERENCES tweet (id),

    -- if original tweet gets deleted, retweet gets deleted
    CONSTRAINT retweet_fk FOREIGN KEY (retweet_id) REFERENCES tweet (id) ON DELETE CASCADE,

    -- if account gets deleted, tweet gets deleted
    CONSTRAINT account_fk FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);
*/
