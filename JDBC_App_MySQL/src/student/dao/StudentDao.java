package student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import student.dto.Student;
import student.requirements.StudentRequirements;

public class StudentDao implements StudentRequirements {

	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	private static Student std = null;

	@Override
	public int saveStudent(Student std) throws ClassNotFoundException, SQLException, ParseException {
		if (std.getMarks() >= 1 && std.getMarks() <= 100 && String.valueOf(std.getPhone()).length() == 10
				&& String.valueOf(std.getPhone()) != null) {
			con = StudentRequirements.getConnection();
			pst = con.prepareStatement("INSERT INTO STUDENTDB VALUES (?, ?, ?, ?, ?, ?)");

			pst.setInt(1, std.getId());
			pst.setString(2, std.getName());
			pst.setInt(3, std.getMarks());
			pst.setLong(4, std.getPhone());
			pst.setString(5, std.getAddress());
			pst.setDate(6, getFormatedDate(std.getDob()));

			int count = pst.executeUpdate();
			con.close();
			return count;
		} else
			return -1;
	}

	@Override
	public ArrayList<Student> displayAllStudents() throws ClassNotFoundException, SQLException {
		con = StudentRequirements.getConnection();

		pst = con.prepareStatement("SELECT * FROM STUDENTDB");
		rs = pst.executeQuery();

		ArrayList<Student> stdList = new ArrayList<Student>();
		while (rs.next()) {
			std = new Student();
			std.setId(rs.getInt("stdId"));
			std.setName(rs.getString("stdName"));
			std.setMarks(rs.getInt("stdMarks"));
			std.setPhone(rs.getLong("stdPhone"));
			std.setAddress(rs.getString("stdAddress"));
			std.setDob(rs.getDate("stdDob").toString());

			stdList.add(std);
		}
		con.close();
		return stdList;
	}

	@Override
	public Student displayStudentById(int id) throws ClassNotFoundException, SQLException {
		con = StudentRequirements.getConnection();

		pst = con.prepareStatement("SELECT * FROM STUDENTDB WHERE STDID = ?");
		pst.setInt(1, id);

		rs = pst.executeQuery();
		while (rs.next()) {
			std = new Student();
			std.setId(rs.getInt("stdId"));
			std.setName(rs.getString("stdName"));
			std.setMarks(rs.getInt("stdMarks"));
			std.setAddress(rs.getString("stdAddress"));
			std.setPhone(rs.getLong("stdPhone"));
			var temp = rs.getDate("stdDob");
			std.setDob(temp.toString());
		}
		return std;
	}

	@Override
	public ArrayList<Student> displayStudentByName(String name) throws ClassNotFoundException, SQLException {
		con = StudentRequirements.getConnection();

		pst = con.prepareStatement("SELECT * FROM STUDENTDB WHERE STDNAME = ?");
		pst.setString(1, name);
		rs = pst.executeQuery();

		ArrayList<Student> stdList = null;
		while (rs.next()) {
			std = new Student();

			std.setId(rs.getInt("stdId"));
			std.setName(rs.getString("stdName"));
			std.setMarks(rs.getInt("stdMarks"));
			std.setAddress(rs.getString("stdAddress"));
			std.setPhone(rs.getLong("stdPhone"));
			var temp = rs.getDate("stdDob");
			std.setDob(temp.toString());

			stdList = new ArrayList<Student>();
			stdList.add(std);
		}
		con.close();
		return stdList;
	}

	@Override
	public Student displayStudentByPhone(long phone) throws ClassNotFoundException, SQLException {
		con = StudentRequirements.getConnection();

		pst = con.prepareStatement("SELECT * FROM STUDENTDB WHERE STDPHONE = ? ");
		pst.setLong(1, phone);
		rs = pst.executeQuery();

		while (rs.next()) {
			std = new Student();
			std.setId(rs.getInt("stdId"));
			std.setName(rs.getString("stdName"));
			std.setMarks(rs.getInt("stdMarks"));
			std.setAddress(rs.getString("stdAddress"));
			std.setPhone(rs.getLong("stdPhone"));
			var temp = rs.getDate("stdDob");
			std.setDob(temp.toString());
		}
		con.close();
		return std;
	}

	@Override
	public ArrayList<Student> displayPassStudent(int marks) throws ClassNotFoundException, SQLException {
		con = StudentRequirements.getConnection();

		pst = con.prepareStatement("SELECT * FROM jdbc_app_mysql.studentdb WHERE STDMARKS >= ?");
		pst.setInt(1, marks);
		rs = pst.executeQuery();
		ArrayList<Student> stdList = null;

		stdList = new ArrayList<Student>();
		while (rs.next()) {
			std = new Student();

			std.setId(rs.getInt("stdId"));
			std.setName(rs.getString("stdName"));
			std.setMarks(rs.getInt("stdMarks"));
			std.setAddress(rs.getString("stdAddress"));
			std.setPhone(rs.getLong("stdPhone"));
			var temp = rs.getDate("stdDob");
			std.setDob(temp.toString());

			stdList.add(std);
		}
		con.close();
		return stdList;
	}

	@Override
	public String updateStudentName(int id, String name) throws ClassNotFoundException, SQLException {
		std = displayStudentById(id);
		if (std == null)
			return "Student with ID " + id + " not present !!!";
		else {
			con = StudentRequirements.getConnection();
			pst = con.prepareStatement("UPDATE STUDENTDB SET STDNAME = ? WHERE STDID = ? ");
			pst.setString(1, name);
			pst.setInt(2, std.getId());

			int c = pst.executeUpdate();
			con.close();
			if (c != 0)
				return "Name of *" + std.getName() + "* Updated Successfully !!!";
			else
				return "OOps, somthing wants wrong !!!";
		}
	}

	@Override
	public String updateStudentPhone(int id, long phone) throws ClassNotFoundException, SQLException {
		std = displayStudentById(id);
		if (std == null)
			return "Student with ID " + id + " not present !!!";
		else {
			con = StudentRequirements.getConnection();
			pst = con.prepareStatement("UPDATE STUDENTDB SET STDPHONE =? WHERE STDID = ?");
			pst.setLong(1, phone);
			pst.setInt(2, std.getId());

			int c = pst.executeUpdate();
			con.close();
			if (c != 0)
				return "Phone Number of *" + std.getName() + "* Updated Successfully !!!";
			else
				return "OOps, somthing wants wrong !!!";
		}
	}

	@Override
	public String updateStudentMarks(int id, int marks) throws ClassNotFoundException, SQLException {
		if (marks >= 1 && marks <= 100) {
			std = displayStudentById(id);
			if (std == null)
				return "Student with ID " + id + " not present !!!";
			else {
				con = StudentRequirements.getConnection();
				pst = con.prepareStatement("UPDATE STUDENT SET STDMARKS = ? WHERE STDID =? ");
				pst.setInt(1, marks);
				pst.setInt(2, std.getId());

				int c = pst.executeUpdate();
				con.close();
				if (c != 0)
					return "Marks of *" + std.getName() + "* Updated Successfully !!!";
				else
					return "OOps, somthing wants wrong !!!";
			}
		} else
			return "Marks should be between 1 to 100";
	}

	@Override
	public String updateStudentAddress(int id, String address) throws ClassNotFoundException, SQLException {
		std = displayStudentById(id);
		if (std == null)
			return "Student with ID " + id + " not present !!!";
		else {
			con = StudentRequirements.getConnection();
			pst = con.prepareStatement("UPDATE STUDENTDB SET STDADDRESS =? WHERE STDID =?");
			pst.setString(1, address);
			pst.setInt(2, std.getId());

			int c = pst.executeUpdate();
			con.close();
			if (c != 0)
				return "Address of *" + std.getName() + "* Updated Successfully !!!";
			else {
				return "OOps, somthing wants wrong !!!";
			}
		}
	}

	@Override
	public String updateStudentDate(int id, String dob) throws ClassNotFoundException, SQLException, ParseException {
		std = displayStudentById(id);
		if (std == null)
			return "Student with ID " + id + " not present !!!";
		else {
			if (dob == null || dob.length() == 0)
				return "Enter proper date format";
			else {
				con = StudentRequirements.getConnection();
				pst = con.prepareStatement("UPDATE STUDENTDB SET STDDOB=? WHERE STDID=?");
				pst.setDate(1, getFormatedDate(dob));
				pst.setInt(2, id);
				int c = pst.executeUpdate();
				if (c != 0)
					return "DOB of Studnet *" + std.getName() + "* Updated Successfully !!!";
				else
					return "OOps, somthing wants wrong !!!";
			}
		}
	}

	@Override
	public String updateStudent(int id, Student udatedStd) throws ClassNotFoundException, SQLException, ParseException {
		std = displayStudentById(id);
		if (std == null)
			return "Student with ID " + id + " not present !!!";
		else {
			if (udatedStd.getMarks() >= 1 && udatedStd.getMarks() <= 100) {
				con = StudentRequirements.getConnection();
				pst = con.prepareStatement(
						"UPDATE STUDENTDB SET STDNAME=?, STDPHONE=?, STDMARKS=?, STDADDRESS=?, STDDOB=? WHERE STDID =?");
				pst.setString(1, udatedStd.getName());
				pst.setLong(2, udatedStd.getPhone());
				pst.setInt(3, udatedStd.getMarks());
				pst.setString(4, udatedStd.getAddress());
				pst.setInt(5, std.getId());
				pst.setDate(6, getFormatedDate(std.getDob()));

				int c = pst.executeUpdate();
				con.close();
				if (c != 0)
					return "All details of " + std.getName() + " get updated Successfully.";
				else
					return "OOps, somthing wants wrong !!!";
			} else
				return "Marks should be between 1 to 100";
		}
	}

	@Override
	public String deleteStudent(int id) throws ClassNotFoundException, SQLException {
		std = displayStudentById(id);
		if (std == null)
			return "Student with ID " + id + " not present !!!";
		else {
			con = StudentRequirements.getConnection();
			pst = con.prepareStatement("DELETE FROM STUDENTDB WHERE STDID = ?");
			pst.setInt(1, id);

			int c = pst.executeUpdate();
			con.close();
			if (c != 0)
				return std.getName() + " Deleted Successfully.";
			else
				return "OOps, somthing wants wrong !!!";
		}
	}
	
	@Override
	public ArrayList<Student> todaysBirthDay() throws ClassNotFoundException, SQLException, ParseException {
		LocalDate todaysDate = java.time.LocalDate.now();
		int day = todaysDate.getDayOfMonth();
		int month = todaysDate.getMonthValue();
		
		con = StudentRequirements.getConnection();
		pst = con.prepareStatement("SELECT STDNAME, STDPHONE, STDDOB FROM STUDENTDB WHERE DAY(STDDOB)=? AND MONTH(STDDOB)=?");
		pst.setInt(1, day);
		pst.setInt(2, month);
		
		rs = pst.executeQuery();
		ArrayList<Student> stdList = new ArrayList<Student>();
		while(rs.next()) {
			std = new Student();
			
			std.setName(rs.getString("STDNAME"));
			std.setPhone(rs.getLong("STDPHONE"));
			std.setDob(rs.getDate("STDDOB").toString());
			
			stdList.add(std);
		}
		con.close();
		return stdList;
	}

	private final static java.sql.Date getFormatedDate(String dob) throws ParseException {
		// 1. You need to handle different date formats, 2.You want to add more
		// validations or manipulations to the date parsing process, 3. Your input date
		// format might vary or be inconsistent.
//		==================================================================================
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date uDate = sdf.parse(dob);
//		long l = uDate.getTime();
//		return new java.sql.Date(l);

		// You are confident that the date string will always be in the yyyy-MM-dd
		// format.
		return java.sql.Date.valueOf(dob);
	}
}
