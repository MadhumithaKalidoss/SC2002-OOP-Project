import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
/**
 * The StaffMenu class represents the menu and functionality available to staff members within the application.
 * It allows staff members to perform various tasks related to camp management, enquiries, suggestions, and report generation.
 */
public class StaffMenu {
    /**
     * Displays the staff menu and handles staff interactions with the system.
     * @exception InputMismatchException for invalid integer inputs during menu selection.
     * @param staff             The staff member accessing the menu.
     * @param studentList       The list of all students in the system.
     * @param campList          The list of all camps in the system.
     * @param enquiryList       The list of all enquiries in the system.
     * @param suggestionList    The list of all suggestions in the system.
     * @return                  A boolean value indicating whether the staff member is still logged in (true) or has logged out (false).
     */
    public boolean staffMenu(Staff staff, StudentList studentList, CampList campList, EnquiryList enquiryList, SuggestionList suggestionList) {
        Scanner scanner = new Scanner(System.in);

        CampManager campManager = new CampManager(campList);
        StaffCampViewService staffCampViewService = new StaffCampViewService(campList);
        StaffAndCCMEnquiryManager staffAndCCMEnquiryManager = new StaffAndCCMEnquiryManager(enquiryList);
        StaffSuggestionManager staffSuggestionManager = new StaffSuggestionManager(suggestionList);
        EnquiryReportService enquiryReportService = new EnquiryReportService(enquiryList);
        ObjectFinder objectFinder = new ObjectFinder();
        ArrayList<Camp> campsByStaff = objectFinder.getCampsByStaffID(staff.getUserID(), campList);
        StaffCampReportService staffCampReportService = new StaffCampReportService(campsByStaff);
        PerformanceReportService performanceReportService = new PerformanceReportService(staff, campList);

        while (true) {
            try {
                System.out.println("Staff Menu:");
                System.out.println("1. Change Password");
                System.out.println("2. Create a Camp");
                System.out.println("3. Edit a Camp");
                System.out.println("4. Delete a Camp");
                System.out.println("5. Toggle Camp Visibility");
                System.out.println("6. View list of all Camps");
                System.out.println("7. View list of all Camps created by you");
                System.out.println("8. View details of a Camp created by you");
                System.out.println("9. View list of registered students in a Camp created by you");
                System.out.println("10. View Enquiries");
                System.out.println("11. Reply to Enquiries");
                System.out.println("12. View Suggestions");
                System.out.println("13. Approve Suggestions");
                System.out.println("14. Generate Camp Reports");
                System.out.println("15. Generate Performance Reports");
                System.out.println("16. Generate Enquiry Reports");
                System.out.println("17. Logging out of Application");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        staff.changePassword();
                        break;
                    case 2:
                        campManager.createNewCamp(staff);
                        break;
                    case 3:
                        campManager.editExistingCamp(staff);
                        break;
                    case 4:
                        campManager.deleteCamp(staff);
                        break;
                    case 5:
                        campManager.toggleCampVisibility(staff);
                        break;
                    case 6:
                        staffCampViewService.viewAllCamps();
                        break;
                    case 7:
                        staffCampViewService.viewOwnCamps(staff);
                        break;
                    case 8:
                        staffCampViewService.viewCampDetails(staff);
                        break;
                    case 9:
                        staffCampViewService.viewRegisteredStudentsForCamp(staff);
                        break;
                    case 10:
                        staffAndCCMEnquiryManager.viewEnquiriesAsStaff(staff, campList);
                        break;
                    case 11:
                        staffAndCCMEnquiryManager.submitAnswer(staff, campList);
                        break;
                    case 12:
                        staffSuggestionManager.viewSuggestions(staff, campList);
                        break;
                    case 13:
                        staffSuggestionManager.submitAnswer(staff, campList, studentList);
                        break;
                    case 14:
                        staffCampReportService.generateReportOfCampByStaff(staff, "Report.xlsx");
                        break;
                    case 15:
                        performanceReportService.generatePerformanceReportForSelectedCamp("PerformanceReport.xlsx");
                        break;
                    case 16:
                        enquiryReportService.generateEnquiryReport(staff, campList);
                        break;
                    case 17:
                        System.out.println("Logging out...\n");
                        return false;
                        //isLoggedIn = true;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
