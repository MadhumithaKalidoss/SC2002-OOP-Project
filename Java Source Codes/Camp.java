import java.util.Date;
import java.util.ArrayList;

/**
 * The {@code Camp} class represents an event or program organized by a school or a specific faculty.
 * It includes information such as the camp name, start and end dates, registration details, user group who is allowed to attend the camp,
 * location, available slots, committee slots, description, staff in charge, committee members, attendees,
 * and visibility status.
 * <p>
 * The class provides methods to access and modify these attributes, ensuring proper encapsulation and control
 * over the camp-related information.
 *
 */
public class Camp {

    /**
     * The name of the camp.
     */
    private String campName;

    /**
     * The starting date of the camp.
     */
    private Date startDate;

    /**
     * The ending date of the camp.
     */
    private Date endDate;

    /**
     * The closing date for camp registration.
     */
    private Date registrationClosingDate;

    /**
     * The targeted user group (e.g., faculty or whole school).
     */
    private String userGroup;

    /**
     * The location where the camp will take place.
     */
    private String location;

    /**
     * The total number of attendee slots available.
     */
    private int totalSlots;

    /**
     * The total number of committee slots (up to 10).
     */
    private int campCommitteeSlots;

    /**
     * A brief description of the camp.
     */
    private String description;

    /**
     * The staff member in charge of the camp.
     */
    private Staff staffInCharge;

    /**
     * The list of students forming the camp committee.
     */
    private ArrayList<Student> listOfCampCommittee;

    /**
     * The list of students registered as attendees.
     */
    private ArrayList<Student> listOfAttendees;

    /**
     * The visibility status of the camp.
     */
    private boolean visibilityToggle;

    /**
     * Constructs a new {@code Camp} object with the specified parameters.
     *
     * @param campName               The name of the camp.
     * @param startDate              The starting date of the camp.
     * @param endDate                The ending date of the camp.
     * @param registrationClosingDate The closing date for camp registration.
     * @param userGroup              The targeted user group (e.g., faculty or whole school).
     * @param location               The location where the camp will take place.
     * @param totalSlots             The total number of attendee slots available.
     * @param campCommitteeSlots     The total number of committee slots (up to 10).
     * @param description            A brief description of the camp.
     * @param staffInCharge          The staff creating the camp.
     * @param listOfCampCommittee    The list of students forming the camp committee.
     * @param listOfAttendees        The list of students registered as attendees.
     * @param visibilityToggle       The visibility status of the camp.
     */
    public Camp(String campName, Date startDate, Date endDate, Date registrationClosingDate, String userGroup,
                String location, int totalSlots, int campCommitteeSlots, String description, Staff staffInCharge,
                ArrayList<Student> listOfCampCommittee, ArrayList<Student> listOfAttendees, boolean visibilityToggle) {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.listOfCampCommittee = listOfCampCommittee;
        this.listOfAttendees = listOfAttendees;
        this.visibilityToggle = visibilityToggle;
    }

    /**
     * Constructs a default {@code Camp} object with null or default values for attributes.
     */
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
        this.staffInCharge = null;
        this.listOfCampCommittee = null;
        this.listOfAttendees = null;
        this.visibilityToggle = false;
    }

    /**
     * Gets the name of the camp.
     *
     * @return The name of the camp.
     */
    public String getCampName() {
        return this.campName;
    }

    /**
     * Sets the name of the camp.
     *
     * @param campName The name of the camp.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Gets the starting date of the camp.
     *
     * @return The starting date of the camp.
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Sets the starting date of the camp.
     *
     * @param startDate The starting date of the camp.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the ending date of the camp.
     *
     * @return The ending date of the camp.
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * Sets the ending date of the camp.
     *
     * @param endDate The ending date of the camp.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the closing date for camp registration.
     *
     * @return The closing date for camp registration.
     */
    public Date getRegistrationClosingDate() {
        return this.registrationClosingDate;
    }

    /**
     * Sets the closing date for camp registration.
     *
     * @param registrationClosingDate The closing date for camp registration.
     */
    public void setRegistrationClosingDate(Date registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    /**
     * Gets the targeted user group for the camp.
     *
     * @return The targeted user group.
     */
    public String getUserGroup() {
        return this.userGroup;
    }

    /**
     * Sets the targeted user group for the camp.
     *
     * @param userGroup The targeted user group.
     */
    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * Gets the location where the camp will take place.
     *
     * @return The location of the camp.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets the location where the camp will take place.
     *
     * @param location The location of the camp.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the total number of attendee slots available.
     *
     * @return The total number of attendee slots.
     */
    public int getTotalSlots() {
        return this.totalSlots;
    }

    /**
     * Sets the total number of attendee slots available.
     *
     * @param totalSlots The total number of attendee slots.
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    /**
     * Gets the maximum number of committee slots for the camp.
     *
     * @return The maximum number of committee slots.
     */
    public int getCampCommitteeSlots() {
        return this.campCommitteeSlots;
    }

    /**
     * Sets the maximum number of committee slots for the camp.
     * If the provided value is greater than 10, a message is printed.
     *
     * @param campCommitteeSlots The maximum number of committee slots.
     */
    public void setCampCommitteeSlots(int campCommitteeSlots) {
        if (campCommitteeSlots > 10) {
            System.out.println("You cannot create more than 10 slots for Camp Committee!");
        } else {
            this.campCommitteeSlots = campCommitteeSlots;
        }
    }

    /**
     * Gets a brief description of the camp.
     *
     * @return A brief description of the camp.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a brief description of the camp.
     *
     * @param description A brief description of the camp.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the staff member in charge of the camp.
     *
     * @return The staff member in charge of the camp.
     */
    public Staff getStaffInCharge() {
        return this.staffInCharge;
    }

    /**
     * Sets the staff member in charge of the camp.
     *
     * @param staffInCharge The staff member in charge of the camp.
     */
    public void setStaffInCharge(Staff staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    /**
     * Gets the list of students forming the camp committee.
     *
     * @return The list of students forming the camp committee.
     */
    public ArrayList<Student> getListOfCampCommittee() {
        return this.listOfCampCommittee;
    }

    /**
     * Sets the list of students forming the camp committee.
     *
     * @param listOfCampCommittee The list of students forming the camp committee.
     */
    public void setListOfCampCommittee(ArrayList<Student> listOfCampCommittee) {
        this.listOfCampCommittee = listOfCampCommittee;
    }

    /**
     * Gets the list of students registered as attendees for the camp.
     *
     * @return The list of students registered as attendees.
     */
    public ArrayList<Student> getListOfAttendees() {
        return this.listOfAttendees;
    }

    /**
     * Sets the list of students registered as attendees for the camp.
     *
     * @param listOfAttendees The list of students registered as attendees.
     */
    public void setListOfAttendees(ArrayList<Student> listOfAttendees) {
        this.listOfAttendees = listOfAttendees;
    }

    /**
     * Checks the visibility status of the camp.
     *
     * @return {@code true} if the camp is visible; {@code false} otherwise.
     */
    public boolean getVisibilityToggle() {
        return this.visibilityToggle;
    }

    /**
     * Sets the visibility status of the camp.
     *
     * @param visibilityToggle The visibility status of the camp.
     */
    public void setVisibilityToggle(boolean visibilityToggle) {
        this.visibilityToggle = visibilityToggle;
    }
}

