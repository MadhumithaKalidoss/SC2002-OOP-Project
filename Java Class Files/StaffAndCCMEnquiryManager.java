package sc2002project;

import java.util.Scanner;

public class StaffAndCCMEnquiryManager {
    private EnquiryList enquiryList;

    // Constructor
    public StaffAndCCMEnquiryManager(EnquiryList enquiryList) {
        this.enquiryList = enquiryList;
    }

    ObjectFinder objectFinder= new ObjectFinder();
    CampList campList = new CampList();

    // Submit an answer to an enquiry
    public void submitAnswer(String userID) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter the enquiry id
        System.out.print("Enter the enquiry ID to answer: ");
        int enquiryID = scanner.nextInt();

        // Check if the enquiry list is empty
        if (enquiryList.isEmpty()) {
            System.out.println("The enquiry list is empty. No enquiries to answer.");
            return;
        }

        // Check if the enquiryID exists
        Enquiry enquiry = objectFinder.findEnquiryById(enquiryID, enquiryList);
        if (enquiry == null) {
            System.out.println("Invalid enquiry ID. Please check the ID using the 'view enquiries' option.");
            return;
        }

        // Find the camp name related to the enquiryID (This functionality is not provided in the description)
        // Assuming there is a method getCampNameByEnquiryID(enquiryID) in the EnquiryList class
        String campName = objectFinder.findCampNameByEnquiryID(enquiryID, enquiryList.getEnquiryList());

        // Check if the user is authorized to answer the enquiry
        if (!objectFinder.checkUserIDInCamp(campName, userID, campList.getCampList())) {
            System.out.println("You are not authorized to answer enquiries for this camp.");
            return;
        }

        // Check if the enquiry has already been answered
        if (enquiry.isStatus()) {
            System.out.println("This enquiry has already been answered.");
        } else {
            // Ask the user to enter an answer content
            System.out.print("Enter the answer content: ");
            String answerContent = scanner.nextLine();

            // Add the answer to the enquiry
            enquiryList.addAnswer(enquiry, answerContent);

            // Update the status to true
            enquiry.setStatus(true);

            // Tell the user that the answer has been stored successfully
            System.out.println("The answer has been stored successfully.");
        }
    }

    // View enquiries
    public void viewEnquiries(String userID) {
        if (enquiryList.isEmpty()) {
            System.out.println("Your enquiry list is empty.");
        } else {
            Scanner scanner = new Scanner(System.in);

            // Ask the user for the camp name
            System.out.print("Enter the camp name: ");
            String campName = scanner.nextLine();

            // Check if the campName exists by iterating through the list of camps
            if (objectFinder.findCampByName(campName, campList.getCampList()) == null) {
                System.out.println("Invalid camp name. Please check the camp name.");
                return;
            }

            // Print enquiries for the given campName and userID
            System.out.println("Your enquiries:");

            for (Enquiry enquiry : enquiryList.getEnquiryList()) {
                if (objectFinder.checkUserIDInCampIfExists(campName, userID, campList.getCampList())) {
                    System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
                    System.out.println("Enquiry Text: " + enquiry.getEnquiryContent());
                    System.out.println("Enquiry Status: " + (enquiry.isStatus() ? "Answered" : "Not Answered"));
                    System.out.println();
                }
            }
        }
    }
}


