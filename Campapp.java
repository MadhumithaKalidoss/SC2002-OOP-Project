package oopproject;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.ArrayList;

public class Campapp {
	public static void main(String[] args) throws ParseException{
		CampList campList = new CampList();
		Camp camp = new Camp();
		try (Scanner sc = new Scanner(System.in)){
			System.out.println("Camp testing!");
			System.out.println("To add a camp press 1");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter camp name");
				String campName = sc.next();
				System.out.println("Enter start date");
				String dateStr = sc.next();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
				Date startDate = sdf.parse(dateStr);
				System.out.println("Enter end date");
				String dateStr2 = sc.next();
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				Date endDate = sdf2.parse(dateStr2);
				System.out.println("Enter registration closing date date");
				String dateStr3 = sc.next();
				SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
				Date registrationClosingDate = sdf3.parse(dateStr3);
				System.out.println("Enter the user group");
				String userGroup = sc.next();
				System.out.println("Enter the location");
				String location = sc.next();
				System.out.println("Enter the total number of slots");
				int totalSlots = sc.nextInt();
				System.out.println("Enter the number of Camp committee slots");
				int campCommitteeSlots = sc.nextInt();
				System.out.println("Enter a description");
				String description = sc.next();
				System.out.println("Enter the staff in charge");
				String staffinCharge = sc.next();
				//System.out.println("Enter the camp committee names");
				
				//System.out.println("True or false for toggleVisibility");
				//boolean toggleVisibility = sc.nextBoolean();
				campList.addCamp(campName, startDate, endDate, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description, staffinCharge, null, null, false);
				break;
				
			case 2:
				camp.getCampName();
				break;
				
			default:
				return;
			}
			
			
	
}
	}
}

