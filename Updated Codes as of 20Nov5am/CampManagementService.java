import java.util.Scanner;
import java.util.ArrayList;
//import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CampManagementService {

    //Creating instance of CampList and assigning the master 'campList' to the object CampList.
    private CampList campList;
    private SimpleDateFormat dateFormatting = new SimpleDateFormat("dd-MM-yyyy");
    public CampManagementService(CampList campList) {
        this.campList = campList;
    }


    //Creating camp (Case 1)
    public void createNewCamp(Staff staff, CampList campList) {
        Scanner scanner = new Scanner(System.in);

        //check if a camp already exists with existing name
        System.out.println("\nEnter Camp Name:");
        String campName = scanner.nextLine();

        System.out.println("\nEnter the start date of " + campName + ":");
        String startDate = scanner.nextLine();

        System.out.println("\nEnter the end date of " + campName + ":");
        String endDate = scanner.nextLine();

        System.out.println("\nEnter the registration closing date of " + campName + ":");
        String registrationClosingDate = scanner.nextLine();

        System.out.println("\nEnter the faculty allowed to attend " + campName + ":");
        String userGroup = scanner.nextLine();

        System.out.println("\nEnter the location of " + campName + ":");
        String location = scanner.nextLine();

        int totalSlots;
        System.out.println("\nEnter the total slots planned for " + campName + ":");
        totalSlots = scanner.nextInt();
        scanner.nextLine();
        
        int campCommitteeSlots;
        System.out.println("\nEnter the total slots planned for Camp Committee for " + campName + ":");
        while (true) {
        	campCommitteeSlots = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (campCommitteeSlots > 10) {
                System.out.println("The maximum total number of slots that can be allocated to camp committee members is 10.");
                System.out.println("Please enter a number less than or equal to 10:");
            } else 
            
                break;
        }

        System.out.println("\nEnter the description for the " + campName + ":");
        String description = scanner.nextLine();
        
        // Create an empty ArrayList for CampCommittee and Attendees
        ArrayList<CampCommittee> emptyList1 = new ArrayList<>();
        ArrayList<Student> emptyList2 = new ArrayList<>();
        emptyList1.clear();
        emptyList2.clear();
        
        boolean visibility = false;

        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Adjust the format based on your input
        Date parsedStartDate = null;
        Date parsedEndDate = null;
        Date parsedRegistrationClosingDate = null;

        try {
            parsedStartDate = dateFormatting.parse(startDate);
            parsedEndDate = dateFormatting.parse(endDate);
            parsedRegistrationClosingDate = dateFormatting.parse(registrationClosingDate);
        } catch (ParseException e) {
            System.out.println("Error parsing dates. Please enter dates in the correct format.");
            //return null; // Or handle the error in an appropriate way for your application
        }

        // Now you can pass the parsed dates to the CreateCamp method
        campList.CreateCamp(
                campName, parsedStartDate, parsedEndDate, parsedRegistrationClosingDate,
                userGroup, location, totalSlots, campCommitteeSlots, description, staff,
                emptyList1, emptyList2, visibility);
        
    }

    // Editing Camp (Case 2)
    public void editExistingCamp(Staff staff, CampList campList) {
        Scanner scanner = new Scanner(System.in);

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps to edit.\n");
            return;
        }

        System.out.println("Enter the name of the camp to be edited: ");
        String campName = scanner.nextLine();

        ObjectFinder objectFinder = new ObjectFinder();
        Camp camp = objectFinder.findCampByName(campName, campList.getCampList());

        if (camp != null)
        {
            if (camp.getStaffinCharge().getUserID().equals(staff.getUserID()))
            {
                System.out.println("Select an option to edit:");
                System.out.println("[1] Camp Name");
                System.out.println("[2] Start Date");
                System.out.println("[3] End Date");
                System.out.println("[4] Registration Closing Date");
                System.out.println("[5] User Group");
                System.out.println("[6] Location");
                System.out.println("[7] Total Slots");
                System.out.println("[8] Camp Committee Slots");
                System.out.println("[9] Description");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {

                    case 1:
                        System.out.println("Enter new camp name:");
                        String newCampName = scanner.nextLine();
                        campList.updateCampName(camp, newCampName);
                        break;

                    case 2:
                        System.out.println("Enter new start date:");

                        // Read the new start date as a String
                        String startDateString = scanner.nextLine();

                        // Convert the String to a Date
                        try {
                            Date newStartDate = dateFormatting.parse(startDateString);
                            campList.updateStartDate(camp, newStartDate);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
                        }
                        break;

                    case 3:
                        System.out.println("Enter new end date:");

                        // Read the new end date as a String
                        String endDateString = scanner.nextLine();

                        // Convert the String to a Date
                        try {
                            Date newEndDate = dateFormatting.parse(endDateString);
                            campList.updateEndDate(camp, newEndDate);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                        }
                        break;

                    case 4:
                        System.out.println("Enter new registration closing date:");

                        //Read the new registration closing date as a String
                        String RegistrationClosingDateString = scanner.nextLine();

                        //Convert to String to a Date
                        try {
                            Date newEndDate = dateFormatting.parse(RegistrationClosingDateString);
                            campList.updateRegistrationClosingDate(camp, newEndDate);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                        }
                        break;

                    case 5:
                        System.out.println("Select new user group:");
                        System.out.println("1. SCSE");
                        System.out.println("2. ADM");
                        System.out.println("3. EEE");
                        System.out.println("4. NBS");
                        System.out.println("5. SSS");
                        System.out.println("6. Open to any faculty (NTU)");

                        int userGroupOption = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        String newUserGroup;

                        switch (userGroupOption) {
                            case 1:
                                newUserGroup = "SCSE";
                                break;
                            case 2:
                                newUserGroup = "ADM";
                                break;
                            case 3:
                                newUserGroup = "EEE";
                                break;
                            case 4:
                                newUserGroup = "NBS";
                                break;
                            case 5:
                                newUserGroup = "SSS";
                                break;
                            case 6:
                                newUserGroup = "Open to any faculty in NTU";
                                break;
                            default:
                                newUserGroup = "Unknown";
                                System.out.println("Invalid user group option");
                        }

                        campList.updateUserGroup(camp, newUserGroup);
                        break;

                    case 6:
                        System.out.println("Enter the new location");
                        String newLocation = scanner.nextLine();
                        campList.updateLocation(camp, newLocation);
                        break;

                    case 7:
                        System.out.println("Enter the new total slots for attendees");
                        try {
                            int newTotalSlots = Integer.parseInt(scanner.nextLine());
                            campList.updateTotalSlots(camp, newTotalSlots);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer for Total Slots.");
                        }
                        break;

                    case 8:
                        System.out.println("Enter the new total slots for camp committee members");
                        try {
                            int newCampCommitteeSlots = Integer.parseInt(scanner.nextLine());
                            campList.updateTotalSlots(camp, newCampCommitteeSlots);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer for Total Slots.");
                        }
                        break;

                    case 9:
                        System.out.println("Enter the new camp description");
                        String newDescription = scanner.nextLine();
                        campList.updateLocation(camp, newDescription);
                        break;

                    default:
                        System.out.println("Invalid option");
                        break;
                }

            } else {
                System.out.println("You are not in-charge on this camp. You cannot edit the details of this camp.\n");
                scanner.nextLine();
            }
        } else  {
            System.out.println("Camp does not exist. Please check the name of the camp using 'View Camps Created'.\n");
            scanner.nextLine();
        }
    }

    //Deleting camp - (Case 3)
    public void deleteCamp(Staff staff, CampList campList) {
        Scanner scanner = new Scanner(System.in);

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps to delete.\n");
            return;
        }

        System.out.println("Enter the name of the camp to be deleted: ");
        String campName = scanner.nextLine();

        ObjectFinder objectFinder = new ObjectFinder();
        Camp camp = objectFinder.findCampByName(campName, campList.getCampList());

        if (camp != null)
        {
            if (camp.getStaffinCharge().getUserID().equals(staff.getUserID()))
            {
                campList.deleteCamp(camp);

            } else {
                System.out.println("You are not in-charge on this camp. You cannot delete this camp.\n");
                scanner.nextLine();
            }
        } else  {
            System.out.println("Camp does not exist. Please check the name of the camp using 'View Camps Created'.\n");
            scanner.nextLine();
        }
    }

    }

