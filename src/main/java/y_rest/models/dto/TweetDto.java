package y_rest.models.dto;

import y_rest.models.entity.Tweet;

public class TweetDto {

    private long id;
    private long userId;
    private String text;
    private String created;

    public TweetDto(Tweet entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.text = entity.getText();
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}