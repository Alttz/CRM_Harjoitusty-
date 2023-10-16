package s23.crm.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository  extends CrudRepository<Meeting, Long>  {
	
	List<Meeting> findByMeetingTitle(String meetingTitle);


}
