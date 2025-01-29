package y_rest.models.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Account {

    // maybe add a pinned tweet feature?

    @Id
    @Column
    private UUID id;

    @Column
    private Instant created;

    @Column
    private String handle;

    @Column(name = "display")
    private String displayName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "banner_url")
    private String bannerPicUrl;

    @Column(name = "hashed_pw")
    private String hashedPw;

    @Column
    private String bio;

    @ManyToMany
    @JoinTable(
            name = "follow", // existing join table
            joinColumns = @JoinColumn(name = "sheep_id"),
            inverseJoinColumns = @JoinColumn(name = "shepherd_id")
    )
    private List<Account> following;

    @ManyToMany(mappedBy = "following")
    private List<Account> followers;

    // loads all the likes - switch to tweetlike to avoid loading all these accounts 
    @ManyToMany
    @JoinTable(
            name = "tweetlike",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )
    private List<Tweet> likes;

    // only used when you search for an at, we don't actually need/use this in the account dto
    @ManyToMany(mappedBy = "mentions")
    private List<Tweet> mentionedTweets;

    public Account() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getBannerPicUrl() {
        return bannerPicUrl;
    }

    public String getHashedPw() {
        return hashedPw;
    }

    public void setHashedPw(String hashedPw) {
        this.hashedPw = hashedPw;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Account> getFollowing() {
        return following;
    }

    public void setFollowing(List<Account> following) {
        this.following = following;
    }

    public List<Account> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Account> followers) {
        this.followers = followers;
    }

    public List<Tweet> getLikes() {
        return likes;
    }

    public void setLikes(List<Tweet> likes) {
        this.likes = likes;
    }

}