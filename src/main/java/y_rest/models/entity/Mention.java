package y_rest.models.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
// mention class only exists for efficient searching and retrieval of tweets containing mentions
public class Mention {

    @Id
    @Column
    private UUID id;

    @Column
    private String label;

    // i'm using one to one because it makes creating and saving them more efficient - not
    // having to load a mention entity and all the tweets with that mention
    // when I add a mention
    @OneToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public Mention() {
    }

    public Mention(UUID id, String label, Tweet tweet) {
        this.id = id;
        this.label = label;
        this.tweet = tweet;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
