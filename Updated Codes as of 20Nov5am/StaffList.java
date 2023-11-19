import java.util.ArrayList;

public class StaffList {
	
	private ArrayList<Staff> staffList;

	public StaffList() {
		staffList = new ArrayList<Staff>();
	}

	public void addStaff(Staff staff) {
		staffList.add(staff);
	}
	
	public void setUserIdForEmptyUsers() {
        for (Staff staff : staffList) {
            if (staff.getUserID() == null || staff.getUserID().isEmpty()) {
            	staff.setUserID();
            }
        }
    }

	public void setFirstLogin() {
		for (Staff staff : staffList) {
			staff.setFirstLogin(true);
		}
	}
	
	public void setDefaultPassword() {
		for (Staff staff : staffList) {
            if (staff.getPassword() == null || staff.getPassword().isEmpty()) {
            	staff.setPassword("password");
            }
        }
	}
	
	public void changePassword(String userId, String newPassword) {
		ObjectFinder objectFinder = new ObjectFinder();
		Staff staff = objectFinder.findStaffByUserId(userId, staffList);
        if (staff != null) {
        	staff.setPassword(newPassword);
        }
	}
	
	public ArrayList<Staff> getStaffList() {
	    return staffList;
	}

	public Staff findStaffById(String userId) {
		for (Staff staffMember : staffList) {
			if (staffMember.getUserID().equalsIgnoreCase(userId)) {
				return staffMember;
			}
		}
		return null;
	}

}
