import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ClassCommitteApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SuggestionList suggestionList = new SuggestionList(); // Initialize your master list of enquiries
        CCMSuggestionManager suggestionManager = new CCMSuggestionManager(suggestionList); // Pass the list to the manager
        // CampList campList = new CampList(); // Initialize your list of camps

        // Simulate student login
        String studentUserID = "student123"; // Replace with actual student login
        boolean isStudent = true; // Check if the user is a student

        if (isStudent) {
            System.out.println("Welcome, Student!");

            while (true) {
                System.out.println("Please select an option:");
                System.out.println("1. Submit Suggestion");
                System.out.println("2. Delete Suggestion");
                System.out.println("3. Edit Suggestion");
                System.out.println("4. View Suggestions");
                System.out.println("5. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: //Submit Enquiry
                        suggestionManager.submitSuggestion(studentUserID);
                        break;

                    case 2: // Delete Enquiry
                        suggestionManager.deleteSuggestion(studentUserID); //might need to add suggestion list
                        //EnquiryID is not auto-updated to new array index. It remains the same as old index
                        break;

                    case 3: // Edit Enquiry
                        suggestionManager.editSuggestion(studentUserID);
                        break;

                    case 4: // View Enquiries
                        suggestionManager.viewSuggestion(studentUserID);
                        break;

                    case 5:
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