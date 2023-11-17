package sc2002project;

import java.util.Scanner;

public class StudentEnquiryManager {
    private EnquiryList enquirylist;
    
    Scanner scanner = new Scanner(System.in);

    public StudentEnquiryManager(EnquiryList enquirylist) {
        this.enquirylist = enquirylist;
    }

    public void submitEnquiry(String studentUserID) {
    	System.out.println("Enter the camp name: ");
        String campName = scanner.nextLine();
        
        //if(Check if camp is open to that student before allowing him/her to submit enquiry, add a method in this class that checks that)
            System.out.println("Enter your enquiry content: ");
            String enquiryContent = scanner.nextLine();
            
            enquirylist.addEnquiry(studentUserID, campName, enquiryContent);
            System.out.println("Your enquiry has been submitted!\n");
        /*} else {
            System.out.println("This camp is not open to you. You are not allowed submit enquiries for this camp.");
        }*/
    }
    
    public void deleteEnquiry(String studentUserID, EnquiryList enquiryList) {
    	//make this check if that particular student has made any enquiries and not just check whether the whole enquirylist is full or empty
    	if (enquiryList.isEmpty()) {
            System.out.println("Your enquiry list is empty. You have no enquiries to delete.\n");
            return; }
    	
    	System.out.println("Enter the Enquiry ID of the enquiry to be deleted: ");
        int enquiryID = scanner.nextInt();
        
        ObjectFinder objectFinder = new ObjectFinder();
        Enquiry deleteEnquiry = objectFinder.findEnquiryById(enquiryID, enquiryList);
        
        if (deleteEnquiry != null) //check if that enquiry object exists
        {
        	if (deleteEnquiry.getStudentUserID().equals(studentUserID)) //check if that student made that enquiry
        	{
        		if (!deleteEnquiry.isStatus()) //check if the enquiry has been answered or not
        		{
        			enquirylist.removeEnquiry(deleteEnquiry);
        			System.out.println("Your enquiry with ID " + enquiryID + " has been deleted!\n");
        			scanner.nextLine();
        			
        		} else {
        			System.out.println("Your enquiry has already been answered. You cannot delete this enquiry.\n");
        			scanner.nextLine();
        		}
        	} else {
        		System.out.println("You can only delete your own enquiries.\n");
        		scanner.nextLine();
        	}
        } else {
        	System.out.println("Invalid Enquiry ID. Please check the ID of your enquiry using 'View Enquiries'.\n");
        	scanner.nextLine();
        }
    }
       
    
    public void editEnquiry(String studentUserID, EnquiryList enquiryList) {
    	//make this check if that particular student has made any enquiries and not just check whether the whole enquirylist is full or empty
    	if (enquiryList.isEmpty()) {
            System.out.println("Your enquiry list is empty. You have no enquiries to edit.\n");
            return; }
    	
    	System.out.println("Enter the Enquiry ID of the enquiry to be edited: ");
        int enquiryID = scanner.nextInt();
        scanner.nextLine();
        
        ObjectFinder objectFinder = new ObjectFinder();
        Enquiry editEnquiry = objectFinder.findEnquiryById(enquiryID, enquiryList);
        
        if (editEnquiry != null) //check if that enquiry object exists
        {
        	if (editEnquiry.getStudentUserID().equals(studentUserID)) //check if that student made that enquiry
        	{
        		if (!editEnquiry.isStatus()) //check if the enquiry has been answered or not
        		{
        			System.out.println("Enter the edited enquiry content: ");
        			String newContent = scanner.nextLine();
        			enquirylist.updateEnquiry(editEnquiry, newContent);
        			System.out.println("Your enquiry with ID " + enquiryID + " has been edited!\n");
        			
        		} else {
        			System.out.println("Your enquiry has already been answered. You cannot edit this enquiry.\n");
        			scanner.nextLine();
        		}
        	} else {
        		System.out.println("You can only edit your own enquiries.\n");
        		scanner.nextLine();
        	}
        } else {
        	System.out.println("Invalid Enquiry ID. Please check the ID of your enquiry using 'View Enquiries'.\n");
        	scanner.nextLine();
        }
        
    }
    

    public void viewEnquiries(String studentUserID, EnquiryList enquiryList) {
    	//make this check if that particular student has made any enquiries and not just check whether the whole enquirylist is full or empty
    	if (enquiryList.isEmpty()) {
            System.out.println("Your enquiry list is empty.\n");
            return; 
        } 
    	else {
    		System.out.println("Your Enquiries:");
    		System.out.println("------------");
    		for (Enquiry enquiry : enquiryList.getEnquiryList()) 
    		{
    			if (enquiry.getStudentUserID().equals(studentUserID)) {
    				System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
    				System.out.println("Enquiry Content: " + enquiry.getEnquiryContent());
    				System.out.println("Status: " + (enquiry.isStatus() ? "Enquiry has been answered" : "Enquiry has not been answered"));
    				System.out.println("------------\n");
            }
        }
    	}
    }
    
}
