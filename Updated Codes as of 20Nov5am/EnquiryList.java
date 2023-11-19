import java.util.ArrayList;

public class EnquiryList {
    private ArrayList<Enquiry> enquiryList;

    public EnquiryList() {
        enquiryList = new ArrayList<Enquiry>();
    }

    public void addEnquiry(String userID, String campName, String enquiryContent) {
    
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

    public void removeEnquiry(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }

    public void updateEnquiry(Enquiry enquiry, String newContent) {
        enquiry.setEnquiryContent(newContent);
    }

    public void addAnswer(Enquiry enquiry, String newContent) {
        enquiry.setAnswerContent(newContent);
        enquiry.setStatus(true);
    }

    public ArrayList<Enquiry> getEnquiryList() {
        return enquiryList;
    }
    
    public boolean isEmpty() {
        return enquiryList.isEmpty();
    }
}

