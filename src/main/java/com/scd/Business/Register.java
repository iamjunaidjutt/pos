package com.scd.Business;

import com.scd.Data.UserDAO;
import com.scd.Models.User;

public class Register {
    private UserDAO userDAO;

    public Register() {
        this.userDAO = new UserDAO();
    }

    public boolean register(User user) {
        return userDAO.create(user);
    }
}
