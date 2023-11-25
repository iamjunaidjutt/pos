package com.scd.Business;

import com.scd.Data.UserDAO;
import com.scd.Models.User;

public class Register {
    private UserDAO userDAO;

    public Register() {
        this.userDAO = new UserDAO();
    }

    public boolean register(User user) {
        System.out.println(user);
        return userDAO.save(user);
    }

    public boolean checkUsername(String username) {
        return userDAO.checkUsername(username);
    }
}
