package client;

public class Mahasiswa extends User {
    private String nim;
    private String dosenSiapa;

    public Mahasiswa(String nim, String dosenSiapa, String fullname, String username) {
        super(fullname, username);
        this.nim = nim;
        this.dosenSiapa = dosenSiapa;
    }

    public String getDosenSiapa() {
        return dosenSiapa;
    }

    public String getNim() {
        return nim;
    }
}