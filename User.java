package sc2002project;

public class User {
	
	protected String name;
    protected String email;
    protected String faculty;
	protected String userID;
	protected String password;
    
    
    public User(String name, String email, String faculty) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getFaculty() {
        return faculty;
    } 
    
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
    
    public String getUserID() {
    	return userID;
    }
    
    public void setPassword(String newPassword) {
		this.password = newPassword;
	}

    public String getPassword() {
        return password;
    }
   
}
