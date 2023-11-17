package sc2002project;

public class Enquiry {
    private int enquiryID;
    private String studentUserID;
    private String campName;
    private String enquiryContent;
    private String answerContent;
    private boolean status;

    // Parameterized constructor
    public Enquiry(int enquiryID, String studentUserID, String campName, String enquiryContent, String answerContent, boolean status) {
        this.enquiryID = enquiryID;
        this.studentUserID = studentUserID;
        this.campName = campName;
        this.enquiryContent = enquiryContent;
        this.answerContent = answerContent;
        this.status = status;
    }

    // No-argument constructor
    public Enquiry() {
        // Initialize with default values - which is empty
        this.enquiryID = 0;
        this.studentUserID = null;
        this.campName = null;
        this.enquiryContent = null;
        this.answerContent = null;
        this.status = false; //enquiry is unprocessed
    }

    public int getEnquiryID() {
        return enquiryID;
    }

    public String getStudentUserID() {
        return studentUserID;
    }

    public String getCampName() {
        return campName;
    }

    public String getEnquiryContent() {
        return enquiryContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public boolean isStatus() {
        return status;
    }

    public void setEnquiryID(int enquiryID) {
        this.enquiryID = enquiryID;
    }

    public void setStudentUserID(String studentUserID) {
        this.studentUserID = studentUserID;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setEnquiryContent(String enquiryContent) {
        this.enquiryContent = enquiryContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
