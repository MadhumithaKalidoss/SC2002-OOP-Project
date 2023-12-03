/**
 * Represents a suggestion made by a camp committee member for a camp, including its status and approval details.
 */
public class Suggestion {
    /**
     * The ID of the suggestion made
     */
    private int suggestionID;
    /**
     * The name of the cam related to the suggestion
     */
    private String campName;
    /**
     * The ID of the student that made the suggestion
     */
    private String studentUserID;
    /**
     * The content of the suggestion
     */
    private String suggestionText;
    /**
     * The approval status of the suggestion, whether it is accepted or rejected
     */
    private String approvalStatus;
    /**
     * The status of the suggestion, whether it is answered or not
     */
    private boolean suggestionStatus;


    /**
     *Parameterized constructor to create a Suggestion object with specific details.
     * @param suggestionID The ID of the suggestion.
     * @param campName The name of the camp related to the suggestion.
     * @param studentUserID The user ID of the student making the suggestion.
     * @param suggestionText The content of the suggestion.
     * @param approvalStatus The approval status of the suggestion.
     * @param suggestionStatus The status of the suggestion.
     */
    public Suggestion(int suggestionID, String campName, String studentUserID, String suggestionText, String approvalStatus, boolean suggestionStatus) {
        this.suggestionID = suggestionID;
        this.campName = campName;
        this.studentUserID = studentUserID;
        this.suggestionText = suggestionText;
        this.approvalStatus = approvalStatus;
        this.suggestionStatus = suggestionStatus;
    }

    /**
     * No-argument constructor initializing Suggestion with default values.
     */
    public Suggestion() {
        this.suggestionID = 0;
        this.studentUserID = null;
        this.campName = null;
        this.suggestionText = null;
        this.approvalStatus = null;
        this.suggestionStatus = false;
    }

    /**
     * Retrieves the ID of the suggestion.
     * @return The ID of the suggestion.
     */
    public int getSuggestionID() {
        return suggestionID;
    }

    /**
     * Retrieves the name of the camp related to the suggestion.
     * @return The name of the camp related to the suggestion.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Retrieves the user ID of the student making the suggestion.
     * @return The user ID of the student making the suggestion.
     */
    public String getStudentUserID() {
        return studentUserID;
    }

    /**
     * Retrieves the content of the suggestion.
     * @return The content of the suggestion
     */
    public String getSuggestionText() {
        return suggestionText;
    }

    /**
     * Retrieves the approval status of the suggestion.
     * @return The approval status of the suggestion.
     */
    public String getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * Retrieves the status of the suggestion.
     * @return The status of the suggestion.
     */
    public boolean isSuggestionStatus() {
        return suggestionStatus;
    }

    // Setter methods

    /**
     * Sets the ID of the suggestion.
     * @param suggestionID suggestionID The ID of the suggestion.
     */
    public void setSuggestionID(int suggestionID) {
        this.suggestionID = suggestionID;
    }

    /**
     * Sets the user ID of the student making the suggestion.
     * @param studentUserID The user ID of the student making the suggestion.
     */
    public void setStudentUserID(String studentUserID) {
        this.studentUserID = studentUserID;
    }

    /**
     * Sets the name of the camp related to the suggestion.
     * @param campName The name of the camp related to the suggestion.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Sets the content of the suggestion.
     * @param suggestionText The content of the suggestion.
     */
    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    /**
     * Sets the approval status of the suggestion.
     * @param answerText The approval status of the suggestion
     */
    public void setApprovalStatus(String answerText) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * Sets the status of the suggestion.
     * @param suggestionStatus The status of the suggestion.
     */
    public void setSuggestionStatus(boolean suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }
}
