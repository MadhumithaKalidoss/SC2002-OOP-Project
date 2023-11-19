import java.util.List;

public class CampViewService {

    public void viewAllCamps(CampList campList) {
        int campNum = 1;
        System.out.println("List of all camps:");

        for (Camp camp : campList.getCampList()) {
            System.out.println(campNum + ") " + camp.getCampName());
            ++campNum;
            System.out.println();
        }
    }

    public void viewOwnCamps(Staff staff, CampList campList) {
        int campNum = 1;
        System.out.println("List of camps under " + staff.getName() + ":");

        for (Camp camp : campList.getCampList()) {
            if (staff != null && camp.getStaffinCharge() == staff) {
                System.out.println(campNum + ") " + camp.getCampName());
                ++campNum;
                System.out.println();
                //System.out.println("Description: " + camp.getDescription());
                //System.out.println("Location: " + camp.getLocation());
                //System.out.println("Start Date: " + camp.getStartDate());
                //System.out.println("End Date: " + camp.getEndDate());
                //System.out.println("------------");
            }
        }
    }
}
