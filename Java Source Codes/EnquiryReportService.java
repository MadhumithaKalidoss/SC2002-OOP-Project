import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
/**
 * The {@code EnquiryReportGenerationService} class provides functionality to generate an Excel report for the enquiries
 * related to a specific camp. It interacts with an {@link EnquiryList} to retrieve relevant enquiries and uses Apache POI
 * to create an Excel workbook for the report.
 *
 * <p>Usage Example:</p>
 * <pre>
 * {@code
 * EnquiryList enquiryList = new EnquiryList();
 * CampList campList = new CampList();
 * Staff staff = new Staff(); // Assuming an instance of Staff is created
 * EnquiryReportGenerationService reportService = new EnquiryReportGenerationService(enquiryList);
 * reportService.generateEnquiryReport(staff, campList);
 * }
 * </pre>
 */
public class EnquiryReportService {

    private EnquiryList enquiryList;
    ObjectFinder objectFinder = new ObjectFinder();
    Scanner scanner = new Scanner(System.in);
    /**
     * Constructs an {@code EnquiryReportGenerationService} instance with the provided {@link EnquiryList}.
     *
     * @param enquiryList The {@link EnquiryList} used for managing enquiries.
     */
    public EnquiryReportService(EnquiryList enquiryList) {
        this.enquiryList = enquiryList;
    }

    /**
     * Generates an Excel report for enquiries related to a specific camp. The report includes details such as
     * Enquiry ID, Student UserID, Enquiry Text, and Answer Content. The report is created only if the staff member
     * is in charge of the specified camp and if there are enquiries for the camp.
     *
     * @param staff    The {@link Staff} member requesting the enquiry report.
     * @param campList The {@link CampList} used to identify the relevant camp for the report.
     */
    public void generateEnquiryReport(Staff staff, CampList campList) {
        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps.");
            System.out.println();
            return;
        }

        System.out.println("Enter the name of the camp for enquiry report generation: ");
        String campName = scanner.nextLine();
        Camp camp = objectFinder.findCampByName(campName, campList);

        ArrayList<Enquiry> enquiriesForCamp = new ArrayList<>();

        if (camp != null) {
            if (camp.getStaffInCharge().equals(staff)) {

                for (Enquiry enquiry : enquiryList.getList()) {
                    if (enquiry.getCampName().equalsIgnoreCase(campName)) {
                        enquiriesForCamp.add(enquiry);
                    }
                }
            } else {
                System.out.println("You are not in-charge of this camp. You are not allowed to create an enquiry report for this camp.");
                System.out.println();
                return;
            }

        } else {
            System.out.println("Invalid Camp Name. Please check the name of the camp using 'View list of all Camps created by you'.");
            System.out.println();
            return;
        }

        // Generate Excel report if there are enquiries for the camp
        if (!enquiriesForCamp.isEmpty()) {
            createEnquiryReportExcel(enquiriesForCamp, campName);
        } else {
            System.out.println("There are no enquiries for this camp.");
            System.out.println();
        }
    }
    /**
     * Generates an Excel report for enquiries related to the specific camp that the camp committee member is part of.
     * The report includes details such as Enquiry ID, Student UserID, Enquiry Text, and Answer Content.
     *
     * @param student The {@link Student} involved in the camp committee.
     * @param camp    The {@link Camp} relevant for the report.
     */
    public void generateEnquiryReportCCM(Student student, Camp camp) {

        ArrayList<Enquiry> enquiriesForCamp = new ArrayList<>();

        if (camp != null) {
            if (camp.getListOfCampCommittee().contains(student)) {

                for (Enquiry enquiry : enquiryList.getList()) {
                    if (enquiry.getCampName().equalsIgnoreCase(camp.getCampName())) {
                        enquiriesForCamp.add(enquiry);
                    }
                }
            } else {
                System.out.println("You are not the committee member of this camp. You are not allowed to create an enquiry report for this camp.");
                System.out.println();
                return;
            }

        }

        // Generate Excel report if there are enquiries for the camp
        if (!enquiriesForCamp.isEmpty()) {
            createEnquiryReportExcel(enquiriesForCamp, camp.getCampName());
        } else {
            System.out.println("There are no enquiries for this camp.");
            System.out.println();
        }
    }

    /**
     * Creates an Excel report for a given list of enquiries and a specified camp. The report includes header columns
     * for Enquiry ID, Student UserID, Enquiry Text, and Answer Content. The data rows contain corresponding details
     * for each enquiry.
     * @exception IOException for potential errors during file I/O operations when generating the Excel report or closing the workbook.
     * @param enquiries The list of {@link Enquiry} objects for the specified camp.
     * @param campName  The name of the camp for which the report is generated.
     */
    private void createEnquiryReportExcel(ArrayList<Enquiry> enquiries, String campName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Enquiry Report - " + campName);

        // Creating header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Enquiry ID", "Student UserID", "Enquiry Text", "Answer Content"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Populating data rows
        int rowNum = 1;
        for (Enquiry enquiry : enquiries) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(enquiry.getEnquiryID());
            row.createCell(1).setCellValue(enquiry.getStudentUserID());
            row.createCell(2).setCellValue(enquiry.getEnquiryContent());

            String answer = enquiry.getAnswerContent() != null ? enquiry.getAnswerContent() : "Not Answered";
            row.createCell(3).setCellValue(answer);
        }

        // Write the workbook to an Excel file
        try (FileOutputStream fileOut = new FileOutputStream("Enquiry_Report_" + campName + ".xlsx")) {
            workbook.write(fileOut);
            System.out.println("Enquiry report for " + campName + " generated successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while generating the enquiry report.");
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

