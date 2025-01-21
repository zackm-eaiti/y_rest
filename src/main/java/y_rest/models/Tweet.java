package y.models;

public class Tweet {
    private long id;
    private long userId;
    private String text;
    private String created;

    public Tweet(long id, String text, int userId, String created) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.created = created;
    }

    public long getId() { return id; }

    public long getUserId() { return userId; }

    public String getText() { return text; }

    public String getCreated() { return created; }
}