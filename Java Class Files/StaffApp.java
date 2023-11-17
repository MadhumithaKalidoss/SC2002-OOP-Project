package sc2002project;

import java.util.Scanner;

public class StaffApp {

    public static void main(String[] args) {

        // Testing staff functionalities
        //staffFunctionalitiesTest(staffMember, camp1);
    	
    	Scanner scanner = new Scanner(System.in);
    	StudentList studentList = new StudentList();
    	StaffList staffList = new StaffList();
    	EnquiryList enquiryList = new EnquiryList();
    	//StudentEnquiryManager enquiryManager = new StudentEnquiryManager(enquiryList);
    	CampList campList = new CampList();
    	ObjectFinder objectFinder = new ObjectFinder();
    	//CampRegistrationValidation validation = new CampRegistrationValidation();
    	//CampRegistration campRegistration = new CampRegistration(campList, objectFinder, validation, studentList);
    	//CheckCampDetails checkCampDetails = new CheckCampDetails(campList, objectFinder, studentList);
    	//CampWithdrawal campWithdrawal = new CampWithdrawal(campList, objectFinder, studentList);
    	CampManagementService campManagementService = new CampManagementService(campList, staffList);
    	//CampViewService campViewService = new CampViewService(CampList campList, Staff staff);
    	//CampVisibilityService campVisibilityService = new CampVisibilityService();


        // Simulate student login
        String staffUserID = "staff123"; // Replace with actual student login
        boolean isStudent = true; // Check if the user is a student

        if (isStudent) {
            System.out.println("Welcome, Staff!");

            while (true) {
                System.out.println("Please select an option:");

    	        System.out.println("1. Create a Camp");
    	        System.out.println("2. Edit a Camp");
    	        System.out.println("3. Delete a Camp");
    	        System.out.println("4. Toggle Camp Visibility");
    	        System.out.println("5. View All Camps");
    	        System.out.println("6. View Camps Created");
    	        System.out.println("7. View list of students for a camp");
    	        System.out.println("8. View and Reply to Enquiries");
    	        System.out.println("9. Approve Suggestions for Camp Details");
    	        System.out.println("10. Generate Reports");
    	        System.out.println("111. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                    	campManagementService.createNewCamp(staffUserID); //print whether sttanedee slots/camp committee lots are full or free still
                        break;

                    case 2:
                    	//campManagementService.editExistingCamp(null);
                        break;

                    case 3:
                        //campRegistration.registerForCamp(studentUserID);
                        break;

                    case 4:
                        //campWithdrawal.withdrawFromCamp(studentUserID);
                        break;

                    case 5:
                        //enquiryManager.submitEnquiry(studentUserID); //check is user is an attendee
                        break;

                    case 6:
                       // enquiryManager.deleteEnquiry(studentUserID, enquiryList); //check is user is an attendee
                        break;

                    case 7:
                       // enquiryManager.editEnquiry(studentUserID, enquiryList); //check is user is an attendee
                        break;

                    case 8:
                        //enquiryManager.viewEnquiries(studentUserID, enquiryList); //check is user is an attendee
                        break;

                    case 9:
                        System.out.println("Exiting Student Application.");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Access denied. This is for students only.");
        }
   }
    		
 }

   /* private static void staffFunctionalitiesTest(Staff staff, Camp camp) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            // Display menu
            System.out.println("Staff Functionality Menu:");
            System.out.println("1. Create Camp");
            System.out.println("2. View All Camps");
            System.out.println("3. View Own Camps");
            System.out.println("4. Toggle Camp Visibility");
            System.out.println("5. View Enquiries");
            System.out.println("6. Approve Suggestions");
            System.out.println("7. Generate Camp Report");
            System.out.println("8. Generate Performance Report");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Process user choice
            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    // Implement toggle camp visibility logic
                    break;
                case 5:

                    break;
                case 6:
                    // Implement approve suggestions logic
                    break;
                case 7:
                    // Implement generate camp report logic
                    break;
                case 8:
                    // Implement generate performance report logic
                    break;
                case 0:
                    System.out.println("Exiting Staff App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (option != 0);

        scanner.close();
    }
}*/
