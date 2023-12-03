import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The StaffAndCCMEnquiryManager class provides methods for both staff members and Camp Committee Members (CCMs)
 * to manage and respond to enquiries related to camps.
 */
public class StaffAndCCMEnquiryManager {
    private EnquiryList enquiryList;
    ObjectFinder objectFinder= new ObjectFinder();
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a StaffAndCCMEnquiryManager with the specified EnquiryList.
     *
     * @param enquiryList The EnquiryList to be managed by the StaffAndCCMEnquiryManager.
     */
    public StaffAndCCMEnquiryManager(EnquiryList enquiryList) {
        this.enquiryList = enquiryList;
    }

    /**
     * Allows a staff member to submit an answer to an enquiry.
     * @exception InputMismatchException for invalid integer inputs for the enquiry ID.
     * @param staff    The staff member submitting the answer.
     * @param campList The CampList containing the relevant camps.
     */
    public void submitAnswer(Staff staff, CampList campList) {
        try {
            // Check if the enquiry list is empty
            if (enquiryList.isEmpty()) {
                System.out.println("The enquiry list is empty. No enquiries to answer.");
                System.out.println();
                return;
            }

            // Ask the user to enter the enquiry id
            System.out.print("Enter the enquiry ID to answer: ");
            int enquiryID = scanner.nextInt();

            // Check if the enquiryID exists
            Enquiry enquiry = objectFinder.findEnquiryById(enquiryID, enquiryList);
            if (enquiry == null) {
                System.out.println("Invalid enquiry ID. Please check the ID using the 'View enquiries' option.");
                System.out.println();
                return;
            }

            String campName = enquiry.getCampName();
            Camp camp = objectFinder.findCampByName(campName, campList);
            if (staff == camp.getStaffInCharge()) {
                // Check if the enquiry has already been answered
                if (enquiry.isStatus()) {
                    System.out.println("This enquiry has already been answered.");
                    System.out.println();
                } else {
                    System.out.println("Enquiry: " + enquiry.getEnquiryContent());
                    System.out.println("Enter the answer to the enquiry: ");
                    scanner.nextLine();
                    String answerContent = scanner.nextLine();
                    enquiryList.addAnswer(enquiry, answerContent);
                    System.out.println("The enquiry has been answered!");
                    System.out.println();
                }

            } else {
                System.out.println("You are not authorized to answer enquiries for this camp.");
                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the enquiry ID.");
            scanner.nextLine(); // Consume the invalid input
        }
    }


    /**
     * Allows a staff member to view enquiries related to a specific camp.
     * @exception InputMismatchException  for invalid input when reading the camp name.
     * @param staff    The staff member viewing the enquiries.
     * @param campList The CampList containing the relevant camps.
     */
    // View enquiries
    public void viewEnquiriesAsStaff(Staff staff, CampList campList) {
        try {
            if (enquiryList.isEmpty()) {
                System.out.println("The enquiry list is empty. No enquiries to view.");
                System.out.println();

            } else {
                Scanner scanner = new Scanner(System.in);

                // Ask the user for the camp name
                System.out.println("Enter the name of camp whose enquiries you wish to view: ");
                String campName = scanner.nextLine();
                Camp camp = objectFinder.findCampByName(campName, campList);


                // Check if the campName exists by iterating through the list of camps
                if (camp == null) {
                    System.out.println("Invalid camp name. Please check the camp name.");
                    System.out.println();
                    return;
                }

                if (staff == camp.getStaffInCharge()) {
                    // Print enquiries for the given campName and userID
                    System.out.println("Enquiries made to " + camp.getCampName() + ":");

                    for (Enquiry enquiry : enquiryList.getList()) {
                        if (enquiry.getCampName().equals(camp.getCampName())) {
                            System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
                            System.out.println("Enquiry Text: " + enquiry.getEnquiryContent());
                            System.out.println("Enquiry Status: " + (enquiry.isStatus() ? "Answered" : "Not Answered"));
                            System.out.println("------------");
                        }
                    }
                    System.out.println();

                } else {
                    System.out.println("You are not authorized to view the enquiries of this camp.");
                    System.out.println();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    /**
     * Allows a Camp Committee Member (CCM) to submit an answer to an enquiry.
     * @exception InputMismatchException  for invalid integer inputs for the enquiry ID.
     * @param student  The CCM submitting the answer.
     * @param campList The CampList containing the relevant camps.
     */
    public void submitAnswer(Student student, CampList campList) {
        try {
            // Check if the enquiry list is empty
            if (enquiryList.isEmpty()) {
                System.out.println("The enquiry list is empty. No enquiries to answer.");
                System.out.println();
                return;
            }

            // Ask the user to enter the enquiry id
            System.out.print("Enter the enquiry ID to answer: ");
            int enquiryID = scanner.nextInt();

            // Check if the enquiryID exists
            Enquiry enquiry = objectFinder.findEnquiryById(enquiryID, enquiryList);
            if (enquiry == null) {
                System.out.println("Invalid enquiry ID. Please check the ID using the 'View enquiries' option.");
                System.out.println();
                return;
            }

            String campName = enquiry.getCampName();
            Camp camp = objectFinder.findCampByName(campName, campList);
            if (camp.getListOfCampCommittee().contains(student)) {
                // Check if the enquiry has already been answered
                if (enquiry.isStatus()) {
                    System.out.println("This enquiry has already been answered.");
                    System.out.println();
                } else {
                    System.out.println("Enquiry: " + enquiry.getEnquiryContent());
                    System.out.println("Enter the answer to the enquiry: ");
                    scanner.nextLine();
                    String answerContent = scanner.nextLine();
                    enquiryList.addAnswer(enquiry, answerContent);
                    System.out.println("The enquiry has been answered!");
                    System.out.println();

                    // Add points to student
                    student.addPoints();
                    student.getCountEnquiriesReplied();
                }

            } else {
                System.out.println("You are not authorized to answer enquiries for this camp.");
                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the enquiry ID.");
            scanner.nextLine(); // Consume the invalid input
        }
    }


    /**
     * Allows a Camp Committee Member (CCM) to view enquiries related to a specific camp.
     *
     * @param student The CCM viewing the enquiries.
     * @param camp    The Camp containing the relevant enquiries.
     */

    // View enquiries
    public void viewEnquiriesAsCCM(Student student, Camp camp) {

        boolean foundEnquiries = false;

        if (camp.getListOfCampCommittee().contains(student)) {
            // Print enquiries for the given campName and userID
            System.out.println("Enquiries made to " + camp.getCampName() + ":");

            for (Enquiry enquiry : enquiryList.getList()) {
                if (enquiry.getCampName().equals(camp.getCampName())) {
                    System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
                    System.out.println("Enquiry Text: " + enquiry.getEnquiryContent());
                    System.out.println("Enquiry Status: " + (enquiry.isStatus() ? "Answered" : "Not Answered"));
                    System.out.println("------------");
                    foundEnquiries = true;
                }
            }

            if (!foundEnquiries) {
                System.out.println("No enquiries made for this camp yet.");
            }
            System.out.println();

        } else {
            System.out.println("You are not authorized to view the enquiries of this camp.");
            System.out.println();
        }
    }

}
