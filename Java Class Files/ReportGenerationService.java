package sc2002project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGenerationService {
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

        // Create a sheet for each camp
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

    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Camp Name");
        headerRow.createCell(1).setCellValue("Dates");
        headerRow.createCell(2).setCellValue("Location");
        headerRow.createCell(3).setCellValue("Role");
        headerRow.createCell(4).setCellValue("User ID");
    }

    private void populateAttendeeData(Sheet sheet, Camp camp) {
        int rowIndex = 1; // Starting from the second row for data
        for (User attendee : camp.getListofAttendees()) {
            createRowForStudent(sheet, rowIndex++, camp, attendee, "Attendee");
        }
    }

    private void populateCampCommitteeData(Sheet sheet, Camp camp) {
        int rowIndex = 1; // Starting from the second row for data
        for (CampCommittee committeeMember : camp.getListofCampCommittee()) {
            User user = objectFinder.findStudentByUserId(committeeMember.getUserID(), studentList);
            if (user != null) {
                createRowForStudent(sheet, rowIndex++, camp, user, "Camp Committee");
            }
        }
    }

    private void createRowForStudent(Sheet sheet, int rowIndex, Camp camp, User user, String role) {
        Row row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue(camp.getCampName());
        //row.createCell(1).setCellValue(camp.getDates());
        row.createCell(2).setCellValue(camp.getLocation());
        row.createCell(3).setCellValue(role);
        row.createCell(4).setCellValue(user.getUserID());
    }

    private void createRowForPerformance(Sheet sheet, int rowIndex, User user) {
        Row row = sheet.createRow(rowIndex);
        // Assuming user.getUserId() and user.getPoints() are the appropriate methods to retrieve this data
        row.createCell(0).setCellValue(user.getUserID());
        row.createCell(1).setCellValue(user.getPoints());
    }
    
    //add in current date
}

