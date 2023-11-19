import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainApp {
	//private static StudentList studentList = new StudentList();
	//private static StaffList staffList = new StaffList();
	static StudentList studentList = new StudentList();
	static StaffList staffList = new StaffList();
	static CampList campList = new CampList();
	static EnquiryList enquiryList = new EnquiryList();
	static SuggestionList suggestionList = new SuggestionList();
	static ObjectFinder objectFinder = new ObjectFinder();


	static StudentEnquiryManager studentEnquiryManager = new StudentEnquiryManager(enquiryList);
	static CampRegistrationValidation validation = new CampRegistrationValidation();
	static CampRegistration campRegistration = new CampRegistration(campList, objectFinder, validation, studentList);
	static CheckCampDetails checkCampDetails = new CheckCampDetails(campList, objectFinder, studentList);
	static CampWithdrawal campWithdrawal = new CampWithdrawal(campList, objectFinder, studentList);


	static CCMSuggestionManager suggestionManager = new CCMSuggestionManager(suggestionList); // Pass the list to the manager
	static StaffAndCCMEnquiryManager staffAndCCMEnquiryManager = new StaffAndCCMEnquiryManager(enquiryList);

	static CampManagementService campManagementService = new CampManagementService(campList);
	static CampViewService campViewService = new CampViewService();

	static CampVisibilityService campVisibilityService = new CampVisibilityService(campList);
	static SuggestionApprovalService suggestionApproval  = new SuggestionApprovalService(suggestionList);


	private static Sheet sheet;
	public static boolean isLoggedIn;
	public static boolean closeApplication = false;
	public static boolean firstStaffLogin = true;
	public static boolean firstStudentLogin = true;

	public static void main(String[] args) {

		/*StaffList staffList = new StaffList();
		StudentList studentList = new StudentList();*/


		Scanner scanner = new Scanner(System.in);
		String file = "/C:/Users/Madhumitha K/Documents/Java/IntelliJ/sc2002project/src/User Data.xlsx/";

		while (!closeApplication) {
			isLoggedIn = false;

			System.out.println("Welcome to Camps Application and Management System (CAMs)");
			System.out.println("--------------------------------------------------------");

			int role;
			int sheetIndex;

			int validOption = 0;
			do {
				System.out.println("Are you logging in as a staff or a student?");
				System.out.println("1. Staff");
				System.out.println("2. Student");
				System.out.println("3. Exit Application");

				role = scanner.nextInt();

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
						closeApplication = true;
						validOption = 1;
						//break;
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

				if (role == 1) {
					Staff staff = staffList.findStaffById(userId);

					//exception handling
					if (staff != null && staff.getPassword().equals(password)) {
						System.out.println("Welcome, " + staff.getName() + " from " + staff.getFaculty() + " faculty!\n");
						isLoggedIn = true;
						if (staff.getFirstLogin()) {
							System.out.println("This is your first time logging into the application. Please reset your password.");
							System.out.println("Enter your new password: ");
							String newPassword = scanner.nextLine();
							staff.setPassword(newPassword);
							System.out.println("New password is set!\n");
							staff.setFirstLogin(false);

						}

						// Implement staff-specific functionality here
						staffMenu(staff);
					} else {
						System.out.println("Invalid user ID or password. Please try again. as staff");
					}
				} else if (role == 2) {
					Student student = studentList.findStudentById(userId);

					if (student != null && student.getPassword().equals(password)) {
						System.out.println("Welcome, " + student.getName() + " from " + student.getFaculty() + " faculty!\n");
						isLoggedIn = true;
						if (student.getFirstLogin()) {
							System.out.println("This is your first time logging into the application. Please reset your password.");
							System.out.println("Enter your new password: ");
							String newPassword = scanner.nextLine();
							student.setPassword(newPassword);
							System.out.println("New password is set!\n");
							student.setFirstLogin(false);
						}

						// Implement student-specific functionality here
						studentMenu(student);
					} else {
						System.out.println("Invalid user ID or password. Please try again. as student");
					}
				}
			}
		}
	}

	private static void readUserDataFromXLSX(String file, int sheetIndex, StaffList staffList, StudentList studentList) {
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

	private static int getSheetIndex(int role) {
		if (role == 1) {
			return 1; // Assuming staff sheet is at index 1
		} else if (role == 2) {
			return 0; // Assuming student sheet is at index 0
		} else {
			return -1; //wont ever return -1
		}
	}

	private static void staffMenu(Staff staff) {
		Scanner scanner = new Scanner(System.in);
		//System.out.println("Welcome " + staff.getName() + "!");

		while (true) {
			System.out.println("Staff Menu:");
			System.out.println("1. Change Password");
			System.out.println("2. Create a Camp");
			System.out.println("3. Edit a Camp");
			System.out.println("4. Delete a Camp");
			System.out.println("5. Toggle Camp Visibility");
			System.out.println("6. View List of all Camps");
			System.out.println("7. View List of all Camps created by you");
			System.out.println("8. View Enquiries");
			System.out.println("9. Reply to Enquiries");
			System.out.println("10. View Suggestions");
			System.out.println("11. Approve Suggestions");
			System.out.println("12. Generate Reports");
			System.out.println("13. Logging out of Application");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					System.out.println("Enter your new password: ");
					String newPassword = scanner.nextLine();
					staff.setPassword(newPassword);
					System.out.println("New password is set!\n");
					break;
				case 2:
					campManagementService.createNewCamp(staff, campList);
					break;
				case 3:
					campManagementService.editExistingCamp(staff, campList);
					break;
				case 4:
					campManagementService.deleteCamp(staff, campList);
					break;
				case 5:
					campVisibilityService.toggleCampVisibility(staff, campList);
					break;
				case 6:
					campViewService.viewAllCamps(campList);
					break;
				case 7:
					campViewService.viewOwnCamps(staff, campList);
					break;
				case 8:
					staffAndCCMEnquiryManager.submitAnswer(staff.getUserID());
					break;
				case 9:
					staffAndCCMEnquiryManager.viewEnquiries(staff.getUserID());
					break;
				case 10:
					suggestionApproval.viewSuggestions(staff.getUserID());
					break;
				case 11:
					suggestionApproval.viewSuggestions(staff.getUserID());
					break;
				case 12:
					suggestionApproval.submitAnswer(staff.getUserID());
					break;
				case 13:
					System.out.println("Logging out...\n");
					firstStaffLogin = false;
					//isLoggedIn = true;
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void studentMenu(Student student) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Student Menu:");
			System.out.println("1. Change Password");
			System.out.println("2. View Details of Available Camps");
			System.out.println("3. View Registered Camps and Roles");
			System.out.println("4. Register for a Camp");
			System.out.println("5. Withdraw from a Camp");
			System.out.println("6. Submit Enquiry"); // need editing
			System.out.println("7. Delete Enquiry");
			System.out.println("8. Edit Enquiry");
			System.out.println("9. View Enquiries");
			System.out.println("10. Access Camp Committee Features (for camp committee members only)");
			System.out.println("11. Logging out of Application");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					System.out.println("Enter your new password: ");
					String newPassword = scanner.nextLine();
					student.setPassword(newPassword);
					System.out.println("New password is set!\n");
					break;
				case 2:
					checkCampDetails.viewAvailableCampDetails(student);
					break;
				case 3:
					checkCampDetails.viewRegisteredCamps(student);
					break;
				case 4:
					campRegistration.registerForCamp(student);
					break;
				case 5:
					campWithdrawal.withdrawFromCamp(student);
					break;
				case 6:
					studentEnquiryManager.submitEnquiry(student);
					break;
				case 7:
					studentEnquiryManager.deleteEnquiry(student, enquiryList);
					break;
				case 8:
					studentEnquiryManager.editEnquiry(student, enquiryList);
					break;
				case 9:
					studentEnquiryManager.viewEnquiries(student, enquiryList);
					break;
				case 10: // Access Camp Committee Features
					accessCampCommitteeFeatures(student);
					break;
				case 11:
					System.out.println("Logging out...\n");
					firstStudentLogin = false;
					//isLoggedIn = true;
					return;

				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void accessCampCommitteeFeatures(Student student) {

		Scanner scanner = new Scanner(System.in);

		for (Camp campObject : campList.getCampList()) {
			String role = objectFinder.findStudentRoleInCamp(student, campObject);

			if (role.equals("Camp Committee Member")) {
				Camp camp = campObject;

				while (true) {
					System.out.println("Camp Committee Features:");
					System.out.println("1. Submit Suggestion");
					System.out.println("2. Delete Suggestion");
					System.out.println("3. Edit Suggestion");
					System.out.println("4. View Suggestions");
					System.out.println("5. Edit Enquiries");
					System.out.println("6. View Enquiries");
					System.out.println("7. View Camp Details");
					System.out.println("8. Print Report");
					System.out.println("9. Back");

					int choice = scanner.nextInt();
					scanner.nextLine(); // Consume newline

					switch (choice) {
						case 1:
							suggestionManager.submitSuggestion(student.getUserID(), camp);
							break;
						case 2:
							suggestionManager.deleteSuggestion(student.getUserID());
							break;
						case 3:
							suggestionManager.editSuggestion(student.getUserID());
							break;
						case 4:
							suggestionManager.viewSuggestion(student.getUserID());
							break;
						case 5:
							staffAndCCMEnquiryManager.submitAnswer(student.getUserID());
							break;
						case 6:
							staffAndCCMEnquiryManager.viewEnquiries(student.getUserID());
							break;
						case 7:
							//view camp details
							break;
						case 8:
							//print report
							break;
						case 9:
							System.out.println("Returning to main student menu...");
							return;
						default:
							System.out.println("Invalid choice. Please try again.");
					}
				}
				//break;

			} else {
				System.out.println("You are not a camp committee member of any camp. You are not allowed to access camp committee member features.");
				System.out.println("Returning to main student menu...");
				return;
			}
		}


	}

}