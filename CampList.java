package sc2002project;

import java.util.ArrayList;
import java.util.Date;

public class CampList {

	private ArrayList<Camp> campList;

	public ArrayList<Camp> getCampList() {
		return campList;
	}
	
	public CampList() {
        campList = new ArrayList<Camp>();
    }
	
	public void addCamp(String campName, Date startDate, Date endDate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffinCharge, ArrayList<CampCommittee> listofCampCommittee, ArrayList<Student> listofAttendees, boolean visibilityToggle){
		
		Camp newCamp = new Camp();
		
		newCamp.setCampName(campName);
		newCamp.setStartDate(startDate);
		newCamp.setEndDate(endDate);
		newCamp.setRegistrationClosingDate(registrationClosingDate);
		newCamp.setUserGroup(userGroup);
		newCamp.setLocation(location);
		newCamp.setTotalSlots(totalSlots);
		newCamp.setCampCommitteeSlots(campCommitteeSlots);
		newCamp.setDescription(description);
		newCamp.setStaffinCharge(staffinCharge);
		newCamp.setListofCampCommittee(listofCampCommittee);
		newCamp.setListofAttendees(listofAttendees);
		newCamp.setVisibilityToggle(false);
		
		campList.add(newCamp);
	}
	
	public void deleteCamp(Camp camp) {
        campList.remove(camp);
    }
	
	public void updateStartDate(Camp camp, Date startDate) {
        if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
        	camp.setStartDate(startDate);
        }
        else {
        	System.out.println("Camp has registered students!");
        }
    }
	
	public void updateEndDate(Camp camp, Date endDate) {
        if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
        	camp.setEndDate(endDate);
        }
        else {
        	System.out.println("Camp has registered students!");
        }
	}
	
	public void updateRegistrationClosingDate(Camp camp, Date registrationClosingDate) {
		if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
        	camp.setRegistrationClosingDate(registrationClosingDate);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}
	
	public void updateUserGroup(Camp camp, String usergroup) {
		if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
			camp.setUserGroup(usergroup);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}
	
	public void updateLocation(Camp camp, String location) {
		if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
			camp.setLocation(location);
        }
		else {
        	System.out.println("Camp has registered students!");
        }

	}
	
	public void updateDescription(Camp camp, String description) {
		if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
			camp.setDescription(description);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}
	
	/*public void updateListofCampCommittee(Camp camp, ArrayList<CampCommittee> listofCampCommittee) {
		camp.setListofCampCommittee(listofCampCommittee);
	}
	
	
	public void updateListofAttendees(Camp camp, ArrayList<Student> listofAttendees) {
		camp.setListofAttendees(listofAttendees);
	}*/
	
	public void updateVisibilityToggle(Camp camp, boolean visibilityToggle) {
		if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
        	camp.setVisibilityToggle(visibilityToggle);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}

}

