package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private Date birthDate;
	private Double baseSalary;

	private Department department;

	public Seller() {
		// TODO Auto-generated constructor stub
	}

	public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Department department) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {

//		StringBuilder stringBuilder = new StringBuilder();
//
//		stringBuilder.append("Seller ___\n");
//		stringBuilder.append("          |\n");
//		stringBuilder.append("          |_ id: [" + id + "]\n");
//		stringBuilder.append("          |_ name: [" + name + "]\n");
//		stringBuilder.append("          |_ email: [" + email + "]\n");
//		stringBuilder.append("          |_ birtDate: [" + birthDate + "]\n");
//		stringBuilder.append("          |_ baseSalary: [" + baseSalary + "]\n");
//		stringBuilder.append("          |_ Department ___\n");
//		stringBuilder.append("                           |_ " + department + "\n");
//
//		return stringBuilder.toString();
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate + ", baseSalary="
				+ baseSalary + ", department=" + department + "]";
	}

}
