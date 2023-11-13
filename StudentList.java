package sc2002project;

import java.util.ArrayList;

public class StudentList{
	private ArrayList<Student> studentList;
	
    public StudentList() {
    	studentList = new ArrayList<Student>();
    }
    
	public void setUserIdForEmptyUsers() {
        for (Student student : studentList) {
            if (student.getUserID() == null || student.getUserID().isEmpty()) {
            	student.setUserID();
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

}