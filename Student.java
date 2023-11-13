package sc2002project;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<String> withdrawnCampNames;

    public Student(String name, String email, String faculty) {
    	super(name, email, faculty);
        this.withdrawnCampNames = new ArrayList<>();
    }

    public void addWithdrawnCampName(String campName) {
        withdrawnCampNames.add(campName);
    }

    public boolean hasWithdrawnFromCamp(String campName) {
        return withdrawnCampNames.contains(campName);
    }

}
