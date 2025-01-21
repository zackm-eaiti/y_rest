package y.models;

public class TweetLike {
    private long id;
    private long userId;
    private long tweetId;
    private String created;

    public TweetLike(long id, long userId, long tweetId, String created) {
        this.id = id;
        this.userId = userId;
        this.tweetId = tweetId;
        this.created = created;
    }

    public long getId() { return id; }

    public long getUserId() { return userId; }

    public long getTweetId() { return tweetId; }

    public String getCreated() { return created; }
}
