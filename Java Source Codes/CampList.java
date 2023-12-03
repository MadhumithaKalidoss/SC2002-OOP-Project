import java.util.ArrayList;
import java.util.Date;

/**
 * The {@code CampList} class represents a list of camps, providing methods for creating, editing, and managing camp details.
 * It maintains a master list of {@link Camp} objects and allows operations such as creating, updating, and deleting camps.
 * <p>
 * Camp details can be modified only if the camp has no registered students or camp committee members. Attempts to modify
 * details of a camp with registered participants will result in a warning message.
 * <p>
 * The class also provides methods for retrieving the master list of camp objects.
 *
 */
public class CampList {

    /**
     * The master list for all the camps.
     */
    private ArrayList<Camp> campList;

    /**
     * Constructs a new {@code CampList} object with an empty master list.
     */
    public CampList() {
        this.campList = new ArrayList<>();
    }

    /**
     * Creates a new camp and adds it to the master list.
     *
     * @param campName               The name of the camp.
     * @param startDate              The starting date of the camp.
     * @param endDate                The ending date of the camp.
     * @param registrationClosingDate The closing date for camp registration.
     * @param userGroup              The targeted user group (e.g., faculty or whole school).
     * @param location               The location where the camp will take place.
     * @param totalSlots             The total number of attendee slots available.
     * @param campCommitteeSlots     The maximum number of committee slots (up to 10).
     * @param description            A brief description of the camp.
     * @param staffInCharge          The staff member in charge of the camp.
     * @param listOfCampCommittee    The list of students forming the camp committee.
     * @param listOfAttendees        The list of students registered as attendees.
     * @param visibilityToggle       The visibility status of the camp.
     */
    public void createCamp(String campName, Date startDate, Date endDate, Date registrationClosingDate,
                           String userGroup, String location, int totalSlots, int campCommitteeSlots,
                           String description, Staff staffInCharge, ArrayList<Student> listOfCampCommittee,
                           ArrayList<Student> listOfAttendees, boolean visibilityToggle) {

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
        newCamp.setStaffInCharge(staffInCharge);
        newCamp.setListOfCampCommittee(listOfCampCommittee);
        newCamp.setListOfAttendees(listOfAttendees);
        newCamp.setVisibilityToggle(visibilityToggle);

        campList.add(newCamp);
    }

    /**
     * Updates the name of the camp if it has no registered students or camp committee members.
     *
     * @param camp     The camp to be updated.
     * @param campName The new name for the camp.
     */
    public void updateCampName(Camp camp, String campName) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setCampName(campName);
        } else {
            System.out.println("Camp has registered students!");
        }
    }

    /**
     * Updates the start date of the camp if it has no registered students or camp committee members.
     *
     * @param camp     The camp to be updated.
     * @param startDate The new start date for the camp.
     */
    public void updateStartDate(Camp camp, Date startDate) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setStartDate(startDate);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the end date of the camp if it has no registered students or camp committee members.
     *
     * @param camp   The camp to be updated.
     * @param endDate The new end date for the camp.
     */
    public void updateEndDate(Camp camp, Date endDate) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setEndDate(endDate);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the registration closing date of the camp if it has no registered students or camp committee members.
     *
     * @param camp                   The camp to be updated.
     * @param registrationClosingDate The new registration closing date for the camp.
     */
    public void updateRegistrationClosingDate(Camp camp, Date registrationClosingDate) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setRegistrationClosingDate(registrationClosingDate);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the user group of the camp if it has no registered students or camp committee members.
     *
     * @param camp      The camp to be updated.
     * @param userGroup The new user group for the camp.
     */
    public void updateUserGroup(Camp camp, String userGroup) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setUserGroup(userGroup);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the location of the camp if it has no registered students or camp committee members.
     *
     * @param camp     The camp to be updated.
     * @param location The new location for the camp.
     */
    public void updateLocation(Camp camp, String location) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setLocation(location);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the total slots for attendees for the camp if it has no registered students or camp committee members.
     *
     * @param camp       The camp to be updated.
     * @param totalSlots The new total slots for attendees for the camp.
     */
    public void updateTotalSlots(Camp camp, int totalSlots) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setTotalSlots(totalSlots);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the camp committee slots of the camp if it has no registered students or camp committee members.
     *
     * @param camp              The camp to be updated.
     * @param campCommitteeSlots The new camp committee slots for the camp.
     */
    public void updateCampCommitteeSlots(Camp camp, int campCommitteeSlots) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setCampCommitteeSlots(campCommitteeSlots);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the description of the camp if it has no registered students or camp committee members.
     *
     * @param camp        The camp to be updated.
     * @param description The new description for the camp.
     */
    public void updateDescription(Camp camp, String description) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setDescription(description);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Updates the visibility toggle of the camp if it has no registered students or camp committee members.
     *
     * @param camp            The camp to be updated.
     * @param visibilityToggle The new visibility toggle for the camp.
     */
    public void updateVisibilityToggle(Camp camp, boolean visibilityToggle) {
        if (camp.getListOfAttendees().isEmpty() && camp.getListOfCampCommittee().isEmpty()) {
            camp.setVisibilityToggle(visibilityToggle);
        } else {
            System.out.println("You cannot edit the details of this camp as it already has registered students!");
        }
    }

    /**
     * Deletes a camp from the master list.
     *
     * @param camp The camp to be deleted.
     */
    public void deleteCamp(Camp camp) {
        campList.remove(camp);
    }

    /**
     * Retrieves the master array list of camp objects.
     *
     * @return The master array list of camp objects.
     */
    public ArrayList<Camp> getCampList() {
        return campList;
    }
}


