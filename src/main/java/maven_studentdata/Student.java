package maven_studentdata;

class Student {

	String first_name;
	String gender;
	String email;
	double gpa;

	public Student(String first_name, String gender, String email, double gpa) {
		this.first_name = first_name;
		this.gender = gender;
		this.email = email;
		this.gpa = gpa;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		 return "Student{" +
	                "first_name='" + first_name + '\'' +
	                ", gender='" + gender + '\'' +
	                ", email='" + email + '\'' +
	                ", gpa='" + gpa + '\'' +
	                '}';
	}
	}