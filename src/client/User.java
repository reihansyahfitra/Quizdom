package client;
public class User {
    private String fullname;
    private String username;

    public User(String fullname, String username) {
        this.fullname = fullname;
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }
}