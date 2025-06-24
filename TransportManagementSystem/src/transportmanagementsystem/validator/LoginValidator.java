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


public class LoginValidator {
    public static boolean isEmpty(String email, String password) {
        return email.trim().isEmpty() || password.trim().isEmpty();
    }
}
