package sc2002project;

import java.util.List;

public class CampViewService {
    private CampList campList;
    private Staff staff;
    private ObjectFinder objectFinder;


    public CampViewService(CampList campList, Staff staff) {
        this.campList = campList;
        this.staff = staff;
    }

    public void viewAllCamps() {
        System.out.println("View all the camps");
        campList.viewAllCamps();
    }
    
    public void viewOwnCamps(String userId, StaffList staffList, CampList campList) {
        // 1. Use the findStaffByUserId method to find the staff based on the provided userId.
        Staff staff = objectFinder.findStaffByUserId(userId, staffList);

        // Traverse through each camp in CampList
        for (Camp camp : campList.getCampList()) {
            // Check if camp is open to the student's faculty and visibilityToggle is true
            if (staff != null && camp.getStaffinCharge() == staff) {
                // Print the camp details
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Description: " + camp.getDescription());
                System.out.println("Location: " + camp.getLocation());
                System.out.println("Start Date: " + camp.getStartDate());
                System.out.println("End Date: " + camp.getEndDate());
                System.out.println("------------");
            }

                /*if (staff != null) {
            // 2. Retrieve the camps created by the found staff.
            List<Camp> staffCamps = campList.getCampsByStaff(staff);

            // 3. Display the camps created by the staff.
            if (!staffCamps.isEmpty()) {
                System.out.println("Camps created by " + staff.getName() + ":");
                for (Camp camp : staffCamps) {
                    System.out.println("- " + camp.getCampName());
                    // Add more details to display if needed
                }
            } else {
                System.out.println("No camps found for " + staff.getName());
            }
        } else {
            System.out.println("Staff not found for userId: " + userId);*/
        }
    }

   /* private void displayCamps(List<Camp> camps) {
        for (Camp camp : camps) {
            displayCampDetails(camp);
        }
    }

    private void displayCampDetails(Camp camp) {
        System.out.println("Camp Name: " + camp.getCampName());
        // Display other relevant details
        System.out.println("--------------------------");
    }*/
}
