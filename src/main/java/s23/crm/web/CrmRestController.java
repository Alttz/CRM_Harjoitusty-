package s23.crm.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import s23.crm.domain.Meeting;
import s23.crm.domain.MeetingRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CrmRestController {

	@Autowired
	private MeetingRepository meetingRepository;


	@GetMapping(value = "/meetings")
    public List<Meeting> getAllMeetings() {
        return (List<Meeting>) meetingRepository.findAll();
    }

    // Fetch a specific meeting by ID
    @GetMapping("/meeting/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        Optional<Meeting> meetingOpt = meetingRepository.findById(id);
        if (!meetingOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(meetingOpt.get());
    }

    // Create a new meeting
    @PostMapping(value = "/meeting")
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    // Update a meeting
    @PutMapping("/meeting/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting updatedMeeting) {
        if (!meetingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedMeeting.setMeetingid(id); // Ensure the ID in the path is set in the object to avoid mismatches
        return ResponseEntity.ok(meetingRepository.save(updatedMeeting));
    }

    // Delete a meeting
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        if (!meetingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        meetingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}