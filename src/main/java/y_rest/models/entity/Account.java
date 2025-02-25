package y_rest.models.entity;

import java.io.IOException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import y_rest.models.dto.account.AccountFormData;

import java.time.Instant;
import java.util.*;

@Entity
@Table
public class Account {
    private static final Logger log = LogManager.getLogger(Account.class);

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

    @Column(name = "profile_pic")
    private byte[] profilePic;

    @Column(name = "banner_pic")
    private byte[] bannerPic;

    @Column(name = "hashed_pw")
    private String hashedPw;

    @Column
    private String salt;

    @Column
    private String bio;

    @ManyToMany
    @JoinTable(name = "follow", // existing join table
            joinColumns = @JoinColumn(name = "sheep_id"), inverseJoinColumns = @JoinColumn(name = "shepherd_id"))
    private Set<Account> shepherds;

    @ManyToMany(mappedBy = "shepherds")
    private Set<Account> sheep;

    @Column
    private UUID authtoken;

    public Account() {
    }

    public Account(AccountFormData formData) throws IOException {

        // auto-gen
        this.setId(UUID.randomUUID());
        this.setCreated(Instant.now());

        // form data
        this.setHandle(formData.handle());
        this.setDisplayName(formData.displayName());
        this.setEmail(formData.email());
        this.setSalt(generateSalt(16));
        this.setHashedPw(hashPassword(formData.password(), this.getSalt()));

        if (formData.profilePic() != null) {
            this.setProfilePic(formData.profilePic().getBytes());
        } else {
            this.setProfilePic(null);
        }

        if (formData.bannerPic() != null) {
            this.setBannerPic(formData.bannerPic().getBytes());
        } else {
            this.setBannerPic(null);
        }

        this.setBio(formData.bio());

        // "My following" means the same as "My followers"
        this.setShepherds(new HashSet<>());
        this.setSheep(new HashSet<>());
    }

    public boolean authenticate(String formPassword) {
        String formHashed = hashPassword(formPassword, this.getSalt());
        return formHashed.equals(this.getHashedPw());
    }

    public Account updateFromFormData(AccountFormData formData) throws IOException {
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
        if (formData.bio() != null) {
            this.setBio(formData.bio());
        }
        if (formData.profilePic() != null) {
            this.setProfilePic(formData.profilePic().getBytes());
        }
//        if (formData.bannerPic() != null) {
//            this.setBannerPic(formData.bannerPic().getBytes());
//        }
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

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public static String hashPassword(String password, String salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            log.error("e: ", e);
        }

        return "";
    }

    public static String generateSalt(int length) {
        byte[] salt = new byte[length];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        // convert byte array to hex string for storing in database
        StringBuilder hexString = new StringBuilder();
        for (byte b : salt) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public UUID getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(UUID authtoken) {
        this.authtoken = authtoken;
    }

    public byte[] getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(byte[] bannerPic) {
        this.bannerPic = bannerPic;
    }
}