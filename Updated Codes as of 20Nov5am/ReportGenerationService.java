/*import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerationService {
    private Staff staff;
    private ObjectFinder objectFinder;
    private ArrayList<Camp> campsByStaff;

    public ReportGenerationService(Staff staff, ArrayList<Camp> campsByStaff) {
        this.staff = staff;
        this.campsByStaff = campsByStaff;
        this.objectFinder = new ObjectFinder();
    }

    public void generateReportOfCampByStaff(String outputPath) {
        // Display all camps created by the staff
        displayStaffCamps();

        // Prompt user to choose a camp for report generation
        int selectedCampIndex = chooseCamp();
        if (selectedCampIndex == -1) {
            System.out.println("Invalid camp selection. Report generation canceled.");
            return;
        }

        Camp selectedCamp = campsByStaff.get(selectedCampIndex);

        // Create a filter for camp and attendee details
        Filter filter = createFilter();

        // Generate the report based on the selected camp and filter
        generateReport(selectedCamp, outputPath, filter);
    }

    private void displayStaffCamps() {
        System.out.println("Camps created by the staff:");
        for (int i = 0; i < campsByStaff.size(); i++) {
            System.out.println((i + 1) + ". " + campsByStaff.get(i).getCampName());
        }
    }

    private int chooseCamp() {
        System.out.print("Choose a camp for report generation (enter the number): ");
        int selectedCampIndex = -1;
        try {
            selectedCampIndex = Integer.parseInt(System.console().readLine()) - 1;
            if (selectedCampIndex < 0 || selectedCampIndex >= campsByStaff.size()) {
                selectedCampIndex = -1;
            }
        } catch (NumberFormatException | NullPointerException e) {
            // Handle invalid input
        }
        return selectedCampIndex;
    }

    private Filter createFilter() {
        Filter filter = new Filter();

        // Prompt user for filter options
        System.out.print("Include camp details in the report? (yes/no): ");
        filter.setIncludeCampDetails(getUserConfirmation());

        if (filter.includeCampDetails()) {
            // Prompt user for camp details options
            System.out.print("Include camp name? (yes/no): ");
            filter.setIncludeCampName(getUserConfirmation());

            System.out.print("Include start date? (yes/no): ");
            filter.setIncludeStartDate(getUserConfirmation());

            System.out.print("Include end date? (yes/no): ");
            filter.setIncludeEndDate(getUserConfirmation());
            // Add more options as needed
        }

        // Prompt user for attendee details options
        System.out.print("Include attendee details in the report? (yes/no): ");
        filter.setIncludeAttendeeDetails(getUserConfirmation());

        return filter;
    }

    private boolean getUserConfirmation() {
        String input = System.console().readLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    private void generateReport(Camp camp, String outputPath, Filter filter) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(camp.getCampName() + " Report");

        int rowIndex = 0;
        Row headerRow = sheet.createRow(rowIndex++);
        createHeaderRow(headerRow, filter);

        // Populate data based on filter options
        if (filter.includeCampDetails()) {
            createRowForCampDetails(sheet, rowIndex++, camp, filter);
        }

        if (filter.includeAttendeeDetails()) {
            createRowForAttendeeDetails(sheet, rowIndex++, camp);
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
            headerRow.createCell(cellIndex).setCellValue("User ID");
        }
    }

    private void createRowForCampDetails(Sheet sheet, int rowIndex, Camp camp, Filter filter) {
        Row row = sheet.createRow(rowIndex);
        int cellIndex = 0;
        if (filter.includeCampName()) {
            row.createCell(cellIndex++).setCellValue(camp.getCampName());
        }

        if (filter.includeStartDate()) {
            row.createCell(cellIndex++).setCellValue(camp.getStartDate());
        }

        if (filter.includeEndDate()) {
            row.createCell(cellIndex++).setCellValue(camp.getEndDate());
        }
        // Add more options as needed
    }

    private void createRowForAttendeeDetails(Sheet sheet, int rowIndex, Camp camp) {
        for (User attendee : camp.getListofAttendees()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue("Attendee");
            row.createCell(1).setCellValue(attendee.getUserID());
        }

        for (CampCommittee committeeMember : camp.getListofCampCommittee()) {
            User user = objectFinder.findUserById(committeeMember.getUserID(), staff.getFaculty());
            if (user != null) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue("Camp Committee");
                row.createCell(1).setCellValue(user.getUserID());
            }
        }

    }

}*/





/*import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;*/

/*public class ReportGenerationService {
    private Staff staff;
    private ObjectFinder objectFinder;
    private ArrayList<Camp> campsByStaff;
    private StudentList studentList;

    public ReportGenerationService(Staff staff, ArrayList<Camp> campsByStaff) {
        this.staff = staff;
        this.campsByStaff = campsByStaff;
        this.objectFinder = new ObjectFinder();
    }

    public void generateReportOfCampByStaff(String filter, String outputPath) {
        Workbook workbook = new XSSFWorkbook();

        //Create a sheet for each camp
        for (Camp camp : campsByStaff) {
            Sheet sheet = workbook.createSheet(camp.getCampName());
            createHeaderRow(sheet);

            // Check the filter and populate data accordingly
            switch (filter.toLowerCase()) {
                case "attendee":
                    populateAttendeeData(sheet, camp);
                    break;
                case "campcommittee":
                    populateCampCommitteeData(sheet, camp);
                    break;
                // Add more cases for additional filters if needed
                default:
                    System.out.println("Invalid filter.");
            }
        }

        // Write the workbook to an OutputStream or file
        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePerformanceReport(String outputPath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Performance Report");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("User ID");
        headerRow.createCell(1).setCellValue("Points");

        // Populate data
        int rowIndex = 1; // Starting from the second row for data
        for (Camp camp : campsByStaff) {
            for (CampCommittee committeeMember : camp.getListofCampCommittee()) {
                User user = objectFinder.findStudentByUserId(committeeMember.getUserID(), studentList);
                if (user != null) {
                    createRowForPerformance(sheet, rowIndex++, user);
                }
            }
        }

        // Write the workbook to an OutputStream or file
        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Write the workbook to an OutputStream or file
        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        private void createHeaderRow (Sheet sheet){
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Camp Name");
            headerRow.createCell(1).setCellValue("Dates");
            headerRow.createCell(2).setCellValue("Location");
            headerRow.createCell(3).setCellValue("Role");
            headerRow.createCell(4).setCellValue("User ID");
        }

        private void populateAttendeeData (Sheet sheet, Camp camp){
            int rowIndex = 1; // Starting from the second row for data
            for (User attendee : camp.getListofAttendees()) {
                createRowForStudent(sheet, rowIndex++, camp, attendee, "Attendee");
            }
        }

        private void populateCampCommitteeData (Sheet sheet, Camp camp){
            int rowIndex = 1; // Starting from the second row for data
            for (CampCommittee committeeMember : camp.getListofCampCommittee()) {
                User user = objectFinder.findStudentByUserId(committeeMember.getUserID(), studentList);
                if (user != null) {
                    createRowForStudent(sheet, rowIndex++, camp, user, "Camp Committee");
                }
            }
        }

        private void createRowForStudent (Sheet sheet,int rowIndex, Camp camp, User user, String role){
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(camp.getCampName());
            //row.createCell(1).setCellValue(camp.getDates());
            row.createCell(2).setCellValue(camp.getLocation());
            row.createCell(3).setCellValue(role);
            row.createCell(4).setCellValue(user.getUserID());
        }

        private void createRowForPerformance (Sheet sheet,int rowIndex, User user){
            Row row = sheet.createRow(rowIndex);
            // Assuming user.getUserId() and user.getPoints() are the appropriate methods to retrieve this data
            row.createCell(0).setCellValue(user.getUserID());
            row.createCell(1).setCellValue(user.getPoints());
        }

        //add in current date
    }
}*/


