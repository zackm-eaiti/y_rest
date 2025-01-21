package y.models;

public class User {
    private long id;
    private String handle;
    private String email;
    
    public User(long id, String handle, String email) {
        this.id = id;
        this.handle = handle;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getEmail() {
        return email;
    }
}
