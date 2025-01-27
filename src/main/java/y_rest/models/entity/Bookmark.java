package y_rest.models.entity;

import jakarta.persistence.*;
import y_rest.models.dto.BookmarkDto;

import java.util.UUID;

@Entity
@Table
@IdClass(AccountTweetCompositeKey.class) // maybe do class BookmarkId extends Account...Key ? for readability
public class Bookmark {

    @Id
    @Column(name = "account_id")
    private UUID accountId;

    @Id
    @Column(name = "tweet_id")
    private UUID tweetId;

    public Bookmark(UUID accountId, UUID tweetId) {
        this.accountId = accountId;
        this.tweetId = tweetId;
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

    public BookmarkDto convertToDto() {
        return new BookmarkDto(
                getAccountId(),
                getTweetId()
        );
    }
}
