package sc2002project;

import java.util.ArrayList;

public class ObjectFinder {
	
	public Enquiry findEnquiryById(int enquiryId, ArrayList<Enquiry> enquiryList) {
        for (Enquiry enquiry : enquiryList) {
            if (enquiry.getEnquiryID() == enquiryId) {
                return enquiry; // Return the enquiry object when the ID matches
            }
        }
        return null; // Return null if no match is found
    }

    // Overloaded method that accepts an EnquiryList instance
    public Enquiry findEnquiryById(int enquiryId, EnquiryList enquiryList) {
        ArrayList<Enquiry> enquiries = enquiryList.getEnquiryList();
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getEnquiryID() == enquiryId) {
                return enquiry; // Return the enquiry object when the ID matches
            }
        }
        return null; // Return null if no match is found
    }
    
    public Suggestion findSuggestionById(int suggestionId, ArrayList<Suggestion> suggestionList) {
        for (Suggestion suggestion : suggestionList) {
            if (suggestion.getSuggestionID() == suggestionId) {
                return suggestion; // Return the suggestion object when the ID matches
            }
        }
        return null; // Return null if no match is found
    }

    // Overloaded method that accepts an SuggestionList instance
    public Suggestion findSuggestionById(int suggestionId, SuggestionList suggestionList) {
        ArrayList<Suggestion> suggestions = suggestionList.getSuggestionList();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getSuggestionID() == suggestionId) {
                return suggestion; // Return the suggestion object when the ID matches
            }
        }
        return null; // Return null if no match is found
    }
    
    public Student findStudentByUserId(String userId, ArrayList<Student> students) {
        for (Student student : students) {
            if (student.getUserID().equalsIgnoreCase(userId)) {
                return student; // Return the student object when the user ID matches
            }
        }
        return null; // Return null if no match is found
    }
    
    //overloaded method
    public Student findStudentByUserId(String userId, StudentList studentList) {
    	ArrayList<Student> students = studentList.getStudentList();
        for (Student student : students) {
            if (student.getUserID().equalsIgnoreCase(userId)) {
                return student; // Return the student object when the user ID matches
            }
        }
        return null; // Return null if no match is found
    }
    
        public Staff findStaffByUserId(String userId, ArrayList<Staff> staffList) {
            for (Staff staff : staffList) {
                if (staff.getUserID().equalsIgnoreCase(userId)) {
                    return staff; // Return the staff object when the user ID matches
                }
            }
            return null; // Return null if no match is found
        }

        // Overloaded method
        public Staff findStaffByUserId(String userId, StaffList staffList) {
        	//if (staffList == null) return null;
        	
            ArrayList<Staff> staffMembers = staffList.getStaffList();
            for (Staff staff : staffMembers) {
                if (staff.getUserID().equalsIgnoreCase(userId)) {
                    return staff; // Return the staff object when the user ID matches
                }
            }
            return null; // Return null if no match is found
        }


    public Camp findCampByName(String name, ArrayList<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampName().equalsIgnoreCase(name)) {
                return camp; // Return the camp object when the name matches
            }
        }
        return null; // Return null if no match is found
    }

    public Student findStudentByName(String name, ArrayList<Student> students) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student; // Return the student object when the name matches
            }
        }
        return null; // Return null if no match is found
    }
    
    
    public String findStudentRoleInCamp(Student student, Camp camp) {
        // Traverse through the array list of students
        for (Student campStudent : camp.getListofAttendees()) {
            if (campStudent.equals(student)) {
                return "Attendee"; // Student is an attendee in the camp
            }
        }

        // Traverse through the array list of camp committee members
        for (CampCommittee committeeMember : camp.getListofCampCommittee()) {
            if (committeeMember.equals(student)) {
                return "Camp Committee Member"; // Student is a camp committee member
            }
        }

        return "None"; // Student is not part of the camp
    }
    
    public ArrayList<Camp> getCampsByStaffID(String staffUserID, CampList campList) {
        ArrayList<Camp> campsByStaff = new ArrayList<>();

        for (Camp camp : campList.getCampList()) {
            if (camp.getStaffinCharge().getUserID().equals(staffUserID)) {
                campsByStaff.add(camp);
            }
        }

        return campsByStaff;
    }
    
 // Method to find the camp name related to an enquiry ID
    public String findCampNameByEnquiryID(int enquiryID, ArrayList<Enquiry> enquiriesList) {
        for (Enquiry enquiry : enquiriesList) {
            if (enquiry.getEnquiryID()==(enquiryID)) {
                return enquiry.getCampName();
            }
        }
        return null; // If enquiry ID not found
    }

    public String findCampNameBySuggestionID(int suggestionID, ArrayList<Suggestion> suggestionList) {
        for (Suggestion suggestion : suggestionList) {
            if (suggestion.getSuggestionID()==(suggestionID)) {
                return suggestion.getCampName();
            }
        }
        return null; // If enquiry ID not found
    }

    // Method to check if the UserID matches the staff in charge or camp committee members for a given camp name
    public boolean checkUserIDInCamp(String campName, String userID, ArrayList<Camp> campsList) {
        for (Camp camp : campsList) {
            if (camp.getCampName().equals(campName)) {
                if (camp.getStaffinCharge().getUserID().equals(userID)) {
                    return true; // UserID matches the staff in charge
                }
                for (CampCommittee committeeMember : camp.getListofCampCommittee()) {
                    if (committeeMember.getUserID().equals(userID)) {
                        return true; // UserID matches a camp committee member
                    }
                }
            }
        }
        return false; // UserID not found among staff in charge or camp committee members
    }

    // Method to check if a camp exists based on camp name and matches UserID with staff in charge or camp committee members
    public boolean checkUserIDInCampIfExists(String campName, String userID, ArrayList<Camp> campsList) {
        for (Camp camp : campsList) {
            if (camp.getCampName().equals(campName)) {
                if (camp.getStaffinCharge().getUserID().equals(userID)) {
                    return true; // UserID matches the staff in charge
                }
                for (CampCommittee committeeMember : camp.getListofCampCommittee()) {
                    if (committeeMember.getUserID().equals(userID)) {
                        return true; // UserID matches a camp committee member
                    }
                }
            }
        }
        return false; // Camp does not exist or UserID not found among staff in charge or camp committee members
    }
}
