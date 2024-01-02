package controller;

import client.Mahasiswa;
import client.User;
import course.Question;
import course.Quiz;

public interface ControllerToView {
    public abstract void login(String username, String password);
    public abstract void updateUser(String id, String fullname, String username, String role, String lecturerNip);
    public abstract void updatePass(String id, String pass);
    public abstract void deleteUser(String id);
    public abstract void addUser(User u, String pass);
    public abstract void addQuiz(Quiz q);
    public abstract void deleteQuiz(int id);
    public abstract void updateQuiz(String id, String title);
    public abstract void addQuestion(Question q);
    public abstract void deleteQuestion(String id);
    public abstract void updateQuestion(String id, String title);
    public abstract void addNilai(Mahasiswa m, int id_quiz, double nilai);
    public abstract void deleteNilai(String nim, int id_quiz);
}
