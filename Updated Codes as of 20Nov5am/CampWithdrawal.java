import java.util.Scanner;

public class CampWithdrawal {

    private CampList campList;
    private ObjectFinder objectFinder;
    private StudentList studentList;

    public CampWithdrawal(CampList campList, ObjectFinder objectFinder, StudentList studentList) {
        this.campList = campList;
        this.objectFinder = objectFinder;
        this.studentList = studentList;
    }

    public void withdrawFromCamp(Student student) {
        Scanner scanner = new Scanner(System.in);

        // Ask user input for the campName
        System.out.println("Enter the camp name you want to withdraw from: ");
        String campName = scanner.nextLine();

        // Retrieve camp object using campName
        Camp camp = objectFinder.findCampByName(campName, campList.getCampList());

        // Check if the student is an attendee or camp committee member of that camp
        String role = objectFinder.findStudentRoleInCamp(student, camp);

        if (role.equals("Camp Committee Member")) {
            System.out.println("You are not allowed to withdraw from this camp.");
            return;
        }
        
        if (role.equals("None")) {
            System.out.println("You are not registered for this camp.");
            return;
        }

        // Print confirmation and ask for withdrawal confirmation
        System.out.println("You have been registered as a " + role + " for this camp.");
        System.out.println("You will not be able to register for this camp again if you withdraw.");
        System.out.println("Are you sure you want to withdraw from this camp? (yes/no)");

        String withdrawConfirmation = scanner.nextLine();

        if (withdrawConfirmation.equalsIgnoreCase("yes")) {
            // Remove the student from the respective array list based on their role
            if (role.equals("Attendee")) {
                camp.getListofAttendees().remove(student);

            // Update that the student has withdrawn from the camp
            student.addWithdrawnCampName(campName);

            System.out.println("You have been successfully removed from this camp.");
        } else {
            System.out.println("Withdrawal cancelled.");
        }
    }
    }
}

