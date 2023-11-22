package com.scd.Business;

import com.scd.Data.UserDAO;

public class Login {
    private UserDAO userDAO;

    public Login() {
        this.userDAO = new UserDAO();
    }

    public boolean login(String username, String password) {
        return userDAO.userAuthenticate(username, password);
    }

    public String userRole(String username, String password) {
        return userDAO.userRole(username, password);
    }
}
