package client;

public class Grade {
    private String nim;
    private int id_quiz;
    private double nilai;

    public Grade(String nim, int id_quiz, double nilai) {
        this.nim = nim;
        this.id_quiz = id_quiz;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public double getNilai() {
        return nilai;
    }
    
    
}