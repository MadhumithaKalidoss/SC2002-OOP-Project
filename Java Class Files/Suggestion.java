package sc2002project;

public class Suggestion {
    private int suggestionID;
    private String campName;
    // private String staffUserID;
    private String studentUserID;
    private String suggestionText;
    private String answerText;
    private boolean suggestionStatus;

    // Parameter constructor
    public Suggestion(int suggestionID, String campName, String studentUserID, String suggestionText, String answerText, boolean suggestionStatus) {
        this.suggestionID = suggestionID;
        this.campName = campName;
        this.studentUserID = studentUserID;
        this.suggestionText = suggestionText;
        this.answerText = answerText;
        this.suggestionStatus = suggestionStatus;
    }

    // No-argument constructor
    public Suggestion() {
        // Default constructor
        this.suggestionID = 0;
        this.studentUserID = null;
        this.campName = null;
        this.suggestionText = null;
        this.answerText = null;
        this.suggestionStatus = false;
    }

    // Getter methods
    public int getSuggestionID() {
        return suggestionID;
    }

    public String getCampName() {
        return campName;
    }

    public String getStudentUserID() {
        return studentUserID;
    }

    public String getSuggestionText() {
        return suggestionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isSuggestionStatus() {
        return suggestionStatus;
    }

    // Setter methods
    public void setSuggestionID(int suggestionID) {
        this.suggestionID = suggestionID;
    }

    public void setStudentUserID(String studentUserID) {
        this.studentUserID = studentUserID;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setSuggestionStatus(boolean suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }
}