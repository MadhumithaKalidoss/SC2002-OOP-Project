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
 * The {@code CCMCampReportService} class provides functionality to generate a report for a Camp Committee Member (CCM).
 * It allows generating reports containing camp and attendee details based on specified filters.
 */
public class CCMCampReportService {
    private Student campCommittee;
    private ObjectFinder objectFinder = new ObjectFinder();
    private CampList campList;

    /**
     * Generates a camp report for a Camp Committee Member.
     *
     * @param campCommittee The {@link Student} representing the Camp Committee Member.
     * @param campList The {@link CampList} containing the camps.
     * @param OutPath The output path for the generated report.
     */
    public void generateCampReport(Student campCommittee, CampList campList, String OutPath) {
        System.out.println("Generating Camp Report for Camp Committee Member: " + campCommittee.getUserID());

        // Get the camp overseen by the camp committee member
        Camp camp = getCampForCampCommittee(campCommittee, campList);

        if (camp == null) {
            System.out.println("Camp not found. Report generation canceled.");
            return;
        }

        // Create a filter for camp and attendee details
        Filter filter = createFilter();

        // Generate the report based on the selected camp and filter
        generateReport(camp, OutPath, filter);
    }

    /**
     * Generates a report for a specific camp based on given filters.
     * @exception java.util.InputMismatchException handles exceptions related to file operations and provides appropriate error messages in case of failure.
     * @param camp The {@link Camp} for which the report is generated.
     * @param outputPath The output path for the generated report.
     * @param filter The {@link Filter} containing criteria for report generation.
     */
    private void generateReport(Camp camp, String outputPath, Filter filter) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(camp.getCampName() + " Report");

        int rowIndex = 0;
        Row headerRow = sheet.createRow(rowIndex++);
        createHeaderRow(headerRow, filter);

        // Populate data based on the camp and filter
        createRowForCampDetails(sheet, rowIndex++, camp, filter);
        createRowForAttendeeDetails(sheet, rowIndex++, camp, filter);

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
     * Retrieves the camp associated with a Camp Committee Member.
     *
     * @param student The {@link Student} representing the Camp Committee Member.
     * @param campList The {@link CampList} containing the camps.
     * @return The {@link Camp} associated with the Camp Committee Member.
     */
    private Camp getCampForCampCommittee(Student student, CampList campList) {
        for (Camp camp : campList.getCampList()) {
            if (objectFinder.findStudentRoleInCamp(student, camp).equals("Camp Committee Member")) {
                return camp;
            }
        }
        return null;
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
            // Add more options as needed
        }

        if (filter.includeAttendeeDetails()) {
            headerRow.createCell(cellIndex++).setCellValue("Role");
            headerRow.createCell(cellIndex++).setCellValue("User ID");
        }
        headerRow.createCell(cellIndex).setCellValue("Faculty");
    }

    /**
     * Creates rows for attendee details in the report based on specified filters.
     *
     * @param sheet The {@link Sheet} to add the rows to.
     * @param rowIndex The starting index for creating rows.
     * @param camp The {@link Camp} whose attendees' details are included.
     * @param filter The {@link Filter} containing criteria for inclusion.
     */
    private void createRowForAttendeeDetails(Sheet sheet, int rowIndex, Camp camp, Filter filter) {
        for (User attendee : camp.getListOfAttendees()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(3).setCellValue("Attendee"); // Role
            row.createCell(4).setCellValue(attendee.getUserID()); // User ID

            // If faculty information is available, add it to a new column
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
            row.createCell(5).setCellValue(faculty); //Faculty
        }
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

