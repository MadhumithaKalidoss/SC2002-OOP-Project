import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * The {@code StudentMenu} class represents the menu options available for a student in the system.
 * It provides functionalities related to camp registration, withdrawal, enquiry submission, and more.
 */
public class StudentMenu {
    /**
     * Displays the menu options and manages the interaction for a student using the system.
     * @exception InputMismatchException for invalid integer inputs during the student menu interaction, ensuring proper input validation
     * @param student The {@link Student} using the system.
     * @param campList The {@link CampList} containing available camps.
     * @param enquiryList The {@link EnquiryList} containing student enquiries.
     * @param suggestionList The {@link SuggestionList} containing student suggestions.
     * @return {@code true} if the student is logged in, {@code false} if logged out.
     */
    public boolean studentMenu(Student student, CampList campList, EnquiryList enquiryList, SuggestionList suggestionList) {
        Scanner scanner = new Scanner(System.in);

        CampRegistration campRegistration = new CampRegistration(campList);
        CampWithdrawal campWithdrawal = new CampWithdrawal(campList);
        StudentEnquiryManager studentEnquiryManager = new StudentEnquiryManager(enquiryList);
        StudentCampViewService studentCampViewService = new StudentCampViewService(campList);
        CCMMenu campCommitteeMenu = new CCMMenu();

        while (true) {
            try {
                System.out.println("Student Menu:");
                System.out.println("1. Change Password");
                System.out.println("2. View Details of Available Camps");
                System.out.println("3. View Registered Camps and Roles");
                System.out.println("4. Register for a Camp");
                System.out.println("5. Withdraw from a Camp");
                System.out.println("6. Submit Enquiry"); // need editing
                System.out.println("7. Delete Enquiry");
                System.out.println("8. Edit Enquiry");
                System.out.println("9. View Enquiries");
                System.out.println("10. Access Camp Committee Features (for camp committee members only)");
                System.out.println("11. Logging out of Application");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        student.changePassword();
                        break;
                    case 2:
                        studentCampViewService.viewAvailableCampDetails(student);
                        break;
                    case 3:
                        studentCampViewService.viewRegisteredCamps(student);
                        break;
                    case 4:
                        campRegistration.registerForCamp(student);
                        break;
                    case 5:
                        campWithdrawal.withdrawFromCamp(student);
                        break;
                    case 6:
                        studentEnquiryManager.submit(student, campList);
                        break;
                    case 7:
                        studentEnquiryManager.delete(student);
                        break;
                    case 8:
                        studentEnquiryManager.edit(student);
                        break;
                    case 9:
                        studentEnquiryManager.view(student);
                        break;
                    case 10: // Access Camp Committee Features
                        System.out.println();
                        campCommitteeMenu.accessCampCommitteeFeatures(student, campList, enquiryList, suggestionList);
                        break;
                    case 11:
                        System.out.println("Logging out...\n");
                        //isLoggedIn = true;
                        return false;

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
