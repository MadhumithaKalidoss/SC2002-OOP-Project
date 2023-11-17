package sc2002project;

import java.util.List;

public class CampVisibilityService {
    private List<Camp> camps;

    public CampVisibilityService(List<Camp> camps) {
        this.camps = camps;
    }

    public void toggleCampVisibility(Camp camp, boolean isVisible) {
        camp.setVisibilityToggle(isVisible);
    }

    public void displayCampList() {
        System.out.println("Camp List:");
        for (Camp camp : camps) {
            displayCampDetails(camp);
        }
    }

    private void displayCampDetails(Camp camp) {
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Visibility: " + (camp.getVisibilityToggle() ? "On" : "Off"));
        // Display other relevant details
        System.out.println("--------------------------");
    }
}