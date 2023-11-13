package sc2002project;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class StudentApp {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	StudentList studentList = new StudentList();
    	EnquiryList enquiryList = new EnquiryList();
    	StudentEnquiryManager enquiryManager = new StudentEnquiryManager(enquiryList);
    	CampList campList = new CampList();
    	ObjectFinder objectFinder = new ObjectFinder();
    	CampRegistrationValidation validation = new CampRegistrationValidation();
    	CampRegistration campRegistration = new CampRegistration(campList, objectFinder, validation, studentList);
    	CheckCampDetails checkCampDetails = new CheckCampDetails(campList, objectFinder, studentList);
    	CampWithdrawal campWithdrawal = new CampWithdrawal(campList, objectFinder, studentList);


        // Simulate student login
        String studentUserID = "student123"; // Replace with actual student login
        boolean isStudent = true; // Check if the user is a student

        if (isStudent) {
            System.out.println("Welcome, Student!");

            while (true) {
                System.out.println("Please select an option:");
                System.out.println("1. View Details of of Available Camps");
                //System.out.println("2. View Remaining Slots for Available Camps");
                System.out.println("2. View Registered Camps and Roles");
                System.out.println("3. Register for a Camp (as Camp Attendee or Camp Committee)");
                System.out.println("4. Withdraw from a Camp");
                System.out.println("5. Submit Enquiry");
                System.out.println("6. Delete Enquiry");
                System.out.println("7. Edit Enquiry");
                System.out.println("8. View Enquiries"); //edit this to make it show the answer if it has been processed else show like enquiry has not been answered
                System.out.println("9. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkCampDetails.viewAvailableCampDetails(studentUserID); //print whether sttanedee slots/camp committee lots are full or free still
                        break;

                    case 2:
                        checkCampDetails.viewRegisteredCamps(studentUserID);
                        break;

                    case 3:
                        campRegistration.registerForCamp(studentUserID);
                        break;

                    case 4:
                        campWithdrawal.withdrawFromCamp(studentUserID);
                        break;

                    case 5:
                        enquiryManager.submitEnquiry(studentUserID); //check is user is an attendee
                        break;

                    case 6:
                        enquiryManager.deleteEnquiry(studentUserID, enquiryList); //check is user is an attendee
                        break;

                    case 7:
                        enquiryManager.editEnquiry(studentUserID, enquiryList); //check is user is an attendee
                        break;

                    case 8:
                        enquiryManager.viewEnquiries(studentUserID, enquiryList); //check is user is an attendee
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

