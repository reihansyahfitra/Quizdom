package client;

public class Dosen extends User {
    private String nip;

    public Dosen(String nip, String fullname, String username) {
        super(fullname, username);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }
}