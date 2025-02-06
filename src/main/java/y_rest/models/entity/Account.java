package y_rest.models.entity;

import jakarta.persistence.*;
import y_rest.models.dto.account.AccountFormData;

import java.time.Instant;
import java.util.*;

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

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "banner_pic_url")
    private String bannerPicUrl;

    @Column(name = "hashed_pw")
    private String hashedPw;

    @Column
    private String bio;

    @ManyToMany
    @JoinTable(name = "follow", // existing join table
            joinColumns = @JoinColumn(name = "sheep_id"), inverseJoinColumns = @JoinColumn(name = "shepherd_id"))
    private Set<Account> shepherds;

    @ManyToMany(mappedBy = "shepherds")
    private Set<Account> sheep;

    public Account() {
    }

    public Account(AccountFormData formData) {

        // auto-gen
        this.setId(UUID.randomUUID());
        this.setCreated(Instant.now());

        // form data
        this.setHandle(formData.handle());
        this.setDisplayName(formData.displayName());
        this.setEmail(formData.email());

        // TODO: Hash
        this.setHashedPw(formData.password());

        this.setProfilePicUrl(formData.profilePicUrl());
        this.setBannerPicUrl(formData.bannerPicUrl());
        this.setBio(formData.bio());

        // "My following" means the same as "My followers"
        this.setShepherds(new HashSet<>());
        this.setSheep(new HashSet<>());
    }

    public Account updateFromFormData(AccountFormData formData) {
        // null = no change
        if (formData.handle() != null) {
            this.setHandle(formData.handle());
        }
        if (formData.displayName() != null) {
            this.setDisplayName(formData.displayName());
        }
        if (formData.email() != null) {
            this.setEmail(formData.email());
        }
        if (formData.password() != null) {
            this.setHashedPw(formData.password());
        }
        if (formData.profilePicUrl() != null) {
            this.setProfilePicUrl(formData.profilePicUrl());
        }
        if (formData.bannerPicUrl() != null) {
            this.setBannerPicUrl(formData.bannerPicUrl());
        }
        if (formData.bio() != null) {
            this.setBio(formData.bio());
        }
        return this;
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

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getBannerPicUrl() {
        return bannerPicUrl;
    }

    public void setBannerPicUrl(String bannerPicUrl) {
        this.bannerPicUrl = bannerPicUrl;
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

    public Set<Account> getShepherds() {
        return shepherds;
    }

    public void setShepherds(Set<Account> shepherds) {
        this.shepherds = shepherds;
    }

    public Set<Account> getSheep() {
        return sheep;
    }

    public void setSheep(Set<Account> sheep) {
        this.sheep = sheep;
    }

}