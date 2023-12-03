import java.util.ArrayList;
/**
 * The {@code ObjectFinder} class provides methods for finding objects such as {@code Enquiry}, {@code Suggestion},
 * {@code Student}, and {@code Camp} within their respective lists. It is used to locate objects based on various criteria,
 * including unique identifiers and user roles.
 *
 * @author Your Name
 * @version 1.0
 */
public class ObjectFinder {

    /**
     * Finds an {@code Enquiry} object by its unique ID within the given {@code EnquiryList}.
     *
     * @param enquiryId    The unique ID of the enquiry to find.
     * @param enquiryList  The list of enquiries to search.
     * @return The {@code Enquiry} object if found, or {@code null} if not found.
     */
    public Enquiry findEnquiryById(int enquiryId, EnquiryList enquiryList) {
        ArrayList<Enquiry> enquiries = enquiryList.getList();
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getEnquiryID() == enquiryId) {
                return enquiry; // Return the enquiry object when the ID matches
            }
        }
        return null; // Return null if no match is found
    }

    /**
     * Finds a {@code Suggestion} object by its unique ID within the given {@code SuggestionList}.
     *
     * @param suggestionId   The unique ID of the suggestion to find.
     * @param suggestionList The list of suggestions to search.
     * @return The {@code Suggestion} object if found, or {@code null} if not found.
     */
    public Suggestion findSuggestionById(int suggestionId, SuggestionList suggestionList) {
        ArrayList<Suggestion> suggestions = suggestionList.getList();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getSuggestionID() == suggestionId) {
                return suggestion; // Return the suggestion object when the ID matches
            }
        }
        return null; // Return null if no match is found
    }


    /**
     * Finds a list of {@code Enquiry} objects associated with a given user ID within the given {@code EnquiryList}.
     *
     * @param userID        The user ID for which to find enquiries.
     * @param enquiryList   The list of enquiries to search.
     * @return An {@code ArrayList} of {@code Enquiry} objects associated with the specified user ID.
     */
    public ArrayList<Enquiry> findEnquiresByUserID(String userID, EnquiryList enquiryList) {
        ArrayList<Enquiry> userEnquiries = new ArrayList<>();
        for (Enquiry enquiry : enquiryList.getList()) {
            if (enquiry.getStudentUserID().equals(userID)) {
                userEnquiries.add(enquiry);
            }
        }
        return userEnquiries;
    }

    /**
     * Finds a list of {@code Suggestion} objects associated with a given user ID within the given {@code SuggestionList}.
     *
     * @param userID          The user ID for which to find suggestions.
     * @param suggestionList  The list of suggestions to search.
     * @return An {@code ArrayList} of {@code Suggestion} objects associated with the specified user ID.
     */
    public ArrayList<Suggestion> findSuggestionsByUserID(String userID, SuggestionList suggestionList) {
        ArrayList<Suggestion> userSuggestions = new ArrayList<>();
        for (Suggestion suggestion : suggestionList.getList()) {
            if (suggestion.getStudentUserID().equals(userID)) {
                userSuggestions.add(suggestion);
            }
        }
        return userSuggestions;
    }

    /**
     * Finds a {@code Student} object by its user ID within the given {@code StudentList}.
     *
     * @param userId        The user ID of the student to find.
     * @param studentList   The list of students to search.
     * @return The {@code Student} object if found, or {@code null} if not found.
     */
    public Student findStudentByUserId(String userId, StudentList studentList) {
        ArrayList<Student> students = studentList.getStudentList();
        for (Student student : students) {
            if (student.getUserID().equalsIgnoreCase(userId)) {
                return student; // Return the student object when the user ID matches
            }
        }
        return null; // Return null if no match is found
    }

    /**
     * Finds a {@code Camp} object by its name within the given {@code CampList}.
     *
     * @param campName     The name of the camp to find.
     * @param campList     The list of camps to search.
     * @return The {@code Camp} object if found, or {@code null} if not found.
     */
    public Camp findCampByName(String campName, CampList campList) {
        for (Camp camp : campList.getCampList()) {
            if (campName.equalsIgnoreCase(camp.getCampName())) {
                return camp;
            }
        }
        return null; // Return null if no match is found
    }

    /**
     * Determines the role of a {@link Student} in a specific {@link Camp}.
     * Checks if the student is an attendee or a camp committee member.
     *
     * @param student The {@link Student} whose role is being determined.
     * @param camp The {@link Camp} in which the role is being checked.
     * @return A {@code String} representing the role of the student in the camp.
     * Possible return values:
     * - "Attendee": If the student is part of the camp's list of attendees.
     * - "Camp Committee Member": If the student is part of the camp's committee.
     * - "None": If the student is not associated with the camp in any role.
     */
    public String findStudentRoleInCamp(Student student, Camp camp) {
        if (camp.getListOfAttendees().contains(student)) {
            return "Attendee"; // Student is an attendee in the camp
        }

        if (camp.getListOfCampCommittee().contains(student)) {
            return "Camp Committee Member"; // Student is a camp committee member
        }

        return "None"; // Student is not part of the camp
    }

    /**
     * Retrieves a list of {@code Camp} objects associated with a staff member identified by the given staff user ID.
     *
     * @param staffUserID   The user ID of the staff member.
     * @param campList      The list of camps to search.
     * @return An {@code ArrayList} of {@code Camp} objects associated with the specified staff member.
     */
    public ArrayList<Camp> getCampsByStaffID(String staffUserID, CampList campList) {
        ArrayList<Camp> campsByStaff = new ArrayList<>();

        for (Camp camp : campList.getCampList()) {
            if (camp.getStaffInCharge().getUserID().equals(staffUserID)) {
                campsByStaff.add(camp);
            }
        }

        return campsByStaff;
    }

}
