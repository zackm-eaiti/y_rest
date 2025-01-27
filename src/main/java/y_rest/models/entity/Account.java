package y_rest.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import y_rest.models.dto.AccountDto;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table
public class Account {

    @Id
    @Column
    private UUID id;

    @Column
    private Instant created;

    @Column
    private String handle;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(name = "hashed_pw")
    private String hashedPw;

    @Column
    private Boolean priv;

    @Column
    private String bio;

    public Account(UUID id, Instant created, String handle, String email, String phone, String hashedPw, Boolean priv, String bio) {
        this.id = id;
        this.created = created;
        this.handle = handle;
        this.email = email;
        this.phone = phone;
        this.hashedPw = hashedPw;
        this.priv = priv;
        this.bio = bio;
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

    public String getHashedPw() {
        return hashedPw;
    }

    public void setHashedPw(String hashedPw) {
        this.hashedPw = hashedPw;
    }

    public Boolean getPriv() {
        return priv;
    }

    public void setPriv(Boolean priv) {
        this.priv = priv;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

    public AccountDto convertToDto() {
        return new AccountDto(
                getId(),
                getCreated(),
                getHandle(),
                getEmail(),
                getPhone(),
//                getHashedPw(),
                getPriv(),
                getBio()
        );
    }
}