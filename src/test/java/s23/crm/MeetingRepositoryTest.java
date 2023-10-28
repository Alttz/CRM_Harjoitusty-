package s23.crm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import s23.crm.domain.MeetingRepository;
import s23.crm.domain.CustomerRepository;
import s23.crm.domain.EmployeeRepository;
import s23.crm.domain.Meeting;
import s23.crm.domain.Employee;


@SpringBootTest
public class MeetingRepositoryTest {
	@Autowired
	private MeetingRepository meetingRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	 public void findByMeetingTitle() {
	 List<Meeting> meetings = meetingRepository.findByMeetingTitle("Neuvottelu");
	 assertThat(meetings).hasSize(1);
	 assertThat(meetings.get(0).getMeetingTitle()).isEqualTo("Neuvottelu");
	 }
	
	@Test
	public void createNewMeeting() {
		Meeting meeting = new Meeting("Testipalaveri", customerRepository.findByName("Nordea").get(0));

	    // Fetch multiple employees
	    Employee jim = employeeRepository.findByFirstName("Jim").get(0);
	    Employee michael = employeeRepository.findByFirstName("Michael").get(0);
	    
	    // Add employees to the meeting
	    meeting.getEmployees().add(jim);
	    meeting.getEmployees().add(michael);
		
		meetingRepository.save(meeting);
		assertThat(meeting.getMeetingid()).isNotNull();
	} 
	

}
