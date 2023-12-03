/**
 * Represents an Enquiry made by a student regarding a camp, along with its status and responses.
 */
public class Enquiry {
    /**
     * The ID of the enquiry
     */
    private int enquiryID;
    /**
     * UserID of the student that sent the enquiry
     */
    private String studentUserID;
    /**
     * The name of the camp
     */
    private String campName;
    /**
     * The contents of the enquiry
     */
    private String enquiryContent;
    /**
     * The contents of the answer to the enquiry
     */
    private String answerContent;
    /**
     * The status of whether the enquiry has benn answered to or not
     */
    private boolean status;

    /**
     * Parameterized constructor to create an Enquiry object with specific details.
     * @param enquiryID The ID of the enquiry.
     * @param studentUserID The user ID of the student making the enquiry.
     * @param campName The name of the camp related to the enquiry.
     * @param enquiryContent The content of the enquiry.
     * @param answerContent The content of the answer to the enquiry.
     * @param status The status of the enquiry
     */
    public Enquiry(int enquiryID, String studentUserID, String campName, String enquiryContent, String answerContent, boolean status) {
        this.enquiryID = enquiryID;
        this.studentUserID = studentUserID;
        this.campName = campName;
        this.enquiryContent = enquiryContent;
        this.answerContent = answerContent;
        this.status = status;
    }

    /**
     * No-argument constructor initializing Enquiry with default values.
     */
    public Enquiry() {
        // Initialize with default values - which is empty
        this.enquiryID = 0;
        this.studentUserID = null;
        this.campName = null;
        this.enquiryContent = null;
        this.answerContent = null;
        this.status = false; //enquiry is unprocessed
    }

    /**
     * Retrieves the ID of the enquiry.
     * @return The ID of the enquiry.
     */
    public int getEnquiryID() {
        return enquiryID;
    }

    /**
     * Retrieves the user ID of the student making the enquiry.
     * @return The user ID of the student making the enquiry.
     */
    public String getStudentUserID() {
        return studentUserID;
    }

    /**
     * Retrieves the name of the camp related to the enquiry.
     * @return The name of the camp related to the enquiry.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Retrieves the content of the enquiry.
     * @return The content of the enquiry.
     */
    public String getEnquiryContent() {
        return enquiryContent;
    }

    /**
     * Retrieves the content of the answer to the enquiry.
     * @return The content of the answer to the enquiry.
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * Checks the status of the enquiry.
     * @return The status of the enquiry
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the ID of the enquiry.
     * @param enquiryID The ID of the enquiry to set.
     */
    public void setEnquiryID(int enquiryID) {
        this.enquiryID = enquiryID;
    }

    /**
     * Sets the user ID of the student making the enquiry.
     * @param studentUserID The user ID of the student making the enquiry.
     */
    public void setStudentUserID(String studentUserID) {
        this.studentUserID = studentUserID;
    }

    /**
     * Sets the name of the camp related to the enquiry.
     * @param campName The name of the camp related to the enquiry.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Sets the content of the enquiry.
     * @param enquiryContent The content of the enquiry.
     */
    public void setEnquiryContent(String enquiryContent) {
        this.enquiryContent = enquiryContent;
    }

    /**
     * Sets the content of the answer to the enquiry.
     * @param answerContent The content of the answer to the enquiry.
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * Sets the status of the enquiry.
     * @param status The status of the enquiry
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}

