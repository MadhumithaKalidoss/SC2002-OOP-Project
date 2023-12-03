
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Student, extending the functionality of a User in the system.
 */
public class Student extends User {
    /**
     * List of camps that student has withdrawn from
     */
    private ArrayList<String> withdrawnCampNames;
    /**
     * Points awarded for camp committee members
     */
    private int points;
    /**
     * Counts the number of enquiries that have replies
     */
    private int countEnquiriesReplied;
    /**
     * Count the number of suggestions given by camp committee member
     */
    private int countSuggestionsGiven;
    /**
     * Count the number of approved suggestions
     */
    private int countApprovedSuggestions;

    /**
     * Constructor for creating a Student object.
     * @param name The name of the student.
     * @param email The email address of the student.
     * @param faculty The faculty the student belongs to.
     */

    public Student(String name, String email, String faculty) {
        super(name, email, faculty);
        this.withdrawnCampNames = new ArrayList<>();
        this.points = 0;
        this.countEnquiriesReplied = 0;
        this.countSuggestionsGiven = 0;
        this.countApprovedSuggestions = 0;
    }

    /**
     * Adds the name of a withdrawn camp.
     * @param campName
     */
    public void addWithdrawnCampName(String campName) {
        withdrawnCampNames.add(campName);
    }

    /**
     * Checks if the student has withdrawn from a particular camp.
     * @param campName The name of the camp to check withdrawal status
     * @return True if the student has withdrawn from the camp, false otherwise.
     */
    public boolean hasWithdrawnFromCamp(String campName) {
        return withdrawnCampNames.contains(campName);
    }

    /**
     * Increases the points of the student.
     */
    public void addPoints(){ this.points++; }

    /**
     * Decreases the points of the student.
     */
    public void subPoints(){ this.points--; }

    /**
     * Retrieves the current points of the student.
     * @return The points of the student.
     */
    public int getPoints(){return points;}

    /**
     * Retrieves the number of enquiries replied by the student and increments the count.
     * @return The number of enquiries replied by the student.
     */
    public int getCountEnquiriesReplied() {return countEnquiriesReplied++;}

    /**
     * Retrieves the number of suggestions given by the student and increments the count.
     * @return The number of suggestions given by the student.
     */
    public int getCountSuggestionsGiven() {return countSuggestionsGiven++;}

    /**
     *Decrements the number of suggestions given by the student and retrieves the updated count.
     * @return The number of suggestions given by the student after decrementing.
     */
    public int subCountSuggestionDeleted() {return countSuggestionsGiven--;}

    /**
     * Retrieves the number of approved suggestions by the student and increments the count.
     * @return The number of approved suggestions by the student.
     */
    public int getCountApprovedSuggestions() {return countApprovedSuggestions++;}

}
