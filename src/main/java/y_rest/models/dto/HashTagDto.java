package y_rest.models.dto;

import java.util.UUID;

public class HashTagDto {

    private final String name;
    private final UUID tweetId;

    public HashTagDto(String name, UUID tweetId) {
        this.name = name;
        this.tweetId = tweetId;
    }

    public String getName() {
        return name;
    }

    public UUID getTweetId() {
        return tweetId;
    }
}
