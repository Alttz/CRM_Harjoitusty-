package s23.crm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    @NotBlank(message = "Etunimi ei saa olla tyhjä.")
    @Size(min = 2, max = 50, message = "Etunimen pituuden tulee olla 2-50 merkkiä.")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Sukunimi ei saa olla tyhjä.")
    @Size(min = 2, max = 50, message = "Sukunimen pituuden tulee olla 2-50 merkkiä.")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Titteli ei saa olla tyhjä.")
    @Size(min = 3, max = 100, message = "Tittelin pituuden tulee olla 3-100 merkkiä.")
    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜ\\s]*$", message = "Titteli voi sisältää vain kirjaimia ja välilyöntejä.")
    @Column(name = "employee_position")
    private String position;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String position) {
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
