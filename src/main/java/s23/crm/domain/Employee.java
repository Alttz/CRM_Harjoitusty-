package s23.crm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "employee_position")
    private String position;

    public Employee() {
    }

    public Employee(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    

	public Long getId() {
		return employee_id;
	}

	public void setId(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employee_id + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position + "]";
	}

	
}
