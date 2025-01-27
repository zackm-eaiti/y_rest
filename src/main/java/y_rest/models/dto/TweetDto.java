package y_rest.models.dto;

import java.time.Instant;
import java.util.UUID;

public class TweetDto {

    private final UUID id;
    private final UUID accountId;
    private final Instant created;
    private final UUID parentTweetId;
    private final UUID quoteTweetId;
    private final UUID retweetId;
    private final String textContent;

    public TweetDto(UUID id, UUID accountId, Instant created, UUID parentTweetId, UUID quoteTweetId, UUID retweetId, String textContent) {
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

    public UUID getAccountId() {
        return accountId;
    }

    public Instant getCreated() {
        return created;
    }

    public UUID getParentTweetId() {
        return parentTweetId;
    }

    public UUID getQuoteTweetId() {
        return quoteTweetId;
    }

    public UUID getRetweetId() {
        return retweetId;
    }

    public String getTextContent() {
        return textContent;
    }
}
