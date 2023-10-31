package s23.crm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
	@Autowired
	private EntityManager entityManager;

	
	@Order(1)
	@Test
	public void createNewMeeting() {
		Meeting meeting = new Meeting("Testipalaveri", customerRepository.findByName("Nordea").get(0));

	    Employee jim = employeeRepository.findByFirstName("Jim").get(0);
	    Employee michael = employeeRepository.findByFirstName("Michael").get(0);
	    
	    meeting.getEmployees().add(jim);
	    meeting.getEmployees().add(michael);
		
		meetingRepository.save(meeting);
		assertThat(meeting.getMeetingid()).isNotNull();
	} 
	
	@Order(3)
	@Test
	@Transactional
	 public void findByMeetingTitle() {
	 List<Meeting> meetings = meetingRepository.findByMeetingTitle("Updated meeting");
	 assertThat(meetings).isNotEmpty();
	 assertThat(meetings.get(0).getMeetingTitle()).isEqualTo("Updated meeting");
	 }
	
	@Order(2)
	@Test
	@Transactional
	@Rollback(false)
	public void updateMeeting() {
	    List<Meeting> meetings = meetingRepository.findByMeetingTitle("Testipalaveri");
	    assertThat(meetings).isNotEmpty();
	    Meeting meetingToUpdate = meetings.get(0);
	    System.out.println("Before Update: " + meetingToUpdate.getMeetingTitle());

	    meetingToUpdate.setMeetingTitle("Updated meeting");
	    System.out.println("After Update: " + meetingToUpdate.getMeetingTitle());

	    meetingRepository.save(meetingToUpdate);
	    entityManager.flush();
	    entityManager.clear();

	    meetingToUpdate = meetingRepository.findByMeetingTitle("Updated meeting").get(0);

	    List<Meeting> updatedMeetings = meetingRepository.findByMeetingTitle("Updated meeting");
	    assertThat(updatedMeetings).isNotEmpty();
	    assertThat(updatedMeetings.get(0).getMeetingTitle()).isEqualTo("Updated meeting");
	}

	@Order(4)
	@Test
	@Transactional
	@Rollback(false)
	public void deleteMeeting() {
	    List<Meeting> meetings = meetingRepository.findByMeetingTitle("Updated meeting");
	    assertThat(meetings).isNotEmpty();
	    Meeting meetingToDelete = meetings.get(0);

	    meetingRepository.delete(meetingToDelete);

	    entityManager.flush();
	    entityManager.clear();

	    List<Meeting> meetingsAfterDelete = meetingRepository.findByMeetingTitle("Updated meeting");
	    assertThat(meetingsAfterDelete).isEmpty();
	}


}
