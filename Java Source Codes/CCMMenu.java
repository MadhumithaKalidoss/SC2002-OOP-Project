import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * The {@code CCMMenu} class provides functionalities and features available exclusively to Camp Committee Members (CCMs).
 * It includes operations related to suggestion submission, enquiry management, camp report generation, and more.
 */
public class CCMMenu {
    /**
     * Grants access to Camp Committee features based on the given student's role and associated camps.
     * @exception InputMismatchException handles InputMismatchException for menu choices
     * @exception NullPointerException to manage scenarios where the student is not a camp committee member, ensuring robust execution and user guidance.
     * @param student The {@link Student} accessing the Camp Committee features.
     * @param campList The {@link CampList} containing available camps.
     * @param enquiryList The {@link EnquiryList} containing student enquiries.
     * @param suggestionList The {@link SuggestionList} containing student suggestions.
     */
    public void accessCampCommitteeFeatures(Student student, CampList campList, EnquiryList enquiryList, SuggestionList suggestionList) {

        Scanner scanner = new Scanner(System.in);

        ObjectFinder objectFinder = new ObjectFinder();
        StaffAndCCMEnquiryManager staffAndCCMEnquiryManager = new StaffAndCCMEnquiryManager(enquiryList);
        CCMCampViewService ccmCampViewService = new CCMCampViewService(campList);
        CCMSuggestionManager suggestionManager = new CCMSuggestionManager(suggestionList);
        EnquiryReportService enquiryReportService = new EnquiryReportService(enquiryList);

        String role = null;
        Camp camp= null;
        for (Camp campObject : campList.getCampList()) {
            role = objectFinder.findStudentRoleInCamp(student, campObject);

            if (role.equals("Camp Committee Member")) {
                camp = campObject;
                break;
            }
        }

        if (role.equals("Camp Committee Member") && camp != null) {
            while (true) {
                try {
                    System.out.println("Camp Committee Features:");
                    System.out.println("1. Submit Suggestion");
                    System.out.println("2. Delete Suggestion");
                    System.out.println("3. Edit Suggestion");
                    System.out.println("4. View Suggestions");
                    System.out.println("5. Reply to Enquiries");
                    System.out.println("6. View Enquiries");
                    System.out.println("7. View Camp Details");
                    System.out.println("8. Generate Camp Report");
                    System.out.println("9. Generate Enquiry Report");
                    System.out.println("10. Back");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            suggestionManager.submit(student, campList);
                            break;
                        case 2:
                            suggestionManager.delete(student);
                            break;
                        case 3:
                            suggestionManager.edit(student);
                            break;
                        case 4:
                            suggestionManager.view(student);
                            break;
                        case 5:
                            staffAndCCMEnquiryManager.submitAnswer(student, campList);
                            break;
                        case 6:
                            staffAndCCMEnquiryManager.viewEnquiriesAsCCM(student, camp);
                            break;
                        case 7:
                            ccmCampViewService.viewCampDetails(student);
                            break;
                        case 8:
                            CCMCampReportService CCMCampReportService = new CCMCampReportService();
                            CCMCampReportService.generateCampReport(student, campList, "Report.xlsx");
                            break;
                        case 9:
                            enquiryReportService.generateEnquiryReportCCM(student, camp);
                            break;
                        case 10:
                            System.out.println("Returning to main student menu...");
                            System.out.println();
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                }

            }

        } else {
            System.out.println("You are not a camp committee member of any camp. You are not allowed to access camp committee member features.");
            System.out.println("Returning to main student menu...");
            System.out.println();
        }

    }
}