package y_rest.models.entity;

import jakarta.persistence.*;

import java.util.UUID;

// this entity exists so that I can create the media DTO
@Entity
@Table
public class Media {

    @Id
    @Column
    private UUID id;

    @Column(name = "tweet_id")
    private UUID tweetId;

    @Column(name = "media_type")
    private String mediaType;

    @Column
    private String url;

    public Media() {}

    public Media(UUID id, UUID tweetId, String mediaType, String url) {
        this.id = id;
        this.tweetId = tweetId;
        this.mediaType = mediaType;
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getTweetId() {
        return tweetId;
    }

    public void setTweetId(UUID tweetId) {
        this.tweetId = tweetId;
    }
}
