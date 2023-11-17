package sc2002project;

import java.util.Scanner;
public class SuggestionApprovalService {
    private SuggestionList suggestionList;

    // Constructor
    public SuggestionApprovalService(SuggestionList suggestionList) {
        this.suggestionList = suggestionList;
    }

    ObjectFinder objectFinder= new ObjectFinder();
    CampList campList = new CampList();
    Suggestion suggestion = new Suggestion();

    // Submit an answer to an enquiry
    public void submitAnswer(String userID) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter the enquiry id
        System.out.print("Enter the suggestion ID to answer: ");
        int suggestionID = scanner.nextInt();

        // Check if the enquiry list is empty
        if (suggestionList.isEmpty()) {
            System.out.println("The suggestion list is empty. No suggestions to answer.");
            return;
        }

        // Check if the enquiryID exists
        Suggestion suggestion = objectFinder.findSuggestionById(suggestionID, suggestionList);
        if (suggestion == null) {
            System.out.println("Invalid suggestion ID. Please check the ID using the 'view suggestions' option.");
            return;
        }

        // Find the camp name related to the enquiryID (This functionality is not provided in the description)
        // Assuming there is a method getCampNameByEnquiryID(enquiryID) in the EnquiryList class
        String campName = objectFinder.findCampNameBySuggestionID(suggestionID, suggestionList.getSuggestionList());

        // Check if the user is authorized to answer the enquiry
        if (!objectFinder.checkUserIDInCamp(campName, userID, campList.getCampList())) {
            System.out.println("You are not authorized to answer suggestions for this camp.");
            return;
        }

        // Check if the enquiry has already been answered
        if (suggestion.isSuggestionStatus()) {
            System.out.println("This suggestion has already been answered.");
        } else {
            // Ask the user to enter an answer content
            System.out.print("Enter the answer content: ");
            String answerContent = scanner.nextLine();

            // Add the answer to the enquiry
            suggestionList.addAnswer(suggestion, answerContent);

            // Update the status to true
            suggestion.setSuggestionStatus(true);

            // Tell the user that the answer has been stored successfully
            System.out.println("The answer has been stored successfully.");
        }
    }

    // View enquiries
    public void viewSuggestions(String userID) {
        if (suggestionList.isEmpty()) {
            System.out.println("Your suggestion list is empty.");
        } else {
            Scanner scanner = new Scanner(System.in);

            // Ask the user for the camp name
            System.out.print("Enter the camp name: ");
            String campName = scanner.nextLine();

            // Check if the campName exists by iterating through the list of camps
            if (objectFinder.findCampByName(campName, campList.getCampList()) == null) {
                System.out.println("Invalid camp name. Please check the camp name.");
                return;
            }

            // Print enquiries for the given campName and userID
            System.out.println("Your suggestions:");

            for (Suggestion enquiry : suggestionList.getSuggestionList()) {
                if (objectFinder.checkUserIDInCampIfExists(campName, userID, campList.getCampList())) {
                    System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
                    System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
                    System.out.println("Suggestion Status: " + (suggestion.isSuggestionStatus() ? "Answered" : "Not Answered"));
                    System.out.println();
                }
            }
        }
    }

}
