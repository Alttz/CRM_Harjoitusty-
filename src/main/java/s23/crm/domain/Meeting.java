package s23.crm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingid;
	
	@NotBlank
    @Column(name = "meeting_title")
	private String meetingTitle;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Meeting() {
	}

	public Meeting(@NotBlank String meetingTitle, Customer customer, Employee employee) {
		super();
		this.meetingTitle = meetingTitle;
		this.customer = customer;
		this.employee = employee;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Meeting [meetingid=" + meetingid + ", meetingTitle=" + meetingTitle + ", customer=" + customer
				+ ", employee=" + employee + "]";
	}



	
	
	
	
	

}
