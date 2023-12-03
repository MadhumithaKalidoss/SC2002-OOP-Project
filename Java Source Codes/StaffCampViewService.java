import java.text.SimpleDateFormat;
import java.util.Scanner;
/**
 * The StaffCampViewService class provides methods for staff members to view details of camps,
 * including a list of all camps, camps created by the staff, and details of a specific camp.
 * It also allows staff to view the list of registered students and camp committee members for a specific camp.
 */
public class StaffCampViewService {
    private CampList campList;
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a StaffCampViewService with the specified CampList.
     *
     * @param campList The CampList to be managed by the StaffCampViewService.
     */
    public StaffCampViewService(CampList campList) {
        this.campList = campList;
    }

    /**
     * Displays a list of all camps.
     */
    public void viewAllCamps() {

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps.");
            System.out.println();
            return;
        }

        int campNum = 1;
        System.out.println("List of All Camps:");

        for (Camp camp : campList.getCampList()) {
            System.out.println(campNum + ") " + camp.getCampName());
            ++campNum;
        }
        System.out.println();
    }

    /**
     * Displays a list of camps created by the specified staff member.
     *
     * @param staff The staff member whose camps are to be displayed.
     */
    public void viewOwnCamps(Staff staff) {

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps.");
            System.out.println();
            return;
        }

        int campNum = 1;
        System.out.println("List of camps under " + staff.getName() + ":");

        if (objectFinder.getCampsByStaffID(staff.getUserID(), campList).isEmpty()) {
            System.out.println("You have not created any camps.");
            System.out.println();
            return;
        }

        for (Camp camp : campList.getCampList()) {
            if (staff != null && camp.getStaffInCharge() == staff) {
                System.out.println(campNum + ") " + camp.getCampName());
                ++campNum;
            }
        }
        System.out.println();
    }

    /**
     * Displays detailed information about a specific camp.
     *
     * @param staff The staff member requesting the camp details.
     */
    public void viewCampDetails(Staff staff) {

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps.");
            System.out.println();
            return;
        }

        System.out.println("Enter the name of the camp that you want to view details of:");
        String campName = scanner.nextLine();

        Camp camp = objectFinder.findCampByName(campName, campList);

        if (camp != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, EEE");

            if (camp.getStaffInCharge().getUserID().equals(staff.getUserID())) {

                System.out.println("Details of " + camp.getCampName() + ":");
                System.out.println("1) Description: " + camp.getDescription());
                System.out.println("2) Location: " + camp.getLocation());
                System.out.println("3) User Group: " + camp.getUserGroup());
                System.out.println("4) Registration Closing Date: " + dateFormat.format(camp.getRegistrationClosingDate()));
                System.out.println("5) Start Date: " + dateFormat.format(camp.getStartDate()));
                System.out.println("6) End Date: " + dateFormat.format(camp.getEndDate()));
                System.out.println("7) Total Slots for Attendees: " + camp.getTotalSlots());
                System.out.println("8) Total Slots for Camp Committee Members: " + camp.getCampCommitteeSlots());
                System.out.println("9) Number of registered Attendees: " + camp.getListOfAttendees().size());
                System.out.println("10) Number of registered Camp Committee: " + camp.getListOfCampCommittee().size());
                System.out.println("11) Visibility to Students: " + (camp.getVisibilityToggle() ? "On" : "Off"));
                System.out.println();
            } else {
                System.out.println("You are not in-charge of this camp. You are not allowed to view the details of this camp.");
                System.out.println();
            }
        } else {
            System.out.println("Invalid Camp Name. Please check the name of the camp using 'View list of all Camps created by you'.");
            System.out.println();
        }
    }

    /**
     * Displays the list of registered students and camp committee members for a specific camp.
     *
     * @param staff The staff member requesting the list of registered students.
     */
    public void viewRegisteredStudentsForCamp(Staff staff) {
        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps.");
            System.out.println();
            return;
        }

        System.out.println("Enter the name of the camp that you want to view the list of registered student for:");
        String campName = scanner.nextLine();

        Camp camp = objectFinder.findCampByName(campName, campList);

        if (camp != null) {

            if (camp.getStaffInCharge().getUserID().equals(staff.getUserID())) {
                System.out.println("Camp Attendees:");
                if (camp.getListOfAttendees().isEmpty()) {
                    System.out.println("No attendees registered for this camp.");
                } else {
                    for (Student attendee : camp.getListOfAttendees()) {
                        System.out.println(attendee.getName());
                    }
                }

                System.out.println();

                System.out.println("Camp Committee Members:");
                if (camp.getListOfCampCommittee().isEmpty()) {
                    System.out.println("No committee members registered for this camp.");
                } else {
                    for (Student committeeMember : camp.getListOfCampCommittee()) {
                        System.out.println(committeeMember.getName());
                    }
                }

                System.out.println();

            } else {
                System.out.println("You are not in-charge of this camp. You are not allowed to view the details of this camp.");
                System.out.println();
            }
        } else {
            System.out.println("Invalid Camp Name. Please check the name of the camp using 'View list of all Camps created by you'.");
            System.out.println();
        }
    }
}
