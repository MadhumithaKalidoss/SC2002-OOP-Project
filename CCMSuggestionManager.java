import java.util.Scanner;

public class CCMSuggestionManager {
    private SuggestionList suggestionList;

    // Constructor
    public CCMSuggestionManager(SuggestionList suggestionList) {
        this.suggestionList = suggestionList;
    }

    // Submit a suggestion
    public void submitSuggestion(String studentUserID) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the camp name
        System.out.print("Enter the camp name: ");
        String campName = scanner.nextLine();

        // Ask the user to enter their suggestion content
        System.out.print("Enter your suggestion content: ");
        String suggestionText = scanner.nextLine();

        // Call the addSuggestion method to add the suggestion into the suggestion list
        suggestionList.addSuggestion(studentUserID, campName, suggestionText);

        // Inform the user that their suggestion has been submitted
        System.out.println("Your suggestion has been submitted.");
    }

    // Delete a suggestion
    public void deleteSuggestion(String studentUserID) {
        if (suggestionList.isEmpty()) {
            System.out.println("Your suggestion list is empty. No suggestions to delete.");
        } else {
            Scanner scanner = new Scanner(System.in);

            // Ask the user to enter the suggestion id to be deleted
            System.out.print("Enter the suggestion ID to be deleted: ");
            int suggestionID = scanner.nextInt();

            // Find the suggestion in the list
            for (Suggestion suggestion : suggestionList.getSuggestionList()) {
                if (suggestion.getSuggestionID() == suggestionID && studentUserID.equals(suggestion.getStudentUserID())) {
                    // Check if the suggestion has already been answered
                    if (suggestion.isSuggestionStatus()) {
                        System.out.println("Your suggestion has already been answered. You cannot delete it.");
                    } else {
                        // Delete the suggestion
                        suggestionList.removeSuggestion(suggestion);
                        System.out.println("Your suggestion has been deleted.");
                    }
                    return;
                }
            }

            // If suggestion does not exist
            System.out.println("Invalid suggestion ID. Please check if the ID is valid.");
        }
    }

    // Edit a suggestion
    public void editSuggestion(String studentUserID) {
        if (suggestionList.isEmpty()) {
            System.out.println("Your suggestion list is empty. No suggestions to edit.");
        } else {
            Scanner scanner = new Scanner(System.in);

            // Ask the user to enter the suggestion ID to be edited
            System.out.print("Enter the suggestion ID to be edited: ");
            int suggestionID = scanner.nextInt();

            // Find the suggestion in the list
            for (Suggestion suggestion : suggestionList.getSuggestionList()) {
                if (suggestion.getSuggestionID() == suggestionID && studentUserID.equals(suggestion.getStudentUserID())) {
                    // Check if the suggestion has already been answered
                    if (suggestion.isSuggestionStatus()) {
                        System.out.println("Your suggestion has already been answered. You cannot edit it.");
                    } else {
                        // Ask the user to enter their edited content
                        System.out.print("Enter your edited content: ");
                        String updatedSuggestion = scanner.nextLine();

                        // Update the suggestion with the new content
                        suggestionList.updateSuggestion(suggestion, updatedSuggestion);
                        System.out.println("Your suggestion has been edited.");
                    }
                    return;
                }
            }

            // If suggestion does not exist
            System.out.println("Invalid suggestion ID. Please check if the ID is valid.");
        }
    }

    // View enquiries
    public void viewSuggestion(String studentUserID) {
        if (suggestionList.isEmpty()) {
            System.out.println("Your suggestion list is empty.");
        } else {
            System.out.println("Your suggestions:");

            // Print suggestions for the given studentUserID
            for (Suggestion suggestion : suggestionList.getSuggestionList()) {
                if (studentUserID.equals(suggestion.getStudentUserID())) {
                    System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
                    System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
                    System.out.println("Suggestion Status: " + (suggestion.isSuggestionStatus() ? "Answered" : "Not Answered"));
                    System.out.println();
                }
            }
        }
    }
}
