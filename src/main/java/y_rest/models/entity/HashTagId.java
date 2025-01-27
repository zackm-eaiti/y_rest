package y_rest.models.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class HashTagId implements Serializable {
    private String name;
    private UUID tweetId;

    public HashTagId() {}

    public HashTagId(String name, UUID tweetId) {
        this.name = name;
        this.tweetId = tweetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        HashTagId hashTagId = (HashTagId) o;
        return Objects.equals(name, hashTagId.name) && Objects.equals(tweetId, hashTagId.tweetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tweetId);
    }
}
