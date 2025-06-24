/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagementsystem.service;

/**
 *
 * @author WARDA YOUSAF
 */


import transportmanagementsystem.model.User;
import transportmanagementsystem.repository.UserRepository;
import transportmanagementsystem.validator.UserValidator;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public boolean registerUser(User user) {
        if (UserValidator.isValid(user)) {
            userRepository.saveUser(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public int getUserCount() {
        return userRepository.getUserCount();
    }
}
