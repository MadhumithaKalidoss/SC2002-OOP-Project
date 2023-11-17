package sc2002project;

import java.util.ArrayList;

public class SuggestionList {
    private ArrayList<Suggestion> suggestionList;

    // Constructor to initialize the array list
    public SuggestionList() {
        this.suggestionList = new ArrayList<>();
    }

    // Create a new suggestion object and add it to the list
    public void addSuggestion(String userID, String campName, String suggestionText) {
        Suggestion newSuggestion = new Suggestion(
                suggestionList.size() + 1, // Update the suggestionID
                campName,
                userID,
                suggestionText,
                null, // Initialize answerText as null
                false // Initialize suggestionStatus as false
        );

        suggestionList.add(newSuggestion);
    }

    // Update the suggestionText with the updatedSuggestion
    public void updateSuggestion(Suggestion suggestion, String updatedSuggestion) {
        suggestion.setSuggestionText(updatedSuggestion);
    }

    // Remove the suggestion from the suggestion array list
    public void removeSuggestion(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    // Update the answerText with the answer and change the suggestionStatus to true
    public void addAnswer(Suggestion suggestion, String answer) {
        suggestion.setAnswerText(answer);
        suggestion.setSuggestionStatus(true);
    }

    // Get the suggestionList
    public ArrayList<Suggestion> getSuggestionList() {
        return suggestionList;
    }

    // Check if the suggestionList is empty
    public boolean isEmpty() {
        return suggestionList.isEmpty();
    }
}
