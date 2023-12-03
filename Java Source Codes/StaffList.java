import java.util.ArrayList;

/**
 * Represents a list of staff members with user management operations.
 * Implements the UserList interface to manage staff-specific user operations.
 */
public class StaffList implements UserList{
    /**
     * A list of the staff members
     */
    private ArrayList<Staff> staffList;

    /**
     * Constructs a StaffList object with an empty list of staff members.
     */
    public StaffList() {
        this.staffList = new ArrayList<>();
    }

    /**
     * Adds a staff member to the list of staff members.
     * @param staff The staff member object to be added to the list.
     */
    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    /**
     * Sets user IDs for staff members in the list if the ID is empty or null.
     * Iterates through the staff member list and assigns user IDs to staff members where the ID is missing or empty.
     */
    public void setUserIdForEmptyUsers() {
        for (Staff staff : staffList) {
            if (staff.getUserID() == null || staff.getUserID().isEmpty()) {
                staff.setUserID();
            }
        }
    }

    /**
     * Sets the first login flag for all staff members in the list.
     * Marks all staff members in the list as having completed their first login.
     */
    public void setFirstLogin() {
        for (Staff staff : staffList) {
            staff.setFirstLogin(true);
        }
    }

    /**
     * Sets a default password for staff members in the list if their password is empty or null.
     * Assigns a default password to staff members in the list where the password is missing or empty.
     */
    public void setDefaultPassword() {
        for (Staff staff : staffList) {
            if (staff.getPassword() == null || staff.getPassword().isEmpty()) {
                staff.setPassword("password");
            }
        }
    }

    /**
     * Retrieves the list of staff members.
     * @return The ArrayList containing the list of staff members.
     */
    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    /**
     * Finds a staff member in the list by their user ID.
     * @param userId The user ID of the staff member to find.
     * @return The found Staff object; null if not found.
     */
    public Staff findStaffById(String userId) {
        for (Staff staffMember : staffList) {
            if (staffMember.getUserID().equalsIgnoreCase(userId)) {
                return staffMember;
            }
        }
        return null;
    }

}

