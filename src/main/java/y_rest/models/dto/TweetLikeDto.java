package y_rest.models.dto;

import java.time.Instant;
import java.util.UUID;

public class TweetLikeDto {
    private final UUID accountId;
    private final UUID tweetId;
    private final Instant created;

    public TweetLikeDto(UUID accountId, UUID tweetId, Instant created) {
        this.accountId = accountId;
        this.tweetId = tweetId;
        this.created = created;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public UUID getTweetId() {
        return tweetId;
    }

    public Instant getCreated() {
        return created;
    }
}
