package controller;

import client.User;

public interface AdminToView {
    public abstract void transfer(String id, String fullname, String username, String role, String lecturerNip);
    public abstract void changePass(String id, String newPass);
    public abstract void insert(User u, String pass);
}
