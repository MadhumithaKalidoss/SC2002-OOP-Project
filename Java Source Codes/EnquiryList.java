import java.util.ArrayList;

/**
 * Represents a list of enquiries with operations to manage enquiry-related functionalities.
 * Implements the FeedbackList interface for managing Enquiry objects.
 */
public class EnquiryList implements FeedbackList<Enquiry>{
    /**
     * A list of enquiries made
     */
    private ArrayList<Enquiry> enquiryList;

    /**
     * Constructs an EnquiryList object with an empty list of enquiries.
     */
    public EnquiryList() {this.enquiryList = new ArrayList<>();}

    /**
     * Adds a new enquiry to the list.
     * @param userID The user ID associated with the enquiry.
     * @param campName The name of the camp related to the enquiry.
     * @param enquiryContent The content of the enquiry.
     */
    public void add(String userID, String campName, String enquiryContent) {

        // Create a new Enquiry
        Enquiry newEnquiry = new Enquiry();

        newEnquiry.setEnquiryID(enquiryList.size() + 1);//get the current size or array list, add 1 and assign to enquiryID;
        newEnquiry.setStudentUserID(userID); // Set the studentID
        newEnquiry.setCampName(campName); // Set the campName
        newEnquiry.setEnquiryContent(enquiryContent); // Set EnquiryContent
        newEnquiry.setAnswerContent(null); // Set AnswerContent to null
        newEnquiry.setStatus(false); // Set status to False

        enquiryList.add(newEnquiry);
    }

    /**
     * Removes an enquiry from the list.
     * @param enquiry The enquiry object to be removed.
     */
    public void remove(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }

    /**
     * Updates the content of an existing enquiry.
     * @param enquiry The enquiry object to be updated.
     * @param newContent The new content to update for the enquiry.
     */
    public void update(Enquiry enquiry, String newContent) {
        enquiry.setEnquiryContent(newContent);
    }

    /**
     * Adds an answer to an enquiry and updates its status.
     * @param enquiry The enquiry object to add an answer to.
     * @param newContent The new content to set as the answer.
     */
    public void addAnswer(Enquiry enquiry, String newContent) {
        enquiry.setAnswerContent(newContent);
        enquiry.setStatus(true);
    }

    /**
     * Retrieves the list of enquiries.
     * @return The ArrayList containing the list of enquiries.
     */
    public ArrayList<Enquiry> getList() {
        return enquiryList;
    }

    /**
     * Checks if the list of enquiries is empty.
     * @return true if the list is empty; false otherwise.
     */
    public boolean isEmpty() {
        return enquiryList.isEmpty();
    }
}


