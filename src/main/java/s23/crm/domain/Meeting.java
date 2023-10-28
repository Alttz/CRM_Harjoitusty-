package s23.crm.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingid;
	
	@NotBlank(message = "Palaverilla tulee olla nimi")
    @Column(name = "meeting_title")
	private String meetingTitle;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
//	@ManyToOne
//	@JoinColumn(name = "employee_id")
//	private Employee employee; 
	
	@ManyToMany
	@NotEmpty(message = "Vähintään yksi asiakasvastaava tulee olla valittuna")
	@JoinTable(
	    name = "meeting_employee",
	    joinColumns = @JoinColumn(name = "meeting_id"),
	    inverseJoinColumns = @JoinColumn(name = "employee_id")
	)
	
	@OrderBy("firstName ASC, lastName ASC")
	private Set<Employee> employees = new HashSet<>();

	public Meeting() {
	}
	
	public Meeting(String string) {
	}

	public Meeting(@NotBlank(message = "Palaverilla tulee olla nimi") String meetingTitle, Customer customer) {
		super();
		this.meetingTitle = meetingTitle;
		this.customer = customer;
	}

	public Meeting(@NotBlank(message = "Palaverilla tulee olla nimi") String meetingTitle, Customer customer,
			@NotEmpty(message = "Vähintään yksi asiakasvastaava tulee olla valittuna") Set<Employee> employees) {
		super();
		this.meetingTitle = meetingTitle;
		this.customer = customer;
		this.employees = employees;
	}



	public Long getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(Long meetingid) {
		this.meetingid = meetingid;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Meeting [meetingid=" + meetingid + ", meetingTitle=" + meetingTitle + ", customer=" + customer
				+ ", employees=" + employees + "]";
	}
	
	
}
