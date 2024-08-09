package student.dto;

public class Student {
	int id;
	String name;
	int marks;
	long phone;
	String address;
	// MySQL Date format: 'yyyy-mm-dd'
	String dob;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Student:    [stdId=" + id + ", stdName=" + name + ", stdMarks=" + marks + ", stdPhone=" + phone
				+ ", stdAddress=" + address + ", stdDob=" + dob + "]";
	}

}
