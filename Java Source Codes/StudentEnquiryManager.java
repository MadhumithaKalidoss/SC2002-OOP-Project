import java.util.ArrayList;
import java.util.Scanner;
/**
 * The {@code StudentEnquiryManager} class manages student enquiries for camps,
 * allowing students to submit, delete, edit, and view their enquiries.
 * It extends the {@link FeedbackManager} class.
 */
public class StudentEnquiryManager extends FeedbackManager {
    private EnquiryList enquiryList;
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a {@code StudentEnquiryManager} with a provided enquiry list.
     *
     * @param enquiryList The {@link EnquiryList} to manage student enquiries.
     */
    public StudentEnquiryManager(EnquiryList enquiryList) {this.enquiryList = enquiryList;}

    /**
     * Allows a student to submit an enquiry for a specific camp.
     *@exception IllegalArgumentException  to address potential errors that might occur during the submission of student enquiries, ensuring a graceful error message and input handling.
     * @param student   The {@link Student} submitting the enquiry.
     * @param campList  The {@link CampList} containing camps.
     */
    public void submit(Student student, CampList campList) {
        try {
            System.out.println("Enter the camp name: ");
            String campName = scanner.nextLine();

            Camp camp = objectFinder.findCampByName(campName, campList);
            if (camp != null) {

                // Check if camp is open to the student
                if ((student.getFaculty().equals(camp.getUserGroup()) || camp.getUserGroup().equalsIgnoreCase("ntu"))) {
                    System.out.println("Enter your enquiry content: ");
                    String enquiryContent = scanner.nextLine();

                    enquiryList.add(student.getUserID(), campName, enquiryContent);
                    System.out.println("Your enquiry has been submitted!");
                    System.out.println();
                } else {
                    System.out.println("This camp is not open to you. You are not allowed to submit enquiries for this camp.");
                    System.out.println();
                }
            } else {
                System.out.println("Invalid Camp Name.");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    /**
     * Enables a student to delete their own enquiry.
     * @exception IllegalArgumentException to cover potential errors arising during the deletion of a student's enquiry, ensuring proper error notification and input handling.
     * @param student The {@link Student} deleting the enquiry.
     */
    public void delete(Student student) {
        try {
            ArrayList<Enquiry> enquiries = objectFinder.findEnquiresByUserID(student.getUserID(), enquiryList);
            if (enquiries.isEmpty()) {
                System.out.println("You have not made any enquiries to delete.");
                System.out.println();
                return;
            }

            System.out.println("Enter the Enquiry ID of the enquiry to be deleted: ");
            int enquiryID = scanner.nextInt();

            ObjectFinder objectFinder = new ObjectFinder();
            Enquiry deleteEnquiry = objectFinder.findEnquiryById(enquiryID, enquiryList);

            if (deleteEnquiry != null) //check if that enquiry object exists
            {
                if (deleteEnquiry.getStudentUserID().equals(student.getUserID())) //check if that student made that enquiry
                {
                    if (!deleteEnquiry.isStatus()) //check if the enquiry has been answered or not
                    {
                        enquiryList.remove(deleteEnquiry);
                        System.out.println("Your enquiry with ID " + enquiryID + " has been deleted!");
                        System.out.println();

                    } else {
                        System.out.println("Your enquiry has already been answered. You cannot delete this enquiry.");
                        System.out.println();
                    }
                } else {
                    System.out.println("You can only delete your own enquiries.");
                    System.out.println();
                }
            } else {
                System.out.println("Invalid Enquiry ID. Please check the ID of your enquiry using 'View Enquiries'.");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    /**
     * Allows a student to edit their own enquiry if it hasn't been answered.
     * @exception java.util.InputMismatchException to cover potential errors encountered during the editing process of a student's enquiry, guaranteeing proper error handling and user guidance.
     * @param student The {@link Student} editing the enquiry.
     */

    public void edit(Student student) {
        try {
            if (enquiryList.isEmpty()) {
                System.out.println("You have not made any enquiries to edit.");
                System.out.println();

            } else {
                ArrayList<Enquiry> enquiries = objectFinder.findEnquiresByUserID(student.getUserID(), enquiryList);
                if (enquiries.isEmpty()) {
                    System.out.println("Your enquiry list is empty. No enquiries to edit.");
                    System.out.println();
                    return;
                }

                System.out.println("Enter the Enquiry ID of the enquiry to be edited: ");
                int enquiryID = scanner.nextInt();
                scanner.nextLine();

                ObjectFinder objectFinder = new ObjectFinder();
                Enquiry editEnquiry = objectFinder.findEnquiryById(enquiryID, enquiryList);

                if (editEnquiry != null) //check if that enquiry object exists
                {
                    if (editEnquiry.getStudentUserID().equals(student.getUserID())) //check if that student made that enquiry
                    {
                        if (!editEnquiry.isStatus()) //check if the enquiry has been answered or not
                        {
                            System.out.println("Enter the edited enquiry content: ");
                            String newContent = scanner.nextLine();
                            enquiryList.update(editEnquiry, newContent);
                            System.out.println("Your enquiry with ID " + enquiryID + " has been edited!");
                            System.out.println();

                        } else {
                            System.out.println("Your enquiry has already been answered. You cannot edit this enquiry.");
                            System.out.println();
                        }
                    } else {
                        System.out.println("You can only edit your own enquiries.");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid Enquiry ID. Please check the ID of your enquiry using 'View Enquiries'.");
                    System.out.println();
                }

            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    /**
     * Displays enquiries made by the student along with their status and answers.
     * @exception IllegalArgumentException to cover potential errors that might occur during the viewing of a student's enquiries, ensuring appropriate error handling and user guidance in case of unexpected issues.
     * @param student The {@link Student} viewing their enquiries.
     */
    public void view(Student student) {
        try {
            if (enquiryList.isEmpty()) {
                System.out.println("You have not made any enquiries yet.");
                System.out.println();

            } else {
                ArrayList<Enquiry> enquiries = objectFinder.findEnquiresByUserID(student.getUserID(), enquiryList);
                if (enquiries.isEmpty()) {
                    System.out.println("Your enquiry list is empty. No enquiries to view.");
                    System.out.println();
                } else {
                    System.out.println("Your Enquiries:");
                    System.out.println("------------");
                    for (Enquiry enquiry : enquiryList.getList()) {
                        if (enquiry.getStudentUserID().equals(student.getUserID())) {
                            System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
                            System.out.println("Enquiry Content: " + enquiry.getEnquiryContent());
                            if (enquiry.isStatus())
                            {
                                System.out.println("Status: Enquiry has been answered");
                                System.out.println("The answer to your enquiry: " + enquiry.getAnswerContent());
                            } else {
                                System.out.println("Status: Enquiry has not been answered");
                            }
                            System.out.println("------------");
                        }
                    }
                    System.out.println();
                }
            }
        }catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine(); // Consume the invalid input
        }
    }
}



