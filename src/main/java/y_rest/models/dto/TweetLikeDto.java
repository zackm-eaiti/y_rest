package y_rest.models.dto;

import y_rest.models.entity.TweetLike;

public class TweetLikeDto {
    private long id;
    private long userId;
    private long tweetId;
    private String created;

    public TweetLikeDto(TweetLike entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.tweetId = entity.getTweetId();
        this.created = entity.getCreated();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}