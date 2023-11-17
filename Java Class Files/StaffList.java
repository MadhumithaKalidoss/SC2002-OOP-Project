package sc2002project;

import java.util.ArrayList;

public class StaffList {
	
	private ArrayList<Staff> staffList;
		
	public StaffList() {
		staffList = new ArrayList<Staff>();
	}
	
	public void setUserIdForEmptyUsers() {
        for (Staff staff : staffList) {
            if (staff.getUserID() == null || staff.getUserID().isEmpty()) {
            	staff.setUserID();
            }
        }
    }
	
	public void setInitialLoginNum() {
        for (Staff staff : staffList) {
            if (staff.getLoginNum() != 0) {
            	staff.setLoginNum(0);
            }
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

}
