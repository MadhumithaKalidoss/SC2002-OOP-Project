import java.util.ArrayList;

/**
 *Represents a list of suggestions with operations to manage suggestion-related functionalities.
 *Implements the FeedbackList interface for managing Suggestion objects.
 */
public class SuggestionList implements FeedbackList<Suggestion> {
    /**
     * A list of the suggestions submitted by camp committee members
     */
    private ArrayList<Suggestion> suggestionList;

    /**
     * Constructs a SuggestionList object with an empty list of suggestions.
     */
    public SuggestionList() {
        this.suggestionList = new ArrayList<>();
    }

    /**
     * Adds a new suggestion to the list.
     * @param userID The user ID associated with the suggestion.
     * @param campName The name of the camp related to the suggestion.
     * @param suggestionText The content of the suggestion.
     */
    // Create a new suggestion object and add it to the list
    public void add(String userID, String campName, String suggestionText) {
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

    /**
     * Updates the content of an existing suggestion.
     * @param suggestion The suggestion object to be updated.
     * @param updatedSuggestion The new content to update for the suggestion.
     */
    // Update the suggestionText with the updatedSuggestion
    public void update(Suggestion suggestion, String updatedSuggestion) {
        suggestion.setSuggestionText(updatedSuggestion);
    }

    /**
     * Removes a suggestion from the list.
     * @param suggestion The suggestion object to be removed.
     */
    // Remove the suggestion from the suggestion array list
    public void remove(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    /**
     * Adds an answer to a suggestion and updates its status.
     * @param suggestion The suggestion object to add an answer to.
     * @param approvalStatus The approval status to set for the suggestion.
     */
    // Update the answerText with the answer and change the suggestionStatus to true
    public void addAnswer(Suggestion suggestion, String approvalStatus) {
        suggestion.setApprovalStatus(approvalStatus);
        suggestion.setSuggestionStatus(true);
    }

    /**
     * Retrieves the list of suggestions.
     * @return The ArrayList containing the list of suggestions.
     */
    // Get the suggestionList
    public ArrayList<Suggestion> getList() {
        return suggestionList;
    }

    /**
     * Checks if the list of suggestions is empty.
     * @return true if the list is empty; false otherwise.
     */
    // Check if the suggestionList is empty
    public boolean isEmpty() {
        return suggestionList.isEmpty();
    }

}

