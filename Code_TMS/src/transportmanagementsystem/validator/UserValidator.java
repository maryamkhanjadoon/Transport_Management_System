/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagementsystem.validator;

/**
 *
 * @author WARDA YOUSAF
 */


import transportmanagementsystem.model.User;

public class UserValidator {
    public static boolean isValid(User user) {
        return !user.getName().isEmpty() && user.getEmail().contains("@");
    }
}
