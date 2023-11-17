package sc2002project;

import java.util.Scanner;

public class CampRegistration {

    private CampList campList;
    private ObjectFinder objectFinder;
    private CampRegistrationValidation validation;
    private StudentList studentList;

    public CampRegistration(CampList campList, ObjectFinder objectFinder, CampRegistrationValidation validation, StudentList studentList) {
        this.campList = campList;
        this.objectFinder = objectFinder;
        this.validation = validation;
        this.studentList = studentList;
    }

    public void registerForCamp(String studentUserID) {
        Scanner scanner = new Scanner(System.in);

        // Ask user input for the campName
        System.out.println("Enter the camp name you want to sign up for: ");
        String campName = scanner.nextLine();

        // Retrieve student object using studentUserID
        Student student = objectFinder.findStudentByUserId(studentUserID, studentList.getStudentList());

        // Retrieve camp object using campName
        Camp camp = objectFinder.findCampByName(campName, campList.getCampList());

        if (camp != null && student != null) {
            // Provide user with options for roles
            System.out.println("Choose your role for the camp:");
            System.out.println("1. Camp Attendee");
            System.out.println("2. Camp Committee Member");

            int roleOption = scanner.nextInt();

            switch (roleOption) {
                case 1: // Camp Attendee
                    registerAsAttendee(student, camp);
                    break;

                case 2: // Camp Committee Member
                    registerAsCampCommittee((CampCommittee) student, camp);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } else {
            System.out.println("Camp or student not found.");
        }
    }

    private void registerAsAttendee(Student student, Camp camp) {
        if (validation.isCampOpenToStudent(camp, student) &&
            !validation.hasWithdrawnFromCamp(camp, student) &&
            !validation.hasDateClash(camp, student, campList) &&
            !validation.hasPassedRegistrationDeadline(camp) &&
            !validation.isFullforAttendees(camp)) {
            // Add the student to the list of attendees
            camp.getListofAttendees().add(student);
            System.out.println("Successfully registered as a Camp Attendee for " + camp.getCampName());
        } else {
            System.out.println("Failed to register as a Camp Attendee for " + camp.getCampName());
        }
    }

    private void registerAsCampCommittee(CampCommittee student, Camp camp) {
        if (validation.isCampOpenToStudent(camp, student) &&
            !validation.hasWithdrawnFromCamp(camp, student) &&
            !validation.hasDateClash(camp, student, campList) &&
            !validation.hasPassedRegistrationDeadline(camp) &&
            !validation.isFullforCampCommittee(camp) &&
            !validation.isCampCommittee(camp, student)) {
            // Add the student to the list of committee members
            camp.getListofCampCommittee().add(student);
            System.out.println("Successfully registered as a Camp Committee Member for " + camp.getCampName());
        } else {
            System.out.println("Failed to register as a Camp Committee Member for " + camp.getCampName());
        }
    }
}

