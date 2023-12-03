import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 * The {@code CCMSuggestionManager} class extends {@link FeedbackManager} and provides functionality specific to handling suggestions
 * made by Camp Committee Members (CCM). CCMs can submit, delete, edit, and view their suggestions using this manager.
 * The class utilizes a {@link SuggestionList} to store and manage suggestions made by users.
 * It interacts with a {@link CampList} and a {@link Student} to identify the relevant camp for submitting suggestions.
 * Suggestion details, such as content, status, and suggestion ID, are managed and displayed.
 *
 * <p>Usage Example:</p>
 * <pre>
 * {@code
 * CampList campList = new CampList();
 * SuggestionList suggestionList = new SuggestionList();
 * CCMSuggestionManager suggestionManager = new CCMSuggestionManager(suggestionList);
 * suggestionManager.submit(student, campList);
 * suggestionManager.view(student);
 * suggestionManager.delete(student);
 * suggestionManager.edit(student);
 * }
 * </pre>
 */
public class CCMSuggestionManager extends FeedbackManager {
    private SuggestionList suggestionList;
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a {@code CCMSuggestionManager} instance with the provided {@link SuggestionList}.
     *
     * @param suggestionList The {@link SuggestionList} used for managing suggestions.
     */
    public CCMSuggestionManager(SuggestionList suggestionList) {
        this.suggestionList = suggestionList;
    }

    /**
     * Allows a Camp Committee Member (CCM) to submit a suggestion for a specific camp.
     * The CCM is prompted to enter the suggestion content, and the suggestion is added to the suggestion list.
     * Points are awarded to the student for submitting a suggestion.
     * @exception IllegalArgumentException for invalid suggestion inputs and ensures proper user guidance
     * @exception NullPointerException by validating the camp object before accessing its properties.
     * @param student  The {@link Student} submitting the suggestion.
     * @param campList The {@link CampList} used to identify the relevant camp for the suggestion.
     */
    // Submit a suggestion
    public void submit(Student student, CampList campList) {

        Camp camp = null;
        for (Camp campObject : campList.getCampList())
        {
            if (campObject.getListOfCampCommittee().contains(student))
                camp = campObject;
        }

        String suggestionText = null;
        do {
            try {
                System.out.println("You are making a suggestion to " + camp.getCampName());
                System.out.println("\nEnter your suggestion");
                suggestionText = scanner.nextLine();

                // Check if the description contains only digits or special symbols
                if (suggestionText.matches("[\\d\\p{Punct}]+")) {
                    throw new IllegalArgumentException("Invalid input. Please enter a valid suggestion without just numbers or special symbols.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                suggestionText = null;
            }
        } while (suggestionText == null);

        suggestionList.add(student.getUserID(), camp.getCampName(), suggestionText);

        System.out.println("Your suggestion has been submitted.");
        System.out.println();

        // Add points to the student
        student.addPoints();
        student.getCountSuggestionsGiven();
    }

    /**
     * Allows a Camp Committee Member (CCM) to delete a previously submitted suggestion.
     * The CCM is prompted to enter the suggestion ID to be deleted, and the corresponding suggestion is removed from the list.
     * Points are deducted from the student for deleting a suggestion.
     * @exception InputMismatchException handles user input for deleting suggestions, checks for the validity of the entered suggestion ID, ensures the user's ownership of the suggestion, and verifies whether the suggestion has been processed before allowing deletion
     * @param student The {@link Student} attempting to delete a suggestion.
     */

    // Delete a suggestion
    public void delete(Student student) {

        ArrayList<Suggestion> suggestions = objectFinder.findSuggestionsByUserID(student.getUserID(), suggestionList);
        if (suggestions.isEmpty()) {
            System.out.println("You have made no suggestions to delete.");
            System.out.println();
            return;
        }

        try {
            // Ask the user to enter the suggestion id to be deleted
            System.out.println("Enter the suggestion ID to be deleted: ");
            int suggestionID = scanner.nextInt();

            // Find the suggestion in the list
            for (Suggestion suggestion : suggestionList.getList()) {
                if (suggestion.getSuggestionID() == suggestionID && student.getUserID().equals(suggestion.getStudentUserID())) {
                    // Check if it's the users suggestion
                    if (!objectFinder.findSuggestionById(suggestionID, suggestionList).getStudentUserID().equals(student.getUserID())) {
                        System.out.println("You are not allowed to delete this suggestion.");
                        System.out.println();
                        return;
                    }
                    // Check if the suggestion has already been answered
                    if (suggestion.isSuggestionStatus()) {
                        System.out.println("Your suggestion has already been processed. You cannot delete it.");
                        System.out.println();
                        return;
                    } else {
                        // Delete the suggestion
                        suggestionList.remove(suggestion);
                        System.out.println("Your suggestion has been deleted.");
                        System.out.println();

                        // Remove points from the student
                        student.subPoints();
                        student.subCountSuggestionDeleted();
                        return;
                    }
                }
            }

            // If suggestion does not exist
            System.out.println("Invalid suggestion ID. Please check if the ID is valid.");
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid suggestion ID.");
            System.out.println();
        }
    }
    /**
     * Allows a Camp Committee Member (CCM) to edit the content of a previously submitted suggestion.
     * The CCM is prompted to enter the suggestion ID and the new content for the suggestion.
     * Editing is allowed only if the suggestion has not been processed.
     * @exception InputMismatchException for invalid input when reading the suggestion ID.
     * @param student The {@link Student} attempting to edit a suggestion.
     */
    public void edit(Student student) {

        ArrayList<Suggestion> suggestions = objectFinder.findSuggestionsByUserID(student.getUserID(), suggestionList);
        if (suggestions.isEmpty()) {
            System.out.println("You have made no suggestions to edit.");
            System.out.println();
            return;
        }

        try {
            System.out.println("Enter the Suggestion ID of the suggestion to be edited: ");
            Scanner scanner = new Scanner(System.in);
            int suggestionID = scanner.nextInt();
            scanner.nextLine();

            ObjectFinder objectFinder = new ObjectFinder();
            Suggestion editSuggestion = objectFinder.findSuggestionById(suggestionID, suggestionList);

            if (editSuggestion != null) //check if that suggestion object exists
            {
                if (editSuggestion.getStudentUserID().equals(student.getUserID())) //check if that student made that suggestion
                {
                    if (!editSuggestion.isSuggestionStatus()) //check if the suggestion has been answered or not
                    {
                        System.out.println("Enter the edited suggestion content: ");
                        String newContent = scanner.nextLine();
                        suggestionList.update(editSuggestion, newContent);
                        System.out.println("Your suggestion with ID " + suggestionID + " has been edited!");
                        System.out.println();

                    } else {
                        System.out.println("Your suggestion has already been processed. You cannot edit this suggestion.");
                        System.out.println();
                    }
                } else {
                    System.out.println("You can only edit your own suggestions.");
                    System.out.println();
                }
            } else {
                System.out.println("Invalid Suggestion ID. Please check the ID of your suggestion using 'View Suggestions'.");
                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid suggestion ID.");
            System.out.println();
        }
    }

    /**
     * Displays the suggestions made by a specific Camp Committee Member (CCM).
     * The CCM's suggestions, along with their IDs, content, and processing status, are printed.
     *
     * @param student The {@link Student} for whom suggestions are to be displayed.
     */

    public void view(Student student) {

        ArrayList<Suggestion> suggestions = objectFinder.findSuggestionsByUserID(student.getUserID(), suggestionList);
        if (suggestions.isEmpty()) {
            System.out.println("You have not made any suggestions yet.");
            System.out.println();

        } else {
            System.out.println("Your suggestions:");

            // Print suggestions for the given studentUserID
            for (Suggestion suggestion : suggestions) {
                if (student.getUserID().equals(suggestion.getStudentUserID())) {
                    System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
                    System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
                    System.out.println("Suggestion Status: " + (suggestion.isSuggestionStatus() ? "Processed" : "Not Processed"));
                    System.out.println("------------");
                }
            }
            System.out.println();
        }
    }
}
