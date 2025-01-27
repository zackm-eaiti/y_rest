package y_rest.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;
import y_rest.models.dto.HashTagDto;

import java.util.UUID;

@Entity
@Table
@IdClass(HashTagId.class)
public class HashTag {

    @Id
    @Column
    private String name;

    @Id
    @Column(name = "tweet_id")
    private UUID tweetId;

    public HashTag(String name, UUID tweetId) {
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

    public HashTagDto convertToDto() {
        return new HashTagDto(
                getName(),
                getTweetId()
        );
    }
}
