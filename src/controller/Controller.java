package controller;

import client.*;
import course.Question;
import course.Quiz;
import view.*;
import java.util.ArrayList;

public class Controller implements ControllerToView {
    ArrayList<User> listUser;
    ArrayList<Grade> listGrade;
    ArrayList<Quiz> listQuiz;
    ArrayList<Question> listQuestion;
    Login loginView;
    DashboardMhs dashboardMhsView;
    DashboardDosen dashboardDosenView;
    DashboardAdmin dashboardAdminView;
    
    public Controller(){
        this.listUser = new ArrayList();
        this.listQuiz = new ArrayList();
        this.listGrade = new ArrayList();
        this.listQuestion = new ArrayList();
        this.listUser.addAll(Database.getDosen());
        this.listUser.addAll(Database.getMhs());
        this.listUser.addAll(Database.getAdmin());
        this.listQuiz.addAll(Database.getQuiz());
        this.listGrade.addAll(Database.getNilai());
        this.listQuestion.addAll(Database.getQuestion());
    }
    
    
    public void defaultView(){
        this.loginView = new Login();
        this.loginView.setVisible(true);
        this.loginView.setSender(this);
    }

    @Override
    public void login(String username, String password) {
        if(Database.validateLogin(username, password)){
            User user = null;
            for(User u:this.listUser){
                System.out.println(u.getUsername());
                if(u.getUsername().equals(username)){
                    user = u;
                    break;
                }
            }
            if(user!=null){
                System.out.println("awe");
                this.loginView.setVisible(false);
                String result = Database.findRole(user);
                System.out.println(result);
                switch(result){
                    case "Mahasiswa" -> {
                        this.dashboardMhsView = new DashboardMhs((Mahasiswa)user, this.listQuiz, this.listGrade, this.listQuestion);
                        this.dashboardMhsView.setVisible(true);
                        this.dashboardMhsView.setSub(this);
                    }
                    case "Dosen" -> {
                        this.dashboardDosenView = new DashboardDosen((Dosen)user, this.listUser, this.listQuiz, this.listGrade);
                        this.dashboardDosenView.setVisible(true);
                        this.dashboardDosenView.setSub(this);
                    }
                    case "Admin" -> {
                        System.out.println("awe");
                        this.dashboardAdminView = new DashboardAdmin(user.getFullname(), this.listUser);
                        this.dashboardAdminView.setVisible(true);
                        this.dashboardAdminView.setSub(this);
                    }
                }
            }
            
        }else{
            System.out.println("Login failed");
        }
    }

    @Override
    public void updateUser(String id, String fullname, String username, String role, String lecturerNip) {
        Database.updateUser(id, fullname, username, role, lecturerNip);
        this.refresh();
    }
    
    public void updatePass(String id, String newPass){
        Database.changePassword(id, newPass);
        this.refresh();
    }
    
    public void deleteUser(String id){
        Database.deleteUser(id);
        this.refresh();
    }
    
    @Override
    public void addUser(User u, String pass){
        if(u instanceof Dosen){
            Database.addDosen((Dosen)u, pass);
        }else if (u instanceof Mahasiswa){
            Database.addMahasiswa((Mahasiswa)u, pass);
        }else if (u instanceof Admin){
            System.out.println("Belum bisa");
        }
        this.refresh();
    }
    
    public void refresh(){
        this.listUser = new ArrayList();
        this.listQuiz = new ArrayList();
        this.listQuestion = new ArrayList();
        this.listGrade = new ArrayList();
        this.listUser.addAll(Database.getDosen());
        this.listUser.addAll(Database.getMhs());
        this.listUser.addAll(Database.getAdmin());
        this.listQuiz.addAll(Database.getQuiz());
        this.listQuestion.addAll(Database.getQuestion());
        this.listGrade.addAll(Database.getNilai());
        if(this.dashboardAdminView!=null){
            this.dashboardAdminView.setUsers(listUser);
        }
    }

    @Override
    public void addNilai(Mahasiswa m, int id_quiz, double nilai){
        if(Database.findNilai(m, id_quiz)){
            Database.editNilai(m, id_quiz, nilai);
        }else{
            Database.addNilai(m, id_quiz, nilai);
        }
        this.dashboardMhsView.repaint();
        this.dashboardMhsView.revalidate();
        this.dashboardMhsView.setVisible(true);
    }
    
    public void deleteNilai(String id, int id_quiz){
        Database.deleteNilai(id, id_quiz);
        this.refresh();
        this.dashboardDosenView.setListMhs();
    }
    
    @Override
    public void addQuiz(Quiz q) {
        
    }

    @Override
    public void deleteQuiz(int id) {
        Database.deleteQuiz(id);
        this.refresh();
        this.dashboardDosenView.setQuizs(listQuiz);
    }

    @Override
    public void updateQuiz(String id, String title) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addQuestion(Question q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteQuestion(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateQuestion(String id, String title) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}