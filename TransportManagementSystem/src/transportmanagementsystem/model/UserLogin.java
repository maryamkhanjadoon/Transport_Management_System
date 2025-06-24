package transportmanagementsystem.model;

public class UserLogin {

    private static String loggedInUsername;
    private static UserLogin currentUser;

    private String email;
    private String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public static void setCurrentUser(UserLogin user) {
        currentUser = user;
    }

    public static UserLogin getCurrentUser() {
        return currentUser;
    }

    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }

    // ✅ FIXED: Implemented instead of throwing an exception
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
}
