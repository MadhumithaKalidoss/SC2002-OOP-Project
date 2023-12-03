import java.util.Scanner;

/**
 * Represents a user in the system
 */
public class User {
    /**
     * name of user
     */
    private String name;
    /**
     * user's email address
     */
    private String email;
    /**
     * The user's faculty
     */
    private String faculty;
    /**
     * The user's ID used for login
     */
    private String userID;
    /**
     * The user's password which will be changed after first login
     */
    private String password;
    /**
     * A flag to indicate whether the user is logging in for the first time or not
     */
    private boolean firstLogin;

    /**
     * Creates a new User object with the specifies parameters
     * @param name The name of the user
     * @param email The email address of user
     * @param faculty The faculty user belongs to
     */
    public User(String name, String email, String faculty) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
    }

    /**
     * Gets the name of the user
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email address of the user
     * @return The email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the faculty of the user
     * @return The name of the faculty user belongs to
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the UserID of the user and checks for the correct user ID
     */
    public void setUserID() {
        // Check if the email is not null and contains the "@" symbol
        if (email != null && email.contains("@")) {
            // Split the email using "@" as a delimiter and return the first part
            userID = email.split("@")[0];
        } else {
            // Handle the case where the email is invalid or doesn't contain "@"
            System.out.println("Invalid email format");
        }
    }

    /**
     * Gets the userID of the user
     * @return The userID of the user
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the password for user's account
     * @param newPassword
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Gets the password of the user
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Facilitates the process of changing the user's password
     */
    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        boolean passwordMatch;
        do {
            System.out.println("Enter your new password: ");
            String newPassword = scanner.nextLine();
            if (newPassword.equals(getPassword())) {
                System.out.println("Please choose a different password than your current one.");
                passwordMatch = true;
            } else {
                setPassword(newPassword);
                System.out.println("New password is set!\n");
                passwordMatch = false;
            }
        } while (passwordMatch);
    }

    /**
     * Sets the status of the flag for a user's first login
     * @param firstLogin
     */
    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    /**
     * Gets the status of the flag for a user's first login
     * @return Flag of firstlogin
     */
    public boolean getFirstLogin() {
        return firstLogin;
    }
}

