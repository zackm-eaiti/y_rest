package y_rest.models.dto;

import y_rest.models.entity.User;

public class UserDto {
    private long id;
    private String handle;
    private String email;

    public UserDto(User entity) {
        this.id = entity.getId();
        this.handle = entity.getHandle();
        this.email = entity.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
