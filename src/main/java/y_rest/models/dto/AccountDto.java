package y_rest.models.dto;

import java.time.Instant;
import java.util.UUID;

public class AccountDto {

    private final UUID id;
    private final Instant created;
    private final String handle;
    private final String email;
    private final String phone;
//    private final String hashedPw;
    private final Boolean priv;
    private final String bio;

    // Constructor
    public AccountDto(UUID id, Instant created, String handle, String email, String phone/*,  String hashedPw */, Boolean priv, String bio) {
        this.id = id;
        this.created = created;
        this.handle = handle;
        this.email = email;
        this.phone = phone;
//        this.hashedPw = hashedPw;
        this.priv = priv;
        this.bio = bio;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreated() {
        return created;
    }

    public String getHandle() {
        return handle;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

//    public String getHashedPw() {
//        return hashedPw;
//    }

    public Boolean getPriv() {
        return priv;
    }

    public String getBio() {
        return bio;
    }
}