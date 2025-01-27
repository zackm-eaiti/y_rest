package y_rest.models.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

// Utilized by Tweetlike and Bookmark for their composite primary keys
public class AccountTweetCompositeKey implements Serializable {

    private UUID accountId;
    private UUID tweetId;

    public AccountTweetCompositeKey() {}

    public AccountTweetCompositeKey(UUID accountId, UUID tweetId) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountTweetCompositeKey that = (AccountTweetCompositeKey) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(tweetId, that.tweetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, tweetId);
    }
}
