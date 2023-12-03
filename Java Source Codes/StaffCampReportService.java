import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * The {@code StaffCampReportService} class provides functionality to generate reports for camps associated with a staff member.
 */
public class StaffCampReportService {
    ObjectFinder objectFinder = new ObjectFinder();
    private CampList campList;
    private ArrayList<Camp> campsByStaff;

    /**
     * Constructs a {@code StaffCampReportService} instance with the provided list of camps associated with the staff.
     *
     * @param campsByStaff The list of {@link Camp} associated with the staff.
     */
    public StaffCampReportService(ArrayList<Camp> campsByStaff) {
        this.campsByStaff = campsByStaff;
    }

    /**
     * Generates a report for a camp associated with the staff.
     *
     * @param staff The {@link Staff} associated with the camps.
     * @param outputPath The output path for the generated report.
     */
    public void generateReportOfCampByStaff(Staff staff, String outputPath) {
        if (campsByStaff == null && staff != null) {
            campsByStaff = objectFinder.getCampsByStaffID(staff.getUserID(), campList);
        }

        // Display the list of camps
        displayCamps();

        // Prompt user to choose a camp for report generation
        String selectedCampName = chooseCamp();
        Camp selectedCamp = findCampByName(selectedCampName);

        if (selectedCamp == null) {
            System.out.println("Invalid camp selection. Report generation canceled.");
            return;
        }

        // Create a filter for camp and attendee details
        Filter filter = createFilter();

        // Generate the report based on the selected camp and filter
        generateReport(selectedCamp, outputPath, filter);
    }

    /**
     * Displays the list of camps associated with the staff.
     */
    public void displayCamps() {
        System.out.println("Camps created by you:");
        if (campsByStaff != null){
            for (int i = 0; i < campsByStaff.size(); i++) {
                System.out.println((i + 1) + ") " + campsByStaff.get(i).getCampName());
            }
        }
        else {
            System.out.println("No camps available.");
        }
    }

    /**
     * Prompts the user to choose a camp for report generation.
     *
     * @return The name of the selected camp for the report.
     */
    public String chooseCamp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the camp for report generation: ");
        String selectedCampName = scanner.nextLine().trim();

        if (selectedCampName.isEmpty()) {
            System.out.println("Invalid camp name. Report generation canceled.");
            return null;
        }

        return selectedCampName;
    }

    /**
     * Finds a camp by its name among the camps associated with the staff.
     *
     * @param campName The name of the camp to search for.
     * @return The {@link Camp} object with the specified name.
     */
    public Camp findCampByName(String campName) {
        for (Camp camp : campsByStaff) {
            if (camp.getCampName().equalsIgnoreCase(campName)) {
                return camp;
            }
        }
        return null;
    }

    /**
     * Creates a filter based on user-provided options.
     *
     * @return The generated {@link Filter} object based on user selections.
     */
    public Filter createFilter() {
        Filter filter = new Filter();

        // Prompt user for filter options
        System.out.print("Include camp details in the report? (yes/no): ");
        filter.setIncludeCampDetails(getUserConfirmation());

        if (filter.includeCampDetails()) {
            // Prompt user for camp details options
            System.out.print("Include camp name? (yes/no): ");
            filter.setIncludeCampName(getUserConfirmation());
            if (filter.includeCampName()) {
                System.out.print("Filter camp names by alphabet? (yes/no): ");
                if (getUserConfirmation()) {
                    System.out.print("Enter the alphabet to filter by: ");
                    String alphabetFilter = getAlphabetFilter();
                    filter.setCampNameAlphabetFilter(alphabetFilter);
                }
            }

            System.out.print("Include start date? (yes/no): ");
            filter.setIncludeStartDate(getUserConfirmation());

            System.out.print("Include end date? (yes/no): ");
            filter.setIncludeEndDate(getUserConfirmation());
        }

        // Prompt user for attendee details options
        System.out.print("Include attendee details in the report? (yes/no): ");
        filter.setIncludeAttendeeDetails(getUserConfirmation());
        if (filter.includeAttendeeDetails()) {
            // Prompt user for additional attendee details options
            System.out.print("Include attendee role? (yes/no): ");
            filter.setIncludeAttendeeRole(getUserConfirmation());
            if (filter.includeAttendeeRole()) {
                System.out.print("Filter roles by alphabet? (yes/no): ");
                if (getUserConfirmation()) {
                    System.out.print("Enter the alphabet to filter by: ");
                    String roleAlphabetFilter = getAlphabetFilter();
                    filter.setRoleAlphabetFilter(roleAlphabetFilter);
                }
            }

            System.out.print("Include attendee user ID? (yes/no): ");
            filter.setIncludeAttendeeUserID(getUserConfirmation());

            // Additional attendee details options
            System.out.print("Filter attendees by name? (yes/no): ");
            if (getUserConfirmation()) {
                System.out.print("Enter the name to filter by: ");
                String attendeeNameFilter = getUserInput();
                filter.setAttendeeNameFilter(attendeeNameFilter);
                System.out.print("Filter names alphabetically? (yes/no): ");
                if (getUserConfirmation()) {
                    System.out.print("Enter the alphabet to filter names alphabetically: ");
                    String nameAlphabetFilter = getAlphabetFilter();
                    filter.setAttendeeNameAlphabetFilter(nameAlphabetFilter);
                }
            }
        }

        return filter;
    }

    /**
     * Retrieves an alphabet filter from user input.
     *
     * @return A single alphabet provided by the user.
     */
    public String getAlphabetFilter() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toUpperCase();

        if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
            return String.valueOf(input.charAt(0));
        } else {
            System.out.println("Invalid input. Please enter a single alphabet.");
            return getAlphabetFilter();
        }
    }

    /**
     * Retrieves user confirmation for yes/no input.
     *
     * @return {@code true} if user input is "yes" or "y", otherwise {@code false}.
     */
    public boolean getUserConfirmation() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    /**
     * Generates a report for a specific camp based on specified filters.
     *
     * @param camp The {@link Camp} for which the report is generated.
     * @param outputPath The output path for the generated report.
     * @param filter The {@link Filter} containing criteria for report generation.
     */
    public void generateReport(Camp camp, String outputPath, Filter filter) {
        // Filter camps based on the user's choices
        List<Camp> filteredCamps = filterCamps(campsByStaff, campFilter -> {
            // Add conditions based on the filter options
            if (filter.includeCampName() && !campFilter.getCampName().equals(camp.getCampName())) {
                return false;
            }

            if (filter.includeStartDate() && !campFilter.getStartDate().equals(camp.getStartDate())) {
                return false;
            }

            if (filter.includeEndDate() && !campFilter.getEndDate().equals(camp.getEndDate())) {
                return false;
            }

            return true; // Include the camp if it passes all conditions
        });

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(camp.getCampName() + " Report");

        int rowIndex = 0;
        Row headerRow = sheet.createRow(rowIndex++);
        createHeaderRow(headerRow, filter);

        // Populate data based on filter options
        for (Camp filteredCamp : filteredCamps) {
            if (filter.includeCampDetails()) {
                createRowForCampDetails(sheet, rowIndex++, filteredCamp, filter);
            }

            if (filter.includeAttendeeDetails()) {
                createRowForAttendeeDetails(sheet, rowIndex++, filteredCamp);
            }
        }

        // Write the workbook to an OutputStream or file
        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
            System.out.println("Report generated successfully at " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error generating the report.");
        }
    }

    /**
     * Creates the header row for the report based on specified filters.
     *
     * @param headerRow The {@link Row} representing the header row.
     * @param filter The {@link Filter} containing criteria for inclusion.
     */
    private void createHeaderRow(Row headerRow, Filter filter) {
        int cellIndex = 0;
        if (filter.includeCampDetails()) {
            if (filter.includeCampName()) {
                headerRow.createCell(cellIndex++).setCellValue("Camp Name");
            }

            if (filter.includeStartDate()) {
                headerRow.createCell(cellIndex++).setCellValue("Start Date");
            }

            if (filter.includeEndDate()) {
                headerRow.createCell(cellIndex++).setCellValue("End Date");
            }
        }

        if (filter.includeAttendeeDetails()) {
            headerRow.createCell(cellIndex++).setCellValue("Role");
            headerRow.createCell(cellIndex++).setCellValue("User ID");
        }
        headerRow.createCell(cellIndex).setCellValue("Faculty");
    }

    /**
     * Creates a row with camp details based on specified filters.
     *
     * @param sheet The {@link Sheet} to add the row to.
     * @param rowIndex The index at which the row is created.
     * @param camp The {@link Camp} whose details are included.
     * @param filter The {@link Filter} containing criteria for inclusion.
     */
    private void createRowForCampDetails(Sheet sheet, int rowIndex, Camp camp, Filter filter) {
        Row row = sheet.createRow(rowIndex);
        int cellIndex = 0;

        if (filter.includeCampName()) {
            row.createCell(cellIndex++).setCellValue(camp.getCampName());
        }

        if (filter.includeStartDate()) {
            Date startDate = camp.getStartDate();
            row.createCell(cellIndex++).setCellValue(formatDate(startDate));
        }

        if (filter.includeEndDate()) {
            Date endDate = camp.getEndDate();
            row.createCell(cellIndex++).setCellValue(formatDate(endDate));
        }
    }

    /**
     * Formats a given date to a specific format.
     *
     * @param date The {@link Date} object to be formatted.
     * @return A formatted {@code String} representation of the date.
     */
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * Creates rows for attendee details in the report based on specified filters.
     *
     * @param sheet The {@link Sheet} to add the rows to.
     * @param rowIndex The starting index for creating rows.
     * @param camp The {@link Camp} whose attendees' details are included.
     */
    private void createRowForAttendeeDetails(Sheet sheet, int rowIndex, Camp camp) {
        for (User attendee : camp.getListOfAttendees()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(3).setCellValue("Attendee"); // Role
            row.createCell(4).setCellValue(attendee.getUserID()); // User ID

            if (attendee instanceof Student) {
                String faculty = ((Student) attendee).getFaculty();
                row.createCell(5).setCellValue(faculty); // Faculty
            }
        }

        for (Student committeeMember : camp.getListOfCampCommittee()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(3).setCellValue("Camp Committee"); // Role
            row.createCell(4).setCellValue(committeeMember.getUserID()); // User ID

            String faculty = committeeMember.getFaculty();
            row.createCell(5).setCellValue(faculty); // Faculty
        }
    }

    /**
     * Filters camps based on the provided predicate.
     *
     * @param camps The list of {@link Camp} to filter.
     * @param filterPredicate The predicate to apply for filtering.
     * @return A filtered list of {@link Camp} based on the predicate.
     */
    // Method to filter camps based on the provided predicate
    public List<Camp> filterCamps(List<Camp> camps, Predicate<Camp> filterPredicate) {
        if (camps == null) {
            return new ArrayList<>();
        }

        return camps.stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user input as a {@code String}.
     */
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

}
