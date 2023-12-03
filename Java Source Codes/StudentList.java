import java.util.ArrayList;

/**
 * Represents a list of students with user management operations.
 * Implements the UserList interface to manage student-specific user operations.
 */
public class StudentList implements UserList {
    /**
     * A list of students
     */
    private ArrayList<Student> studentList;

    /**
     * Constructs a StudentList object with an empty list of students.
     */
    public StudentList() {
        this.studentList = new ArrayList<>();
    }

    /**
     * Adds a student to the list of students.
     * @param student The student object to be added to the list.
     */
    public void addStudent(Student student) {
        studentList.add(student);
    }

    /**
     * Sets user IDs for students in the list if the ID is empty or null.
     * Iterates through the student list and assigns user IDs to students where the ID is missing or empty.
     */
    public void setUserIdForEmptyUsers() {
        for (Student student : studentList) {
            if (student.getUserID() == null || student.getUserID().isEmpty()) {
                student.setUserID();
            }
        }
    }

    /**
     * Sets the first login flag for all students in the list.
     * Marks all students in the list as having completed their first login.
     */
    public void setFirstLogin() {
        for (Student student : studentList) {
            student.setFirstLogin(true);
        }
    }

    /**
     *Sets a default password for students in the list if their password is empty or null.
     *Assigns a default password to students in the list where the password is missing or empty.
     */
    public void setDefaultPassword() {
        for (Student student : studentList) {
            if (student.getPassword() == null || student.getPassword().isEmpty()) {
                student.setPassword("password");
            }
        }
    }

    /**
     * Retrieves the list of students.
     * @return The ArrayList containing the list of students.
     */
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     * Finds a student in the list by their user ID.
     * @param userId The user ID of the student to find.
     * @return The found Student object; null if not found.
     */
    public Student findStudentById(String userId) {
        for (Student student : studentList) {
            if (student.getUserID().equalsIgnoreCase(userId)) {
                return student;
            }
        }
        return null;
    }
}
