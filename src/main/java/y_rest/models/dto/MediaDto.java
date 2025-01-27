package y_rest.models.dto;

import java.util.UUID;

public class MediaDto {
    private final UUID id;
    private final UUID tweetId;
    private final String mediaType;
    private final String url;

    public MediaDto(UUID id, UUID tweetId, String mediaType, String url) {
        this.id = id;
        this.tweetId = tweetId;
        this.mediaType = mediaType;
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTweetId() {
        return tweetId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getUrl() {
        return url;
    }
}
