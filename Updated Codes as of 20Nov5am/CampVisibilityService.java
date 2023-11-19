import java.util.List;
import java.util.Scanner;

public class CampVisibilityService {
    private CampList campList;

    public CampVisibilityService(CampList campList) {
        this.campList = campList;
    }

    public void toggleCampVisibility(Staff staff, CampList campList) {
        Scanner scanner = new Scanner(System.in);

        if (campList.getCampList().isEmpty()) {
            System.out.println("There are no existing camps to edit visibility.\n");
            return;
        }

        System.out.println("Enter the name of the camp to edit visibility of: ");
        String campName = scanner.nextLine();

        ObjectFinder objectFinder = new ObjectFinder();
        Camp camp = objectFinder.findCampByName(campName, campList.getCampList());

        if (camp != null)
        {
            if (camp.getStaffinCharge().getUserID().equals(staff.getUserID()))
            {
                System.out.println("Do you want to turn the visbility of this camp to students on or off?");
                String visibility = scanner.nextLine();

                if (visibility.equalsIgnoreCase("on")) {
                    camp.setVisibilityToggle(true);
                    System.out.println("Visbility of " + camp.getCampName() + " turned on.");
                } else if (visibility.equalsIgnoreCase("off")) {
                    camp.setVisibilityToggle(false);
                    System.out.println("Visbility of " + camp.getCampName() + " turned off.");
                } else
                    System.out.println("Invalid option.");

            } else {
                System.out.println("You are not in-charge on this camp. You cannot edit the visibility of this camp.\n");
                scanner.nextLine();
            }
        } else  {
            System.out.println("Camp does not exist. Please check the name of the camp using 'View Camps Created'.\n");
            scanner.nextLine();
        }
    }

    }