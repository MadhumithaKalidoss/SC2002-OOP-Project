import java.util.Scanner;
/**
 * The {@code CampWithdrawal} class provides functionality for withdrawing a {@link Student} from a {@link Camp}.
 * It allows students to withdraw from a camp, updating the camp's attendee list and the student's withdrawal record.
 * This class utilizes the {@link ObjectFinder} to locate the camp and determine the student's role in the camp.
 * Withdrawal confirmation is required, and the withdrawal process differs for attendees and camp committee members.
 *
 */

public class CampWithdrawal {
    private CampList campList;
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a {@code CampWithdrawal} instance with the provided {@link CampList}.
     *
     * @param campList The {@link CampList} containing the master list of camps that stores all the students signed up for all the camps.
     */
    public CampWithdrawal(CampList campList) {
        this.campList = campList;
    }

    /**
     * Withdraws a {@link Student} from a camp based on user input.
     * The method prompts the user for the camp name and processes the withdrawal accordingly.
     * Students cannot withdraw if they are camp committee members or if they are not registered for the camp.
     * Withdrawal confirmation is required and can be canceled by the user.
     * @exception java.util.InputMismatchException  exceptions related to withdrawing from a camp. It provides appropriate error messages and allows the user to retry input when exceptions occur, such as when entering an invalid camp name or an invalid withdrawal confirmation.
     * @param student The {@link Student} intending to withdraw from a camp.
     */
    public void withdrawFromCamp(Student student) {

        try {
            if (campList.getCampList().isEmpty()) {
                System.out.println("There are no existing camps.");
                System.out.println();
                return;
            }

            System.out.println("Enter the name of the camp you want to withdraw from: ");
            String campName = scanner.nextLine();

            Camp camp = objectFinder.findCampByName(campName, campList);
            if (camp == null) {
                System.out.println("Camp does not exist.");
                System.out.println();
            } else {
                // Check the student role in camp
                String role = objectFinder.findStudentRoleInCamp(student, camp);

                if (role.equals("Camp Committee Member")) {
                    System.out.println("You are registered as a camp committee member. You are not allowed to withdraw from this camp.");
                    System.out.println();
                    return;
                }

                if (role.equals("None")) {
                    System.out.println("You are not registered for this camp.");
                    System.out.println();
                    return;
                }

                System.out.println("You have been registered as a " + role + " for this camp.");
                System.out.println("You will not be able to register for this camp again if you withdraw.");
                System.out.println("Are you sure you want to withdraw from this camp? (Enter Yes/No)");

                String withdrawConfirmation = scanner.nextLine();

                if (withdrawConfirmation.equalsIgnoreCase("yes")) {
                    // Remove the student from the respective array list based on their role
                    if (role.equals("Attendee")) {
                        camp.getListOfAttendees().remove(student);

                        // Update that the student has withdrawn from the camp
                        student.addWithdrawnCampName(campName);
                        System.out.println("You have been successfully removed from this camp.");
                        System.out.println();
                    }

                } else if (withdrawConfirmation.equalsIgnoreCase("no")) {
                    System.out.println("Withdrawal cancelled.");
                    System.out.println();

                } else {
                    System.out.println("Invalid input");
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            System.out.println();
        }
    }
}

