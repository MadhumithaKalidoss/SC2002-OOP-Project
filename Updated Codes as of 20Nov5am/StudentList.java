import java.util.ArrayList;

public class StudentList{
	private ArrayList<Student> studentList;
    public StudentList() {
    	studentList = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

	public void setUserIdForEmptyUsers() {
        for (Student student : studentList) {
            if (student.getUserID() == null || student.getUserID().isEmpty()) {
            	student.setUserID();
            }
        }
    }
	
	public void setFirstLogin() {
        for (Student student : studentList) {
            student.setFirstLogin(true);
        }
    }
	
	public void setDefaultPassword() {
		for (Student student : studentList) {
            if (student.getPassword() == null || student.getPassword().isEmpty()) {
            	student.setPassword("password");
            }
        }
	}
	
	public void changePassword(String userId, String newPassword) {
		ObjectFinder objectFinder = new ObjectFinder();
        Student student = objectFinder.findStudentByUserId(userId, studentList);
        if (student != null) {
        	student.setPassword(newPassword);
        }
	}

	
	public ArrayList<Student> getStudentList() {
	    return studentList;
	}

    public Student findStudentById(String userId) {
        for (Student student : studentList) {
            if (student.getUserID().equalsIgnoreCase(userId)) {
                return student;
            }
        }
        return null;
    }
}