import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The {@code CampRegistration} class provides methods for students to register for camps,
 * specifying their roles as either camp attendees or camp committee members.
 * It performs various validations during the registration process to ensure the integrity
 * of the registration data and adheres to the rules set by the {@link CampRegistrationValidation} class.
 * <p>
 * The class interacts with the {@link CampList} to retrieve camp information and maintains
 * an instance of {@link ObjectFinder} to locate camps within the list based on names.
 * It uses a {@link Scanner} for user input during the registration process.
 * <p>
 * The registration process involves selecting a camp, choosing a role, and performing validations
 * to ensure eligibility. The registration methods are private and called based on the user's role choice.
 * </p>
 * <p>
 * The class employs an instance of {@link CampRegistrationValidation} to validate various conditions,
 * such as existing registrations, open camp status, withdrawal history, date clashes, and registration deadlines.
 * </p>
 *
 */
public class CampRegistration {
    private CampList campList;
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);
    CampRegistrationValidation validation = new CampRegistrationValidation();

    /**
     * Constructs a new {@code CampRegistration} object with a reference to a {@link CampList}.
     *
     * @param campList The {@link CampList} used for retrieving camp information during registration.
     */
    public CampRegistration(CampList campList) {
        this.campList = campList;
    }

    /**
     * Initiates the camp registration process for a specific student, allowing them to choose a camp
     * and specify their role as either camp attendee or camp committee member.
     * @exception InputMismatchException  handles exceptions related to user input during the process of registering for a camp. It provides appropriate error messages and allows the user to retry input when exceptions occur, such as when entering an invalid camp name or an invalid role option.
     * @param student The {@link Student} registering for the camp.
     */
    public void registerForCamp(Student student) {

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps to register of.");
            System.out.println();
            return;
        }

        boolean validCamp = false;
        Camp camp = null;

        while (!validCamp) {
            try {
                System.out.println("Enter the camp name you want to sign up for: ");
                String campName = scanner.nextLine();

                camp = objectFinder.findCampByName(campName, campList);

                if (camp != null) {
                    validCamp = true;
                    if (!camp.getVisibilityToggle()) {
                        System.out.println("The camp is not available to you yet. You are not allowed to register for this camp.");
                        System.out.println();
                        return;
                    }
                } else {
                    System.out.println("The entered camp name does not exist. Please try again.");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear the scanner buffer

                System.out.println("Invalid input format. Please enter a valid camp name.");
                System.out.println();
            }
        }

        boolean validOption = false;
        while (!validOption) {
            try {
                // Provide user with options for roles
                System.out.println("Choose your role for the camp:");
                System.out.println("1. Camp Attendee");
                System.out.println("2. Camp Committee Member");

                int roleOption = scanner.nextInt();
                scanner.nextLine();

                switch (roleOption) {
                    case 1: // Camp Attendee
                        registerAsAttendee(student, camp);
                        validOption = true;
                        break;

                    case 2: // Camp Committee Member
                        registerAsCampCommittee(student, camp);
                        validOption = true;
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }

    /**
     * Registers a student as a camp attendee for the specified camp.
     * Validates various conditions before adding the student to the list of attendees.
     *
     * @param student The {@link Student} registering for the camp.
     * @param camp    The {@link Camp} for which the student is registering.
     */

    private void registerAsAttendee(Student student, Camp camp) {
        boolean validated = true;

        if (validation.isCampAttendee(camp, student)) {
            System.out.println("You are already an attendee of this camp!");
            validated = false;
        } else if (validation.isCampCommittee(camp, student)) {
            System.out.println("You are already a committee member for this camp!");
            validated = false;
        } else {
            if (!validation.isCampOpenToStudent(camp, student)) {
                System.out.println("Camp is not open to you!");
                validated = false;
            }
            if (validation.hasWithdrawnFromCamp(camp, student)) {
                System.out.println("You have previously withdrawn from this camp. You are not allowed to register for this camp again.");
                validated = false;
            }
            if (validation.hasDateClash(camp, student, campList)) {
                System.out.println("The camp duration clashes with another camp you have signed up for!");
                validated = false;
            }
            if (validation.hasPassedRegistrationDeadline(camp)) {
                System.out.println("The registration deadline has passed!");
                validated = false;
            }
            if (validation.isFullForAttendees(camp)) {
                System.out.println("There are no available slots for attendees!");
                validated = false;
            }
        }

        if (validated) {
            // Add the student to the list of attendees
            camp.getListOfAttendees().add(student);
            System.out.println("Successfully registered as a Camp Attendee for " + camp.getCampName() + "!");
        } else {
            System.out.println("Failed to register as a Camp Attendee for " + camp.getCampName() + "!");
        }
        System.out.println();
    }

    /**
     * Registers a student as a camp committee member for the specified camp.
     * Validates various conditions before adding the student to the list of committee members.
     *
     * @param student The {@link Student} registering for the camp.
     * @param camp    The {@link Camp} for which the student is registering.
     */
    private void registerAsCampCommittee(Student student, Camp camp) {
        boolean validated = true;

        if (validation.isCampAttendee(camp, student)) {
            System.out.println("You are already an attendee of this camp!");
            validated = false;
        } else if (validation.isCampCommittee(camp, student)) {
            System.out.println("You are already a committee member for this camp!");
            validated = false;
        } else if (validation.isCampCommitteeForAnyCamp(student, campList)) {
            System.out.println("You are already a camp committee member for another camp!");
            validated = false;
        } else {
            if (!validation.isCampOpenToStudent(camp, student)) {
                System.out.println("Camp is not open to you!");
                validated = false;
            }
            if (validation.hasWithdrawnFromCamp(camp, student)) {
                System.out.println("You have previously withdrawn from this camp. You are not allowed to register for this camp again.");
                validated = false;
            }
            if (validation.hasDateClash(camp, student, campList)) {
                System.out.println("The camp duration clashes with another camp you have signed up for!");
                validated = false;
            }
            if (validation.hasPassedRegistrationDeadline(camp)) {
                System.out.println("The registration deadline has passed!");
                validated = false;
            }
            if (validation.isFullForCampCommittee(camp)) {
                System.out.println("There are no available slots for committee members!");
                validated = false;
            }
        }

        if (validated) {
            // Add the student to the list of committee members
            camp.getListOfCampCommittee().add(student);
            System.out.println("Successfully registered as a Camp Committee Member for " + camp.getCampName() + "!");
        } else {
            System.out.println("Failed to register as a Camp Committee Member for " + camp.getCampName() + "!");
        }
        System.out.println();
    }
}
