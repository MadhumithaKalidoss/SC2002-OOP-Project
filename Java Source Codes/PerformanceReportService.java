import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * The {@code PerformanceReportService} class provides functionalities to generate performance reports
 * for selected camps and their committee members.
 */
public class PerformanceReportService {
    ObjectFinder objectFinder = new ObjectFinder();
    private CampList campList;
    private Staff staff;
    private ArrayList<Camp> campsByStaff;

    /**
     * Constructs a {@code PerformanceReportService} instance with the provided staff and camp list.
     *
     * @param staff The {@link Staff} associated with the performance reports.
     * @param campList The {@link CampList} containing the list of camps.
     */
    public PerformanceReportService(Staff staff, CampList campList) {
        this.staff = staff;
        this.campList = campList;
        this.campsByStaff = objectFinder.getCampsByStaffID(staff.getUserID(), campList);
    }

    /**
     * Generates a performance report for a selected camp.
     * @exception IOException during report generation.
     * @param outputPath The output path for the generated report.
     */
    public void generatePerformanceReportForSelectedCamp( String outputPath) {
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

        // Display the camp committee members for the selected camp
        System.out.println("Camp Committee Members for " + selectedCampName + ":");

        for (int i = 0; i < selectedCamp.getListOfCampCommittee().size(); i++) {
            System.out.println((i + 1) + ") " + selectedCamp.getListOfCampCommittee().get(i).getUserID());
        }

        // Prompt user to choose a camp committee member
        int selectedMemberIndex = chooseCampCommitteeMember(selectedCamp.getListOfCampCommittee().size());

        // Retrieve the selected camp committee member
        Student selectedCampCommitteeMember = selectedCamp.getListOfCampCommittee().get(selectedMemberIndex - 1); // not happening

        // Generate the report based on the selected camp and committee member
        generateReport(selectedCampCommitteeMember, outputPath);
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
     * Prompts the user to choose a camp committee member.
     *
     * @param totalMembers The total number of camp committee members available.
     * @return The index of the chosen camp committee member.
     */
    private int chooseCampCommitteeMember(int totalMembers) {
        Scanner scanner = new Scanner(System.in);
        int selectedMemberIndex;

        do {
            System.out.print("Enter the number corresponding to the camp committee member you want to generate the report for: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // consume the invalid input
            }
            selectedMemberIndex = scanner.nextInt();
        } while (selectedMemberIndex < 1 || selectedMemberIndex > totalMembers);

        return selectedMemberIndex;
    }

    /**
     * Generates a performance report for the selected camp committee member.
     * @throws IOException during report generation.
     * @param campCommittee The {@link Student} representing the camp committee member.
     * @param outputPath The output path for the generated report.
     */
    public void generateReport(Student campCommittee, String outputPath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Performance Report");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Category");
        headerRow.createCell(1).setCellValue("Count");
        headerRow.createCell(2).setCellValue("Points");

        // Populate data
        int rowIndex = 1;
        rowIndex = createDataRow(sheet, rowIndex, "Enquiries Replied", campCommittee.getCountEnquiriesReplied(), 1);
        rowIndex = createDataRow(sheet, rowIndex, "Suggestions Given", campCommittee.getCountSuggestionsGiven(), 1);
        rowIndex = createDataRow(sheet, rowIndex, "Approved Suggestions", campCommittee.getCountApprovedSuggestions(), 1);

        // Calculate and add total points row
        int totalPoints = calculateTotalPoints(campCommittee);
        createDataRow(sheet, rowIndex, "Total Points", totalPoints, 1);

        // Write the workbook to an OutputStream or file
        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
            System.out.println("Performance report generated successfully at " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error generating the report.");
        }
    }

    /**
     * Creates a row of data in the Excel sheet.
     *
     * @param sheet The {@link Sheet} to add the row.
     * @param rowIndex The index at which the row is created.
     * @param category The category of the data.
     * @param count The count of occurrences for the category.
     * @param pointsPerCount The points associated with each count.
     * @return The updated row index.
     */
    private int createDataRow(Sheet sheet, int rowIndex, String category, int count, int pointsPerCount) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(category);
        row.createCell(1).setCellValue(count);
        row.createCell(2).setCellValue(count * pointsPerCount);
        return rowIndex;
    }

    /**
     * Calculates the total points for the camp committee member.
     *
     * @param campCommittee The {@link Student} representing the camp committee member.
     * @return The total points earned by the committee member.
     */
    private int calculateTotalPoints(Student campCommittee) {
        int totalPoints = campCommittee.getPoints();

        return totalPoints;
    }
}

