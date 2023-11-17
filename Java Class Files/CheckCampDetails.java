package sc2002project;

public class CheckCampDetails {

    private CampList campList;
    private ObjectFinder objectFinder;
    private StudentList studentList;

    public CheckCampDetails(CampList campList, ObjectFinder objectFinder, StudentList studentList) {
        this.campList = campList;
        this.objectFinder = objectFinder;
        this.studentList = studentList;
    }

    public void viewAvailableCampDetails(String studentUserID) {
        // Retrieve student object using studentUserID
        Student student = objectFinder.findStudentByUserId(studentUserID, studentList.getStudentList());

        // Get student faculty
        String studentFaculty = student.getFaculty();

        // Traverse through each camp in CampList
        for (Camp camp : campList.getCampList()) {
            // Check if camp is open to the student's faculty and visibilityToggle is true
            if (studentFaculty.equals(camp.getLocation()) && camp.getVisibilityToggle()) {
            	
            	int remainingAttendeeSlots = camp.getTotalSlots() - camp.getListofAttendees().size();
            	int remainingCampCommitteeSlots = camp.getCampCommitteeSlots() - camp.getListofCampCommittee().size();
            	// Print the camp details
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Description: " + camp.getDescription());
                System.out.println("Location: " + camp.getLocation());
                System.out.println("Start Date: " + camp.getStartDate());
                System.out.println("End Date: " + camp.getEndDate());
                System.out.println("Remaining Slots for Attendees: " + remainingAttendeeSlots);
                System.out.println("Remaining Slots for Camp Committee Members: " + remainingCampCommitteeSlots);
                System.out.println("------------");
            }
        }
    }

    /*public void viewCampSlots(String studentUserID) {
        // Retrieve student object using studentUserID
        Student student = objectFinder.findStudentByUserId(studentUserID, studentList.getStudentList());

        // Get student faculty
        String studentFaculty = student.getFaculty();

        // Traverse through each camp in CampList
        for (Camp camp : campList.getCampList()) {
            // Check if camp is open to the student's faculty and visibilityToggle is true
            if (studentFaculty.equals(camp.getLocation()) && camp.getVisibilityToggle()) {
                // Calculate remaining slots
                int remainingSlots = camp.getTotalSlots() - camp.getListofAttendees().size();

                // Print camp name and remaining slots
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Remaining Slots: " + remainingSlots);
                System.out.println("------------");
            }
        }
    }*/

    public void viewRegisteredCamps(String studentUserID) {
        // Retrieve student object using studentUserID
        Student student = objectFinder.findStudentByUserId(studentUserID, studentList.getStudentList());

        // Traverse through each camp in CampList
        for (Camp camp : campList.getCampList()) {
            // Use String role to check whether a student is an attendee or camp committee member
            String role = objectFinder.findStudentRoleInCamp(student, camp);

            // If the role is 'Attendee' or 'Camp Committee Member', print camp name and the role
            if (role.equals("Attendee") || role.equals("Camp Committee Member")) {
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Role: " + role);
                System.out.println("------------");
            }
        }
    }

    public void viewCampCommitteeStatus(String studentUserID) {
        // Retrieve student object using studentUserID
        Student student = objectFinder.findStudentByUserId(studentUserID, studentList.getStudentList());

        // Traverse through each camp in CampList
        for (Camp camp : campList.getCampList()) {
            // Use String role to check whether a student is a camp committee member
            String role = objectFinder.findStudentRoleInCamp(student, camp);

            // If the role is 'Camp Committee Member', print camp name and the role
            if (role.equals("Camp Committee Member")) {
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Role: " + role);
                System.out.println("------------");
            }
        }
    }
}

