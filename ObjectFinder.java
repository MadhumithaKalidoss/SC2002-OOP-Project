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
    
    public Student findStudentByUserId(String userId, ArrayList<Student> students) {
        for (Student student : students) {
            if (student.getUserID().equalsIgnoreCase(userId)) {
                return student; // Return the student object when the user ID matches
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
}
