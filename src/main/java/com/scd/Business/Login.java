package com.scd.Business;

import com.scd.Data.UserDAO;
import com.scd.Models.User;

public class Login {
    private UserDAO userDAO;

    public Login() {
        this.userDAO = new UserDAO();
    }

    /**
     * @param username
     * @param password
     * @return boolean
     */
    public boolean login(String username, String password) {
        return userDAO.userAuthenticate(username, password);
    }

    public User getUser(String username, String password) {
        return userDAO.getUser(username, password);
    }

    public String userRole(String username, String password) {
        return userDAO.userRole(username, password);
    }
}
