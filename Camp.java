package sc2002project;

import java.util.Date;
import java.util.ArrayList;

public class Camp {

	private String campName;
	private Date startDate;
	private Date endDate;
	private Date registrationClosingDate;
	private String userGroup; //this group is open to own school or whole NTU
	private String location;
	private int totalSlots;
	private int campCommitteeSlots; //maximum 10 slots
	private String description;
	private String staffinCharge;
	private ArrayList<CampCommittee> listofCampCommittee;
	private ArrayList<Student> listofAttendees;
	private boolean visibilityToggle;
	
	public Camp(String campName, Date startdate, Date enddate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffinCharge, ArrayList<CampCommittee> listofCampCommittee, ArrayList<Student> listofAttendees, boolean visibilityToggle) {
		this.campName = campName;
		this.startDate = startdate;
		this.endDate = enddate;
		this.registrationClosingDate = registrationClosingDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		this.campCommitteeSlots = campCommitteeSlots;
		this.description = description;
		this.staffinCharge = staffinCharge;
		this.listofCampCommittee = listofCampCommittee;
		this.listofAttendees = listofAttendees;
		this.visibilityToggle = visibilityToggle;
	}
	
	public Camp() {
		this.campName = null;
		this.startDate = null;
		this.endDate = null;
		this.registrationClosingDate = null;
		this.userGroup = null;
		this.location = null;
		this.totalSlots = 0;
		this.campCommitteeSlots = 0;
		this.description = null;
		this.staffinCharge = null;
		this.listofCampCommittee = null;
		this.listofAttendees = null;
		this.visibilityToggle = false;
	}

	public String getCampName() {
		return this.campName;
	}

	/**
	 * 
	 * @param campName
	 */
	public void setCampName(String campName) {
		this.campName = campName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @param dates
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * 
	 * @param dates
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRegistrationClosingDate() {
		return this.registrationClosingDate;
	}

	/**
	 * 
	 * @param registrationClosingDate
	 */
	public void setRegistrationClosingDate(Date registrationClosingDate) {
		this.registrationClosingDate = registrationClosingDate;
	}

	public String getUserGroup() {
		return this.userGroup;
	}

	/**
	 * 
	 * @param userGroup
	 */
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public int getTotalSlots() {
		return this.totalSlots;
	}

	/**
	 * 
	 * @param totalSlots
	 */
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	public int getCampCommitteeSlots() {
		return this.campCommitteeSlots;
	}

	/**
	 * 
	 * @param campCommitteeSlots
	 */
	public void setCampCommitteeSlots(int campCommitteeSlots) {
		if (campCommitteeSlots > 10) {
			System.out.println("You cannot create more than 10 slots for Camp Committee!");
		}
		else {
			this.campCommitteeSlots = campCommitteeSlots;
		}
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStaffinCharge() {
		return this.staffinCharge;
	}

	/**
	 * 
	 * @param staffinCharge
	 */
	public void setStaffinCharge(String staffinCharge) {
		this.staffinCharge = staffinCharge;
	}

	public ArrayList<CampCommittee> getListofCampCommittee() {
		return this.listofCampCommittee;
	}

	/**
	 * 
	 * @param listofCampCommittee
	 */
	public void setListofCampCommittee(ArrayList<CampCommittee> listofCampCommittee) {
		this.listofCampCommittee = listofCampCommittee;
	}

	public ArrayList<Student> getListofAttendees() {
		return this.listofAttendees;
	}

	/**
	 * 
	 * @param listofStudents
	 */
	public void setListofAttendees(ArrayList<Student> listofAttendees) {
		this.listofAttendees = listofAttendees;
	}
	
	public boolean getVisibilityToggle() {
		return this.visibilityToggle;
	}
	
	public void setVisibilityToggle(boolean visibilityToggle) {
		this.visibilityToggle = visibilityToggle;
	}

}
