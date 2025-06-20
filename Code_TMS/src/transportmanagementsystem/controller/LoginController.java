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
import transportmanagementsystem.service.LoginService;

public class LoginController {
    private final LoginService service = new LoginService();
    private int failedAttempts = 0;

    public boolean login(String email, String password) {
        User user = new User(email, password);
        boolean success = service.authenticate(user);

        if (!success) {
            failedAttempts++;
        }

        return success;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }
}
