package y_rest.models.dto;

import java.util.UUID;

public class BookmarkDto {
    private final UUID accountId;
    private final UUID tweetId;

    public BookmarkDto(UUID accountId, UUID tweetId) {
        this.accountId = accountId;
        this.tweetId = tweetId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public UUID getTweetId() {
        return tweetId;
    }
}
