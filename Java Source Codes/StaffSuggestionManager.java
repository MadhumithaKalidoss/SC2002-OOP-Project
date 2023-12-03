import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The StaffSuggestionManager class manages the processing of suggestions by staff members.
 * Staff members can submit answers to suggestions, view suggestions made for a specific camp,
 * and perform related operations.
 */
public class StaffSuggestionManager {
    private SuggestionList suggestionList;
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);
    /**
     * Constructs a StaffSuggestionManager with the specified SuggestionList.
     *
     * @param suggestionList The SuggestionList to be managed by the StaffSuggestionManager.
     */
    public StaffSuggestionManager(SuggestionList suggestionList) {
        this.suggestionList = suggestionList;
    }
    /**
     * Allows a staff member to submit an answer to a suggestion.
     * @exception InputMismatchException for invalid integer inputs when submitting answers to suggestions
     * @param staff        The staff member submitting the answer.
     * @param campList     The list of camps.
     * @param studentList  The list of students.
     */
    public void submitAnswer(Staff staff, CampList campList, StudentList studentList) {
        try {
            if (suggestionList.isEmpty()) {
                System.out.println("The suggestion list is empty. No suggestions to answer");
                System.out.println();
                return;
            }

            System.out.println("Enter the suggestion ID to answer: ");
            int suggestionID = scanner.nextInt();


            Suggestion suggestion = objectFinder.findSuggestionById(suggestionID, suggestionList);
            if (suggestion == null) {
                System.out.println("Invalid suggestion ID. Please check the ID using the 'View Suggestions' option.");
                System.out.println();
                return;
            }

            String campName = suggestion.getCampName();
            Camp camp = objectFinder.findCampByName(campName, campList);


            if (staff == camp.getStaffInCharge()) {

                if (suggestion.isSuggestionStatus()) {
                    System.out.println("This suggestion has already been processed.");
                    System.out.println();
                } else {
                    System.out.println("Suggestion: " + suggestion.getSuggestionText());

                    System.out.println("Enter 1 to approve the suggestion, enter 2 to reject the suggestion: ");
                    int approvalChoice = scanner.nextInt();
                    String approvalStatus = (approvalChoice == 1) ? "Approved" : "Rejected";
                    suggestionList.addAnswer(suggestion, approvalStatus);

                    if (approvalStatus.equals("Approved")) {
                        System.out.println("The suggestion has been approved!");

                        // Add points to student
                        String studentUserId = suggestion.getStudentUserID();
                        Student student = objectFinder.findStudentByUserId(studentUserId, studentList);
                        student.addPoints();
                        student.getCountApprovedSuggestions();
                    } else
                        System.out.println("The suggestion has been rejected!");

                    System.out.println();
                }

            } else {
                System.out.println("You are not authorized to review suggestions made to this camp.");
                System.out.println();
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine();
        }
    }

    /**
     * Allows a staff member to view suggestions made for a specific camp.
     * @exception InputMismatchException for invalid integer inputs when viewing suggestions for a particular camp based on staff authorization.
     * @param staff      The staff member viewing the suggestions.
     * @param campList   The list of camps.
     */
    public void viewSuggestions(Staff staff, CampList campList) {
        try {
            if (suggestionList.isEmpty()) {
                System.out.println("The suggestion list is empty. No suggestions to view.");
                System.out.println();
                return;
            }

            // Ask the user for the camp name
            System.out.println("Enter the camp name: ");
            String campName = scanner.nextLine();
            Camp camp = objectFinder.findCampByName(campName, campList);

            // Check if the campName exists by iterating through the list of camps
            if (camp == null) {
                System.out.println("Invalid camp name. Please check the name of the camp using 'View list of all Camps created by you' .");
                System.out.println();
                return;
            }

            boolean foundSuggestions = false;

            if (staff == camp.getStaffInCharge()) {
                System.out.println("Suggestions for this camp:");
                for (Suggestion suggestion : suggestionList.getList()) {
                    if (suggestion.getCampName().equals(camp.getCampName())) {
                        System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
                        System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
                        System.out.println("Suggestion Status: " + (suggestion.isSuggestionStatus() ? "Answered" : "Not Answered"));
                        System.out.println("------------");
                        foundSuggestions = true;
                    }
                }

                if (!foundSuggestions) {
                    System.out.println("No suggestions made for this camp yet.");
                }
                System.out.println();

            } else {
                System.out.println("You are not authorized to view the suggestions made to this camp.");
                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); // Consume the invalid input
        }

    }
}

