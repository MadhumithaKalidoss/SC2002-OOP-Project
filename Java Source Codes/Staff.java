/**
 * Represents a Staff member, extending the functionality of a User in the system.
 */
public class Staff extends User{
    /**
     * Constructor for creating a Staff object.
     * @param name The name of the staff member.
     * @param email The email address of the staff member.
     * @param faculty The faculty the staff member belongs to.
     */
    public Staff(String name, String email, String faculty) {
        super(name, email, faculty);
    }

}
