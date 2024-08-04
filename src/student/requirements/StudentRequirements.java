package student.requirements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import student.dto.Student;

public interface StudentRequirements {

/*
	 1. Loading the Driver:-									1 . Register the driver
	 Class.forName("com.mysql.cj.jdbc.Driver");		**OR**		Driver driver = new Driver();
				 												DriverManager.registerDriver(driver);
	 
	 2. Establish connection:-
	 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?user=root&password=root");
*/

	// Steps:- 1 & 2 is Combined.
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_app_mysql", "root", "root");
	}

	public int saveStudent(Student std) throws ClassNotFoundException, SQLException;

	public ArrayList<Student> displayAllStudents() throws ClassNotFoundException, SQLException;

	public Student displayStudentById(int id) throws ClassNotFoundException, SQLException;

	public ArrayList<Student> displayStudentByName(String name) throws ClassNotFoundException, SQLException;

	public Student displayStudentByPhone(long phone) throws ClassNotFoundException, SQLException;

	public ArrayList<Student> displayPassStudent(int marks) throws ClassNotFoundException, SQLException;

	public String updateStudentName(int id, String name) throws ClassNotFoundException, SQLException;

	public String updateStudentPhone(int id, long phone) throws ClassNotFoundException, SQLException;

	public String updateStudentMarks(int id, int marks) throws ClassNotFoundException, SQLException;

	public String updateStudentAddress(int id, String address) throws ClassNotFoundException, SQLException;

	public String updateStudent(int id, Student std) throws ClassNotFoundException, SQLException;

	public String deleteStudent(int id) throws ClassNotFoundException, SQLException;
}
