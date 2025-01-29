package y_rest.models.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

// this entity exists explicitly so that I can search for a hashtag and easily get all the
// tweets associated
@Entity
public class Hashtag {

    @Id
    private UUID id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "hashtags")
    private List<Tweet> tweets;

    public Hashtag() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}

