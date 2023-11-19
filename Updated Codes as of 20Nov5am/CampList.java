import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class CampList {

	private ArrayList<Camp> campList;//Master list for all the camps

	//Constructor
	public CampList() {
        campList = new ArrayList<Camp>();
    }

	//Camp Management Service
	// Create Camp (1st Method)
	public void CreateCamp(String campName, Date startDate, Date endDate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, Staff staffinCharge, ArrayList<CampCommittee> listofCampCommittee, ArrayList<Student> listofAttendees, boolean visibilityToggle){
		
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


	//Edit Camp (2nd Method)
	//Case 1 - Edit camp
	public void updateCampName(Camp camp, String campName){
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
		{
			camp.setCampName(campName);
		}
		else {
			System.out.println("Camp has registered students!");
		}
	}

	//Case 2 - Edit Camp
	public void updateStartDate(Camp camp, Date startDate) {
        if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
        {
        	camp.setStartDate(startDate);
        }
        else {
        	System.out.println("Camp has registered students!");
        }
    }


	//Case 3 - Edit Camp
	public void updateEndDate(Camp camp, Date endDate) {
        if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
        {
        	camp.setEndDate(endDate);
        }
        else {
        	System.out.println("Camp has registered students!");
        }
	}

	//Case 4 - Edit Camp
	public void updateRegistrationClosingDate(Camp camp, Date registrationClosingDate) {
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
        {
        	camp.setRegistrationClosingDate(registrationClosingDate);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}

	//Case 5 - Edit camp
	public void updateUserGroup(Camp camp, String usergroup) {
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
        {
			camp.setUserGroup(usergroup);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}

	//Case 6 - Edit camp
	public void updateLocation(Camp camp, String location) {
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
        {
			camp.setLocation(location);
        }
		else {
        	System.out.println("Camp has registered students!");
        }

	}

	//Case 7 -Edit camp
	public void updateTotalSlots(Camp camp, int totalSlots) {
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
        {
			camp.setTotalSlots(totalSlots);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}

	//Case 8 - Edit camp
	public void updateCampComitteeSlots(Camp camp, int campComitteeSlots) {
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
		{
			camp.setCampCommitteeSlots(campComitteeSlots);
		}
		else {
			System.out.println("Camp has registered students!");
		}
	}

	//Case 8 - Edit camp
	public void updateDescription(Camp camp, String Description) {
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
		{
			camp.setDescription(Description);
		}
		else {
			System.out.println("Camp has registered students!");
		}
	}

	//Case 9 - Edit camp
	/*public void updateStaffinCharge(Camp camp, Staff staffinCharge) {
		if (!camp.getListofAttendees().isEmpty() || !camp.getListofCampCommittee().isEmpty()) {
			camp.setStaffinCharge(staffinCharge);
		} else {
			System.out.println("Camp has registered students or committee members!");
		}
	}*/

	//Delete camp (3rd Method)
	public void deleteCamp(Camp camp) {
		campList.remove(camp);
	}

	public void updateVisibilityToggle(Camp camp, boolean visibilityToggle) {
		if (camp.getListofAttendees().isEmpty() && camp.getListofCampCommittee().isEmpty())
        {
        	camp.setVisibilityToggle(visibilityToggle);
        }
		else {
        	System.out.println("Camp has registered students!");
        }
	}

	//Camp view service
	// View all camps
	public ArrayList<Camp> viewAllCamps() {
		return campList;
	}

	public ArrayList<Camp> getCampList() {
		return campList;
	}
}


/*import java.util.ArrayList;
import java.util.Date;

public class CampList {

	private ArrayList<Camp> campList;

	public ArrayList<Camp> getCampList() {
		return campList;
	}
	
	public CampList() {
        campList = new ArrayList<Camp>();
    }
	
	public void addCamp(String campName, Date startDate, Date endDate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, Staff staffinCharge, ArrayList<CampCommittee> listofCampCommittee, ArrayList<Student> listofAttendees, boolean visibilityToggle){
		
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
	
	public void updateListofCampCommittee(Camp camp, ArrayList<CampCommittee> listofCampCommittee) {
		camp.setListofCampCommittee(listofCampCommittee);
	}
	
	
	public void updateListofAttendees(Camp camp, ArrayList<Student> listofAttendees) {
		camp.setListofAttendees(listofAttendees);
	}
	
	public void updateVisibilityToggle(Camp camp, boolean visibilityToggle) {
		if (!camp.getListofAttendees().isEmpty() && !camp.getListofCampCommittee().isEmpty())
        {
        	camp.setVisibilityToggle(visibilityToggle);
        }
		else {
        	System.out.println("Camp has registered students!");
        }

}*/





