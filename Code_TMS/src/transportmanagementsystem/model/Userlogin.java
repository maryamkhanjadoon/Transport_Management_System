/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagementsystem.model;

/**
 *
 * @author WARDA YOUSAF
 */


public class Userlogin {
    private String email;
    private String password;

    public Userlogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

