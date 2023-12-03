import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The {@code CampManager} class provides methods for managing camps, including creating new camps,
 * editing existing camps, deleting camps, and toggling camp visibility. It interacts with the {@link CampList}
 * to perform these operations. This class is designed to be used by staff members responsible for camp management.
 * <p>
 * The class uses a {@link SimpleDateFormat} to parse and format dates, and it includes a {@link Scanner} for user input.
 * The {@link ObjectFinder} class is used to find camp objects based on their names within the {@link CampList}.
 * <p>
 * The methods in this class handle user input, validate data, and delegate operations to the {@link CampList} class
 * to perform the actual camp management tasks.
 *
 */
public class CampManager {
    private CampList campList;
    SimpleDateFormat dateFormatting = new SimpleDateFormat("dd-MM-yyyy");
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new {@code CampManager} object with a reference to a {@link CampList}.
     *
     * @param campList The {@link CampList} to be managed by this {@code CampManager}.
     */
    public CampManager(CampList campList) {this.campList = campList;}

    /**
     * Creates a new camp with details provided by the user, including camp name, dates, location, user group,
     * and other information. Validates input and ensures that the camp is not a duplicate.
     * @exception InputMismatchException handles exceptions related to user input during the process of creating a new camp, providing appropriate error messages and allowing the user to retry input when exceptions occur.
     * @param staff The staff member creating the camp.
     */
    public void createNewCamp(Staff staff) {

        //camp name
        String campName;
        Camp existingCamp;
        do {
            System.out.println("\nEnter name of the camp:");
            campName = scanner.nextLine();

            existingCamp = objectFinder.findCampByName(campName, campList);
            try {
                if (existingCamp != null) {
                    System.out.println("A camp with the name '" + campName + "' already exists. Please enter a different name.");
                }
            } catch (Exception e) {
                System.out.println("Enter a valid name for the camp that does not already exist.");
            }
        } while (existingCamp != null);


        Date currentDate = new Date(); // Get current date

        // Validate Start Date
        Date parsedStartDate = null;
        do {
            System.out.println("\nEnter the start date of " + campName + " in dd-MM-yyyy format:");
            String startDate = scanner.nextLine();

            try {
                parsedStartDate = dateFormatting.parse(startDate);
                if (parsedStartDate.before(currentDate)) {
                    System.out.println("The start date cannot be in the past. Please enter a valid start date.");
                    parsedStartDate = null; // Reset parsedStartDate to trigger re-entry
                }
            } catch (ParseException e) {
                System.out.println("Error parsing the date. Please enter a valid date in the correct format:");
            }
        } while (parsedStartDate == null);


        // Validate End Date
        Date parsedEndDate = null;
        do {
            System.out.println("\nEnter the end date of " + campName + " in dd-MM-yyyy format:");
            String endDate = scanner.nextLine();

            try {
                parsedEndDate = dateFormatting.parse(endDate);
                if (parsedEndDate.before(currentDate)) {
                    System.out.println("The end date cannot be in the past. Please enter a valid end date.");
                    parsedEndDate = null; // Reset parsedEndDate to trigger re-entry
                } else if (parsedEndDate.before(parsedStartDate)) {
                    System.out.println("The end date cannot be before the start date. Please enter a valid end date.");
                    parsedEndDate = null; // Reset parsedEndDate to trigger re-entry
                }
            } catch (ParseException e) {
                System.out.println("Error parsing the date. Please enter a valid date in the correct format.");
            }
        } while (parsedEndDate == null);


        // Validate Registration Closing Date
        Date parsedRegistrationClosingDate = null;
        do {
            System.out.println("\nEnter the registration closing date of " + campName + " in dd-MM-yyyy format:");
            String registrationClosingDate = scanner.nextLine();

            try {
                parsedRegistrationClosingDate = dateFormatting.parse(registrationClosingDate);
                if (parsedRegistrationClosingDate.before(currentDate)) {
                    System.out.println("The registration closing date cannot be in the past. Please enter a valid date.");
                    parsedRegistrationClosingDate = null; // Reset parsedRegistrationClosingDate to trigger re-entry
                } else if (parsedRegistrationClosingDate.after(parsedStartDate)) {
                    System.out.println("The registration closing date cannot be after the start date. Please enter a valid date.");
                    parsedRegistrationClosingDate = null; // Reset parsedRegistrationClosingDate to trigger re-entry
                }
            } catch (ParseException e) {
                System.out.println("Error parsing the date. Please enter a valid date in the correct format.");
            }
        } while (parsedRegistrationClosingDate == null);


        // faculty
        System.out.println("\nEnter the faculty allowed to attend " + campName + ":");
        boolean validUserGroup = false;
        String userGroup = "";
        do {
            try {
                System.out.println("1. SCSE");
                System.out.println("2. ADM");
                System.out.println("3. EEE");
                System.out.println("4. NBS");
                System.out.println("5. SSS");
                System.out.println("6. Open to any faculty (NTU)");

                int userGroupOption = Integer.parseInt(scanner.nextLine());

                switch (userGroupOption) {
                    case 1:
                        userGroup = "SCSE";
                        validUserGroup = true;
                        break;
                    case 2:
                        userGroup = "ADM";
                        validUserGroup = true;
                        break;
                    case 3:
                        userGroup = "EEE";
                        validUserGroup = true;
                        break;
                    case 4:
                        userGroup = "NBS";
                        validUserGroup = true;
                        break;
                    case 5:
                        userGroup = "SSS";
                        validUserGroup = true;
                        break;
                    case 6:
                        userGroup = "NTU";
                        validUserGroup = true;
                        break;
                    default:
                        System.out.println("Invalid user group option. Please enter a valid option.");
                        System.out.println();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println();
            }
        } while (!validUserGroup);


        //location
        String location = null;
        do {
            try {
                System.out.println("\nEnter the location of " + campName + ":");
                location = scanner.nextLine();

                // Check if the description contains only digits or special symbols
                if (location.matches("[\\d\\p{Punct}]+")) {
                    throw new IllegalArgumentException("Invalid input. Please enter a valid location without just numbers or special symbols.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                location = null; // Resetting location to null to repeat the loop
            }
        } while (location == null);


        //total attendee slots
        int totalSlots = 0;
        do {
            try {
                System.out.println("\nEnter the total slots planned for Camp Attendees for " + campName + ":");
                totalSlots = scanner.nextInt();

                if (totalSlots <= 0) {
                    System.out.print("Enter a positive number above 0.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Consume the invalid input to avoid infinite loop
            }
        } while (true);
        scanner.nextLine(); // Consume newline


        //camp committee slots
        int campCommitteeSlots = 0;

        do {
            try {
                System.out.println("\nEnter the total slots planned for Camp Committee for " + campName + ":");
                campCommitteeSlots = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (campCommitteeSlots > 10) {
                    System.out.println("The maximum total number of slots for camp committee members is 10.");
                    System.out.println("Please enter a number less than or equal to 10");
                } else if (campCommitteeSlots <= 0) {
                    System.out.println("Please enter a positive number above 0.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input to prevent infinite loop
            }
        } while (true);


        //description
        String description = null;
        do {
            try {
                System.out.println("\nEnter the description of " + campName + ":");
                description = scanner.nextLine();

                // Check if the description contains only digits or special symbols
                if (description.matches("[\\d\\p{Punct}]+")) {
                    throw new IllegalArgumentException("Invalid input. Please enter a valid location without just numbers or special symbols.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                description = null; // Resetting location to null to repeat the loop
            }
        } while (description == null);

        // Creating an empty ArrayList for CampCommittee and Attendees
        ArrayList<Student> emptyList1 = new ArrayList<>();
        ArrayList<Student> emptyList2 = new ArrayList<>();

        //visibility
        boolean visibility = false;


        campList.createCamp(
                campName, parsedStartDate, parsedEndDate, parsedRegistrationClosingDate,
                userGroup, location, totalSlots, campCommitteeSlots, description, staff,
                emptyList1, emptyList2, visibility);

        System.out.println("The camp has been created!");
        System.out.println();

    }

    /**
     * Edits an existing camp's details, such as name, dates, location, user group, and more.
     * The staff member editing the camp must be the staff in charge of the camp.
     * @exception InputMismatchException exceptions related to user input during the process of editing an existing camp, providing appropriate error messages and allowing the user to retry input when exceptions occur.
     * @param staff The staff member editing the camp.
     */
    public void editExistingCamp(Staff staff) {

        Date currentDate = new Date(); // Get current date

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps to edit.\n");
            return;
        }

        System.out.println("Enter the name of the camp to be edited: ");
        String campName = scanner.nextLine();

        ObjectFinder objectFinder = new ObjectFinder();
        Camp camp = objectFinder.findCampByName(campName, campList);

        if (camp != null)
        {
            if (camp.getStaffInCharge().getUserID().equals(staff.getUserID()))
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
                        boolean validName = false;
                        String newCampName;
                        Camp existingCamp;
                        do {
                            try {
                                System.out.println("\nEnter new camp name:");
                                newCampName = scanner.nextLine();
                                if (newCampName.trim().isEmpty()) {
                                    throw new IllegalArgumentException("Camp name cannot be empty.");
                                }
                                existingCamp = objectFinder.findCampByName(newCampName, campList);
                                if (existingCamp != null) {
                                    System.out.println("A camp with the name '" + newCampName + "' already exists. Please enter a different name.");
                                } else {
                                    campList.updateCampName(camp, newCampName);
                                    validName = true;
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } while (!validName);
                        break;

                    case 2:
                        boolean validStartDate = false;
                        do {
                            try {
                                System.out.println("Enter new start date in dd-MM-yyyy format:");
                                String startDateString = scanner.nextLine();
                                Date newStartDate = dateFormatting.parse(startDateString);

                                // Check if the new start date is not in the past
                                if (newStartDate.before(currentDate)) {
                                    System.out.println("The start date cannot be in the past.");
                                    continue; // Prompt for a new start date
                                }

                                // Check if the new start date is before the current end date and after the registration closing date
                                if (newStartDate.after(camp.getEndDate()) || newStartDate.before(camp.getRegistrationClosingDate())) {
                                    System.out.println("The start date should be after the registration closing date and before the end date.");
                                    continue; // Prompt for a new start date
                                }

                                campList.updateStartDate(camp, newStartDate);
                                validStartDate = true;
                            } catch (ParseException e) {
                                System.out.println("Invalid date format.");
                            }
                        } while (!validStartDate);
                        break;

                    case 3:
                        boolean validEndDate = false;
                        do {
                            try {
                                System.out.println("Enter new end date in dd-MM-yyyy format:");
                                String endDateString = scanner.nextLine();
                                Date newEndDate = dateFormatting.parse(endDateString);

                                // Check if the new end date is not in the past
                                if (newEndDate.before(currentDate)) {
                                    System.out.println("The end date cannot be in the past.");
                                    continue; // Prompt for a new end date
                                }

                                // Check if the new end date is after the current start date
                                if (newEndDate.before(camp.getStartDate())) {
                                    System.out.println("The end date should be after the start date.");
                                    continue; // Prompt for a new end date
                                }

                                campList.updateEndDate(camp, newEndDate);
                                validEndDate = true;
                            } catch (ParseException e) {
                                System.out.println("Invalid date format.");
                            }
                        } while (!validEndDate);
                        break;

                    case 4:
                        boolean validClosingDate = false;
                        do {
                            try {
                                System.out.println("Enter new registration closing date in dd-MM-yyyy format:");
                                String registrationClosingDateString = scanner.nextLine();
                                Date newClosingDate = dateFormatting.parse(registrationClosingDateString);

                                // Check if the new registration closing date is not in the past
                                if (newClosingDate.before(currentDate)) {
                                    System.out.println("The registration closing date cannot be in the past.");
                                    continue; // Prompt for a new registration closing date
                                }

                                // Check if the new registration closing date is before the current start date
                                if (newClosingDate.after(camp.getStartDate())) {
                                    System.out.println("The registration closing date should be before the start date.");
                                    continue; // Prompt for a new registration closing date
                                }

                                campList.updateRegistrationClosingDate(camp, newClosingDate);
                                validClosingDate = true;
                            } catch (ParseException e) {
                                System.out.println("Invalid date format.");
                            }
                        } while (!validClosingDate);
                        break;

                    case 5:
                        boolean validUserGroup = false;
                        String newUserGroup = "";

                        do {
                            try {
                                System.out.println("Select new user group:");
                                System.out.println("1. SCSE");
                                System.out.println("2. ADM");
                                System.out.println("3. EEE");
                                System.out.println("4. NBS");
                                System.out.println("5. SSS");
                                System.out.println("6. Open to any faculty (NTU)");

                                int userGroupOption = Integer.parseInt(scanner.nextLine());

                                switch (userGroupOption) {
                                    case 1:
                                        newUserGroup = "SCSE";
                                        validUserGroup = true;
                                        break;
                                    case 2:
                                        newUserGroup = "ADM";
                                        validUserGroup = true;
                                        break;
                                    case 3:
                                        newUserGroup = "EEE";
                                        validUserGroup = true;
                                        break;
                                    case 4:
                                        newUserGroup = "NBS";
                                        validUserGroup = true;
                                        break;
                                    case 5:
                                        newUserGroup = "SSS";
                                        validUserGroup = true;
                                        break;
                                    case 6:
                                        newUserGroup = "NTU";
                                        validUserGroup = true;
                                        break;
                                    default:
                                        System.out.println("Invalid user group option. Please enter a valid option.");
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                            }
                        } while (!validUserGroup);

                        campList.updateUserGroup(camp, newUserGroup);
                        break;

                    case 6:
                        String newlocation = null;
                        do {
                            try {
                                System.out.println("\nEnter the location of " + campName + ":");
                                newlocation = scanner.nextLine();

                                // Check if the description contains only digits or special symbols
                                if (newlocation.matches("[\\d\\p{Punct}]+")) {
                                    throw new IllegalArgumentException("Invalid input. Please enter a valid location without just numbers or special symbols.");
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                newlocation = null; // Resetting location to null to repeat the loop
                            }
                            campList.updateLocation(camp, newlocation);
                        }  while (newlocation == null);
                        break;

                    case 7:
                        boolean validTotalSlots = false;
                        int newTotalSlots = 0;

                        while (!validTotalSlots) {
                            try {
                                System.out.println("Enter the new total slots for attendees:");
                                newTotalSlots = Integer.parseInt(scanner.nextLine());
                                validTotalSlots = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }

                        campList.updateTotalSlots(camp, newTotalSlots);
                        break;

                    case 8:
                        boolean validInput = false;
                        while (!validInput) {
                            System.out.println("Enter the new total slots for camp committee members:");
                            try {
                                int newCampCommitteeSlots = Integer.parseInt(scanner.nextLine());
                                if (newCampCommitteeSlots > 10) {
                                    System.out.println("You cannot create more than 10 slots for Camp Committee!");
                                } else {
                                    campList.updateCampCommitteeSlots(camp, newCampCommitteeSlots);
                                    validInput = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }
                        break;

                    case 9:
                        String newDescription = null;
                        do {
                            try {

                                System.out.println("\nEnter the location of " + campName + ":");
                                newDescription = scanner.nextLine();

                                // Check if the description contains only digits or special symbols
                                if (newDescription.matches("[\\d\\p{Punct}]+")) {
                                    throw new IllegalArgumentException("Invalid input. Please enter a valid description without just numbers or special symbols.");
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                newDescription = null; // Resetting location to null to repeat the loop
                            }

                            campList.updateDescription(camp, newDescription);
                        }  while (newDescription == null);
                        break;

                    default:
                        System.out.println("Invalid option");
                        break;
                }
                System.out.println("The camp has been edited!");
                System.out.println();

            } else {
                System.out.println("You are not in-charge on this camp. You cannot edit the details of this camp.");
                System.out.println();
            }
        } else  {
            System.out.println("Camp does not exist. Please check the name of the camp using 'View list of all Camps created by you'.");
            System.out.println();
        }
    }

    /**
     * Deletes an existing camp. The staff member deleting the camp must be the staff in charge of the camp,
     * and the camp must not have registered students or camp committee members.
     * @exception InputMismatchException handles exceptions related to deleting a camp, providing appropriate error messages based on the conditions. Specifically, it checks if the camp exists, if the staff member is in charge, and if there are registered students for the camp before allowing deletion. Error messages are displayed for cases where the camp doesn't exist, the staff member is not in charge, or there are registered students for the camp, preventing deletion.
     * @param staff The staff member deleting the camp.
     */

    public void deleteCamp(Staff staff) {

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps to delete.");
            System.out.println();
            return;
        }

        System.out.println("Enter the name of the camp to be deleted: ");
        String campName = scanner.nextLine();

        Camp camp = objectFinder.findCampByName(campName, campList);

        if (camp != null)
        {
            if (camp.getListOfCampCommittee().isEmpty() || camp.getListOfAttendees().isEmpty()) {
                if (camp.getStaffInCharge().getUserID().equals(staff.getUserID())) {
                    campList.deleteCamp(camp);
                    System.out.println("The camp has been deleted!");
                    System.out.println();

                } else {
                    System.out.println("You are not in-charge on this camp. You cannot delete this camp.");
                    System.out.println();
                }
            } else  {
                System.out.println("Camp already has registered students. You are not allowed to delete the camp!");
                System.out.println();
            }
        } else  {
            System.out.println("Camp does not exist. Please check the name of the camp using 'View list of all Camps created by you'.");
            System.out.println();
        }
    }

    /**
     * Toggles the visibility of an existing camp. The staff member toggling visibility must be the staff in charge
     * of the camp. Visibility can be turned on or off, affecting whether the camp is visible to students in the target user group.
     * @exception InputMismatchException handles exceptions related to user input when trying to find and edit the visibility of a camp, providing an "Invalid input" message if an exception occurs during the process.
     * @param staff The staff member toggling camp visibility.
     */

    //Toggle existing camp visibility
    public void toggleCampVisibility(Staff staff) {

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps to edit visibility.");
            System.out.println();
            return;
        }

        Camp camp = null;
        try {
            System.out.println("Enter the name of the camp to edit visibility of: ");
            String campName = scanner.nextLine();

            camp = objectFinder.findCampByName(campName, campList);
            if (camp == null) {
                System.out.println("Camp not found. Please check the name of the camp using 'View list of all Camps created by you'.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }

        if (camp != null) {

            if (camp.getStaffInCharge().getUserID().equals(staff.getUserID())) {
                System.out.println("Do you want to turn the visibility of this camp to students on or off?");
                String visibility = scanner.nextLine();

                if (visibility.equalsIgnoreCase("on")) {
                    campList.updateVisibilityToggle(camp, true);
                    System.out.println("Visibility of " + camp.getCampName() + " turned on.");
                    System.out.println();
                } else if (visibility.equalsIgnoreCase("off")) {
                    campList.updateVisibilityToggle(camp, false);
                    System.out.println("Visibility of " + camp.getCampName() + " turned off.");
                    System.out.println();
                } else
                    System.out.println("Invalid option.");

            } else {
                System.out.println("You are not in-charge on this camp. You are not allowed to edit the visibility of this camp.");
                System.out.println();
            }
        } else {
            System.out.println("Camp does not exist. Please check the name of the camp using 'View list of all Camps created by you'.");
            System.out.println();
        }
    }

}

