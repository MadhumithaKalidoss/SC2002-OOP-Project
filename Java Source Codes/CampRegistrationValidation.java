import java.util.Date;
/**
 * The {@code CampRegistrationValidation} class provides methods for validating various conditions
 * related to camp registration, such as checking whether a student is eligible to register
 * for a camp, verifying registration status, detecting date clashes, and evaluating slot availability.
 * <p>
 * The class interacts with the {@link Camp}, {@link Student}, and {@link CampList} classes to perform validations.
 * It utilizes an instance of {@link ObjectFinder} to locate student roles within a camp and to
 * search for committee members across all camps.
 * </p>
 * <p>
 * The validations cover aspects such as open status, existing registrations, date clashes with
 * other camps, registration deadlines, and slot availability for both attendees and committee members.
 * </p>
 *
 */
public class CampRegistrationValidation {
    ObjectFinder objectFinder = new ObjectFinder();
    /**
     * Checks if a camp is open to a specific student based on their faculty or if the camp
     * is open to any faculty (NTU).
     *
     * @param camp    The {@link Camp} to check for openness.
     * @param student The {@link Student} whose eligibility is being checked.
     * @return {@code true} if the camp is open to the student, {@code false} otherwise.
     */
    public boolean isCampOpenToStudent(Camp camp, Student student) {
        return ((student.getFaculty().equals(camp.getUserGroup()) || camp.getUserGroup().equalsIgnoreCase("ntu")));
    }
    /**
     * Checks if a student is already registered as a camp attendee for the specified camp.
     *
     * @param camp    The {@link Camp} to check for attendance.
     * @param student The {@link Student} whose attendance is being checked.
     * @return {@code true} if the student is a camp attendee, {@code false} otherwise.
     */
    public boolean isCampAttendee(Camp camp, Student student) {
        return camp.getListOfAttendees().contains(student);
    }
    /**
     * Checks if a student is already registered as a camp committee member for the specified camp.
     *
     * @param camp    The {@link Camp} to check for committee membership.
     * @param student The {@link Student} whose committee membership is being checked.
     * @return {@code true} if the student is a camp committee member, {@code false} otherwise.
     */
    public boolean isCampCommittee(Camp camp, Student student) { return camp.getListOfCampCommittee().contains(student);}
    /**
     * Checks if a student is already a committee member for any camp within a given camp list.
     *
     * @param student   The {@link Student} whose committee membership is being checked.
     * @param campList  The {@link CampList} containing all camps to check for committee membership.
     * @return {@code true} if the student is a committee member for any camp, {@code false} otherwise.
     */
    public boolean isCampCommitteeForAnyCamp (Student student, CampList campList) {
        for (Camp camp : campList.getCampList())
        {
            if (isCampCommittee(camp, student))
                return true;
        }
        return false;
    }
    /**
     * Checks for date clashes between the specified camp and other camps in the given camp list
     * for a particular student. Date clashes occur when a student is registered for multiple camps
     * with overlapping date ranges.
     *
     * @param camp      The {@link Camp} to check for date clashes.
     * @param student   The {@link Student} for whom date clashes are being checked.
     * @param campList  The {@link CampList} containing all camps for date clash comparison.
     * @return {@code true} if a date clash is found, {@code false} otherwise.
     */
    public boolean hasDateClash(Camp camp, Student student, CampList campList) {

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
    /**
     * Checks if the specified camp is full for attendees based on the total available slots
     * and the current number of registered attendees.
     *
     * @param camp The {@link Camp} to check for attendee slot availability.
     * @return {@code true} if the camp is full for attendees, {@code false} otherwise.
     */
    public boolean isFullForAttendees(Camp camp) {
        int totalSlots = camp.getTotalSlots();
        int currentAttendees = camp.getListOfAttendees().size();
        return totalSlots <= currentAttendees;
    }
    /**
     * Checks if the specified camp is full for camp committee members based on the total available slots
     * and the current number of registered committee members.
     *
     * @param camp The {@link Camp} to check for committee slot availability.
     * @return {@code true} if the camp is full for committee members, {@code false} otherwise.
     */
    public boolean isFullForCampCommittee(Camp camp) {
        int totalSlots = camp.getCampCommitteeSlots();
        int currentCampCommittee = camp.getListOfCampCommittee().size();
        return totalSlots <= currentCampCommittee;
    }
    /**
     * Checks if the registration deadline for the specified camp has passed.
     *
     * @param camp The {@link Camp} to check for registration deadline.
     * @return {@code true} if the registration deadline has passed, {@code false} otherwise.
     */
    public boolean hasPassedRegistrationDeadline(Camp camp) {
        Date currentDate = new Date();
        Date registrationDeadline = camp.getRegistrationClosingDate();
        return currentDate.after(registrationDeadline);
    }
    /**
     * Checks if the student has previously withdrawn from the specified camp.
     *
     * @param camp    The {@link Camp} to check for withdrawal history.
     * @param student The {@link Student} whose withdrawal history is being checked.
     * @return {@code true} if the student has withdrawn from the camp, {@code false} otherwise.
     */
    public boolean hasWithdrawnFromCamp(Camp camp, Student student) {
        return student.hasWithdrawnFromCamp(camp.getCampName());
    }
}
