/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagementsystem.controller;

/**
 *
 * @author WARDA YOUSAF
 */


import transportmanagementsystem.model.User;
import transportmanagementsystem.service.UserService;

import java.util.List;

public class UserController {
    private UserService userService = new UserService();

    public boolean registerUser(User user) {
        return userService.registerUser(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public int getUserCount() {
        return userService.getUserCount();
    }
}
