import java.text.SimpleDateFormat;
/**
 * The {@code StudentCampViewService} class provides methods for viewing available camps and registered camps for a student.
 * It retrieves details about camps based on the student's profile and camp visibility.
 */
public class StudentCampViewService {
    private CampList campList;
    ObjectFinder objectFinder = new ObjectFinder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, EEE");

    /**
     * Initializes a {@code StudentCampViewService} with a provided camp list.
     *
     * @param campList The {@link CampList} containing available camps.
     */
    public StudentCampViewService(CampList campList) {
        this.campList = campList;
    }

    /**
     * Displays details of available camps suitable for the student based on their faculty.
     *
     * @param student The {@link Student} for whom available camp details are displayed.
     */
    public void viewAvailableCampDetails(Student student) {
        boolean haveAvailableCamps = false;

        String studentFaculty = student.getFaculty();

        for (Camp camp : campList.getCampList()) {

            if (studentFaculty.equalsIgnoreCase(camp.getUserGroup()) || ("NTU").equalsIgnoreCase(camp.getUserGroup())) {
                if (camp.getVisibilityToggle()) {
                    int remainingAttendeeSlots = camp.getTotalSlots() - camp.getListOfAttendees().size();
                    int remainingCampCommitteeSlots = camp.getCampCommitteeSlots() - camp.getListOfCampCommittee().size();

                    System.out.println("Camp Name: " + camp.getCampName());
                    System.out.println("Description: " + camp.getDescription());
                    System.out.println("Location: " + camp.getLocation());
                    System.out.println("Start Date: " + dateFormat.format(camp.getStartDate()));
                    System.out.println("End Date: " + dateFormat.format(camp.getEndDate()));
                    System.out.println("Registration Closing Date: " + dateFormat.format(camp.getRegistrationClosingDate()));
                    System.out.println("Remaining Slots for Attendees: " + remainingAttendeeSlots);
                    System.out.println("Remaining Slots for Camp Committee Members: " + remainingCampCommitteeSlots);
                    System.out.println("------------");
                    haveAvailableCamps = true;

                }
            }
        }
        if (!haveAvailableCamps) {
            System.out.println("There are no available camps");
        }
        System.out.println();
    }

    /**
     * Displays camps for which the student is registered along with their roles in those camps.
     *
     * @param student The {@link Student} whose registered camps are to be displayed.
     */
    public void viewRegisteredCamps(Student student) {
        boolean hasRegisteredCamps = false;

        for (Camp camp : campList.getCampList()) {
            String role = objectFinder.findStudentRoleInCamp(student, camp);

            // If the role is 'Attendee' or 'Camp Committee Member', print camp name and the role
            if (role.equals("Attendee") || role.equals("Camp Committee Member")) {
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Role: " + role);
                System.out.println("------------");
                hasRegisteredCamps = true;
            }
        }

        if (!hasRegisteredCamps) {
            System.out.println("You are not registered for any camps.");
        }
        System.out.println();
    }

}