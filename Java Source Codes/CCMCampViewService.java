import java.text.SimpleDateFormat;
/**
 * The {@code CCMCampViewService} class provides a service for viewing camp details specifically designed for Camp Committee Members (CCM).
 * Camp Committee Members can use this service to view details of camps where they are registered as committee members.
 * The service utilizes the {@link ObjectFinder} to determine the role of a {@link Student} in a given {@link Camp}.
 * Camp details, including name, description, staff-in-charge, location, start date, end date, and registration closing date,
 * are displayed for each camp where the student holds the role of a Camp Committee Member.
 * The date format for displaying dates is "dd MMM yyyy, EEE".
 *
 * <p>Usage Example:</p>
 * <pre>
 * {@code
 * CampList campList = new CampList();
 * CCMCampViewService viewService = new CCMCampViewService(campList);
 * viewService.viewCampDetails(student);
 * }
 * </pre>
 */
public class CCMCampViewService {
    private CampList campList;
    ObjectFinder objectFinder = new ObjectFinder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, EEE");
    /**
     * Constructs a {@code CCMCampViewService} instance with the provided {@link CampList}.
     *
     * @param campList The {@link CampList} containing the camps to be viewed.
     */
    public CCMCampViewService(CampList campList) {
        this.campList = campList;
    }
    /**
     * Displays details of camps for which the provided {@link Student} is registered as a Camp Committee Member.
     * The method iterates through each camp in the camp list, checks the student's role using {@link ObjectFinder},
     * and prints relevant details for each camp where the student is a Camp Committee Member.
     *
     * @param student The {@link Student} for whom camp details are to be displayed.
     */
    public void viewCampDetails(Student student) {
        for (Camp camp : campList.getCampList()) {

            String role = objectFinder.findStudentRoleInCamp(student, camp);

            if (role.equals("Camp Committee Member")) {
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Description: " + camp.getDescription());
                System.out.println("Staff-In-Charge: " + camp.getStaffInCharge().getName());
                System.out.println("Location: " + camp.getLocation());
                System.out.println("Start Date: " + dateFormat.format(camp.getStartDate()));
                System.out.println("End Date: " + dateFormat.format(camp.getEndDate()));
                System.out.println("Registration Closing Date: " + dateFormat.format(camp.getRegistrationClosingDate()));
                System.out.println("------------");
            }
        }
        System.out.println();
    }
}
