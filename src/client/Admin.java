package client;

public class Admin extends User {
    
    private String id;
    
    public Admin(String id, String fullname, String username) {
        super(fullname, username);
        this.id=id;
    }

    public String getId() {
        return id;
    }

    
    
}