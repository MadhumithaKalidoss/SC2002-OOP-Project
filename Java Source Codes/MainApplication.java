import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
/**
 * Main Application class responsible for managing the CAMs (Camps Application and Management System).
 * Handles login, user authentication, and navigation based on the user role (staff or student).
 */
public class MainApplication {
    static StudentList studentList = new StudentList();
    static StaffList staffList = new StaffList();
    static CampList campList = new CampList();
    static EnquiryList enquiryList = new EnquiryList();
    static SuggestionList suggestionList = new SuggestionList();

    private static Sheet sheet;
    public static boolean isLoggedIn;
    public static boolean closeApplication = false;
    public static boolean firstStaffLogin = true;
    public static boolean firstStudentLogin = true;

    /**
     * @exception InputMismatchException for handling invalid input when expecting an integer input from the user.
     * @exception IOException for potential errors during file I/O operations when reading user data from the XLSX file.
     * @param args
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String file = "/C:/Users/sekar/Desktop/Uni/Year 2/SC2002/Java Source Codes/src/User Data.xlsx/"; //add your own file path

        while (!closeApplication) {
            isLoggedIn = false;

            System.out.println("Welcome to Camps Application and Management System (CAMs)");
            System.out.println("--------------------------------------------------------");

            int role;
            int sheetIndex;

            int validOption = 0;
            do {
                while (true) {
                    try {
                        System.out.println("Are you logging in as a staff or a student?");
                        System.out.println("1. Staff");
                        System.out.println("2. Student");
                        System.out.println("3. Exit Application");

                        role = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        System.out.println();
                        scanner.nextLine(); // Consume the invalid input
                    }
                }


                switch (role) {
                    case 1: // Staff login
                        if (firstStaffLogin) {
                            sheetIndex = getSheetIndex(role);
                            readUserDataFromXLSX(file, sheetIndex, staffList, studentList); // Load user data from the XLSX file
                        }
                        validOption = 1;
                        break;
                    case 2: // Student login
                        if (firstStudentLogin) {
                            sheetIndex = getSheetIndex(role);
                            readUserDataFromXLSX(file, sheetIndex, staffList, studentList); // Load user data from the XLSX file
                        }
                        validOption = 1;
                        break;
                    case 3: //Exit application
                        System.out.println("Exiting Application. Thank you!");
                        validOption = 1;
                        closeApplication = true;
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again...");
                        break;
                }

            } while (validOption == 0);
            scanner.nextLine();


            while (!isLoggedIn) {

                if (firstStaffLogin) {
                    // Call the methods using the instance of StaffList
                    staffList.setDefaultPassword();
                    staffList.setFirstLogin();
                    staffList.setUserIdForEmptyUsers();
                }

                if (firstStudentLogin) {
                    // Call the methods using the instance of StudentList
                    studentList.setDefaultPassword();
                    studentList.setFirstLogin();
                    studentList.setUserIdForEmptyUsers();
                }


                System.out.println("\nEnter your user ID: ");
                String userId = scanner.nextLine();
                System.out.println("Enter your password: ");
                System.out.println("If this is your first time logging in, the default password is 'password' ");
                String password = scanner.nextLine();

                try {
                    if (role == 1) {
                        Staff staff = staffList.findStaffById(userId);

                        //need exception handling
                        if (staff != null && staff.getPassword().equals(password)) {
                            System.out.println("Welcome, " + staff.getName() + " from " + staff.getFaculty() + " faculty!\n");
                            isLoggedIn = true;
                            if (staff.getFirstLogin()) {
                                System.out.println("This is your first time logging into the application. Please reset your password.");
                                boolean passwordMatch;
                                do {
                                    System.out.println("Enter your new password: ");
                                    String newPassword = scanner.nextLine();
                                    if (newPassword.equals(staff.getPassword())) {
                                        System.out.println("Please choose a different password than your current one.");
                                        passwordMatch = true;
                                    } else {
                                        staff.setPassword(newPassword);
                                        System.out.println("New password is set!\n");
                                        staff.setFirstLogin(false);
                                        passwordMatch = false;
                                    }
                                } while (passwordMatch);
                            }
                            // Implement staff-specific functionality here
                            StaffMenu staffMenu = new StaffMenu();
                            firstStaffLogin = staffMenu.staffMenu(staff, studentList, campList, enquiryList, suggestionList);

                        } else {
                            System.out.println("Invalid user ID or password. Please try again.");
                        }

                    } else if (role == 2) {

                        Student student = studentList.findStudentById(userId);

                        if (student != null && student.getPassword().equals(password)) {
                            System.out.println("Welcome, " + student.getName() + " from " + student.getFaculty() + " faculty!\n");
                            isLoggedIn = true;
                            if (student.getFirstLogin()) {
                                System.out.println("This is your first time logging into the application. Please reset your password.");
                                boolean passwordMatch;
                                do {
                                    System.out.println("Enter your new password: ");
                                    String newPassword = scanner.nextLine();
                                    if (newPassword.equals(student.getPassword())) {
                                        System.out.println("Please choose a different password than your current one.");
                                        passwordMatch = true;
                                    } else {
                                        student.setPassword(newPassword);
                                        System.out.println("New password is set!\n");
                                        student.setFirstLogin(false);
                                        passwordMatch = false;
                                    }
                                } while (passwordMatch);
                            }
                            // Implement student-specific functionality here
                            StudentMenu studentMenu = new StudentMenu();
                            firstStudentLogin = studentMenu.studentMenu(student, campList, enquiryList, suggestionList);

                        } else {
                            System.out.println("Invalid user ID or password. Please try again.");
                            System.out.println();
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    System.out.println();
                    scanner.nextLine(); //Consume errorline
                }
            }
        }
    }

    /**
     * Reads user data from the provided XLSX file and populates staff or student lists accordingly.
     * @exception IOException  for potential errors during file I/O operations when reading user data from the XLSX file.
     * @param file         Path to the XLSX file containing user data
     * @param sheetIndex   Index of the sheet containing the required user data (0 for student, 1 for staff)
     * @param staffList    StaffList object to populate with staff data
     * @param studentList  StudentList object to populate with student data
     */
        private static void readUserDataFromXLSX (String file,int sheetIndex, StaffList staffList, StudentList
        studentList){
            if (firstStaffLogin) {
                staffList.getStaffList().clear();
            }
            if (firstStudentLogin) {
                studentList.getStudentList().clear();
            }

            // Implement reading student and staff data from XLSX and adding to respective lists
            try (FileInputStream excelFile = new FileInputStream(new File(file));
                 Workbook workbook = new XSSFWorkbook(excelFile)) {

                sheet = workbook.getSheetAt(sheetIndex);

                for (Row row : sheet) {
                    if (row != null) {
                        Cell nameCell = row.getCell(0);
                        Cell emailCell = row.getCell(1);
                        Cell facultyCell = row.getCell(2);
                        //Cell facultyCell = row.getCell(3);

                        if (nameCell != null && emailCell != null && facultyCell != null) {
                            String name = nameCell.getStringCellValue();
                            String email = emailCell.getStringCellValue();
                            String faculty = facultyCell.getStringCellValue();

                            if (sheetIndex == 0) {
                                studentList.addStudent(new Student(name, email, faculty));
                            } else if (sheetIndex == 1) {
                                staffList.addStaff(new Staff(name, email, faculty));
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    /**
     * Gets the index of the sheet in the XLSX file based on the user role.
     *
     * @param role User role identifier (1 for staff, 2 for student)
     * @return Index of the sheet (0 for student, 1 for staff)
     */
        private static int getSheetIndex ( int role){
            if (role == 1) {
                return 1; // Assuming staff sheet is at index 1
            } else if (role == 2) {
                return 0; // Assuming student sheet is at index 0
            } else {
                return -1; //wont ever return -1
            }
        }
}