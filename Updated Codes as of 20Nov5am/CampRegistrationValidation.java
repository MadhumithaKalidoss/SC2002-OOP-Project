import java.util.Date;

public class CampRegistrationValidation {

    public boolean isCampOpenToStudent(Camp camp, Student student) {
        String studentFaculty = student.getFaculty();
        String campLocation = camp.getLocation();

        return studentFaculty.equals(campLocation) || campLocation.equalsIgnoreCase("ntu");
    }

    public boolean isCampCommittee(Camp camp, Student student) {
        ObjectFinder objectFinder = new ObjectFinder();
        String role = objectFinder.findStudentRoleInCamp(student, camp);
        return role.equals("Camp Committee Member");
    }

    public boolean hasDateClash(Camp camp, Student student, CampList campList) {
        ObjectFinder objectFinder = new ObjectFinder();
        String role = objectFinder.findStudentRoleInCamp(student, camp);

        if (role.equals("None")) {
            return false; // Student is not part of the camp, so no clash
        }

        Date campStartDate = camp.getStartDate();
        Date campEndDate = camp.getEndDate();

        for (Camp otherCamp : campList.getCampList()) {
            if (!otherCamp.equals(camp)) {
                String otherCampRole = objectFinder.findStudentRoleInCamp(student, otherCamp);
                if (!otherCampRole.equals("None")) {
                    Date otherCampStartDate = otherCamp.getStartDate();
                    Date otherCampEndDate = otherCamp.getEndDate();

                    // Check for date clash
                    if (campStartDate.before(otherCampEndDate) && otherCampStartDate.before(campEndDate)) {
                        return true; // Date clash found
                    }
                }
            }
        }

        return false; // No date clash found
    }

    public boolean isFullforAttendees(Camp camp) {
        int totalSlots = camp.getTotalSlots();
        int currentAttendees = camp.getListofAttendees().size();
        return totalSlots <= currentAttendees;
    }
    
    public boolean isFullforCampCommittee(Camp camp) {
        int totalSlots = camp.getCampCommitteeSlots();
        int currentCampCommittee = camp.getListofCampCommittee().size();
        return totalSlots <= currentCampCommittee;
    }

    public boolean hasPassedRegistrationDeadline(Camp camp) {
        Date currentDate = new Date();
        Date registrationDeadline = camp.getRegistrationClosingDate();
        return currentDate.after(registrationDeadline);
    }

    public boolean hasWithdrawnFromCamp(Camp camp, Student student) {
        return student.hasWithdrawnFromCamp(camp.getCampName());
    }
}
