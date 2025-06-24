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
import transportmanagementsystem.repository.LoginRepository;

public class LoginService {
    private final LoginRepository repo = new LoginRepository();

    public boolean authenticate(User user) {
        return repo.isValidUser(user);
    }
}
