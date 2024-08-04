package student.services;

import java.util.ArrayList;
import java.util.Scanner;

import student.dao.StudentDao;
import student.dto.Student;

public class StudentServices {
	static Scanner sc = new Scanner(System.in);
	private static StudentDao dao = new StudentDao();

	public static void main(String[] args) {
		Student std = null;
		ArrayList<Student> stdList = null;
		try {
			while (true) {
				System.out.println("\n");
				System.out.println("\t****************************************");
				System.out.println("\t|  Option  |      Description          |");
				System.out.println("\t****************************************");
				System.out.println("\t|    1.    |  Save Student             |");
				System.out.println("\t****************************************");
				System.out.println("\t|    2.    |  Display All Students     |");
				System.out.println("\t|    3.    |  Display Student by Id    |");
				System.out.println("\t|    4.    |  Display Student by Name  |");
				System.out.println("\t|    5.    |  Display Student by Phone |");
				System.out.println("\t|    6.    |  Display Passed Students  |");
				System.out.println("\t****************************************");
				System.out.println("\t|    7.    |  Update Student's Name    |");
				System.out.println("\t|    8.    |  Update Student's Phone   |");
				System.out.println("\t|    9.    |  Update Student's Marks   |");
				System.out.println("\t|   10.    |  Update Student's Address |");
				System.out.println("\t|   11.    |  Update Complete Student  |");
				System.out.println("\t****************************************");
				System.out.println("\t|   12.    |  Delete Student by ID     |");
				System.out.println("\t****************************************");
				System.out.println("\t|   13.    |  Exit                     |");
				System.out.println("\t****************************************");
				System.out.println("\n\n\nEnter your Option");
				int option = sc.nextInt();

				int id, marks;
				long phone;

				switch (option) {
				case 1: // Save
					std = new Student();

					System.out.println("Enter following Details of Student: ");

					System.out.println("ID: ");
					std.setId(sc.nextInt());

					System.out.println("Name: ");
					std.setName(sc.next());

					System.out.println("Marks(less than 100): ");
					std.setMarks(sc.nextInt());

					System.out.println("Phone Number: ");
					std.setPhone(sc.nextLong());

					System.out.println("Address: ");
					std.setAddress(sc.next());

					int count = dao.saveStudent(std);
					if (count != 0)
						System.out.println("Data Inserted Successfully !!!");
					else if(count == -1)
						System.out.println("Enter Marks between 1 to 100 only !!!");
					else
						System.out.println("Oops, Somthing wants wrong !!!");
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 2: // Display All Students
					stdList = dao.displayAllStudents();
					if (stdList==null)
						System.out.println("No data found !!!");
					else
						stdList.stream().forEach(System.out::println);
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 3: // Display Student by Id
					System.out.println("Enter a ID: ");
					std = dao.displayStudentById(Math.abs(sc.nextInt()));
					if (std!=null)
						System.out.println(std);
					else
						System.out.println("No data found with this ID");
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 4: // Display Student by Name
					System.out.println("Enter a Name: ");
					stdList = dao.displayStudentByName(sc.next());
					if (stdList==null)
						System.out.println("No data found with this Name!!!");
					else
						stdList.stream().forEach(System.out::println);
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 5: // Display Student by Phone
					System.out.println("Enter a Phone Number: ");
					phone = sc.nextLong();
					
					if(String.valueOf(phone).length()==10) {
						std = dao.displayStudentByPhone(phone);
						if (std==null)
							System.out.println("No data found with this Phone Number "+phone);
						else
							System.out.println(std);
					} else
						System.out.println("Enter a valid phone number");
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 6: // Display Passed Students
					System.out.println("Enter a Passing Marks: ");
					marks = sc.nextInt();
					if (marks < 100) {
						stdList = dao.displayPassStudent(marks);
						if (stdList==null)
							System.out.println("No Studnet Passed is above " + marks + " marks !!!");
						else {
							System.out.println("Passed Students with " + marks + " marks");
							stdList.stream().forEach(System.out::println);
							int passedStdCount = stdList.size();	// Total Number of Passed std.
							int totalStdCount = dao.displayAllStudents().size(); // Total Number of std.
							System.out.println("\nTotal Students: " + totalStdCount + "\nPassed Students: "
									+ passedStdCount + "\t Failed Students: " + (totalStdCount - passedStdCount));
						}
					} else
						System.out.println("Marks should be in range of 1 & 100 !!!");
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 7: // Update Student's Name
					System.out.println("Enter ID of Studnet & updated Name: ");
					System.out.println(dao.updateStudentName(sc.nextInt(), sc.next()));
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 8: // Update Student's Phone
					System.out.println("Enter ID of Student & updated Phone Number: ");
					System.out.println(dao.updateStudentPhone(sc.nextInt(), sc.nextLong()));
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 9: // Update Student's Marks
					System.out.println("Enter ID of Student & updated Marks: ");
					System.out.println(dao.updateStudentMarks(sc.nextInt(), sc.nextInt()));
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 10: // Update Student's Address
					System.out.println("Enter ID of Student & updated Address: ");
					System.out.println(dao.updateStudentAddress(sc.nextInt(), sc.next()));
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 11: // Update Complete Student
					std = new Student();
					
					System.out.println("ID of student you want to updated: ");
					id = sc.nextInt();
					
					System.out.println("Enter Updated Details of Student: ");
					System.out.println("Updated Name: ");
					std.setName(sc.next());

					System.out.println("Updated Marks(less than 100): ");
					std.setMarks(sc.nextInt());

					System.out.println("updated Phone Number: ");
					std.setPhone(sc.nextLong());

					System.out.println("Updated Address: ");
					std.setAddress(sc.next());
					
					System.out.println(dao.updateStudent(id, std));
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 12: // Delete Student by ID
					System.out.println("Enter ID of Studnet to Delete: ");
					System.out.println(dao.deleteStudent(sc.nextInt()));
					break;
				// ------------------------------------------------------------------------------------------------------------

				case 13: // Exit
					System.out.println("Closing............!!!");
					System.out.println("Do you want to exit ? [y/n]");
					if(sc.next().equalsIgnoreCase("y")) {
						System.out.println("Exited Successfully. ");
						System.exit(1);
					}	
					break;
					
				// ------------------------------------------------------------------------------------------------------------
				default:
					throw new IllegalArgumentException("Unexpected value: " + option + " Enter option between 1 to 13");
				}// switch
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
