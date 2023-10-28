package s23.crm;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import s23.crm.domain.MeetingRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MeetingRepository meetingRepository;

	@Test
	 public void meetingListTest() throws Exception {
	     when(meetingRepository.findAll()).thenReturn(Collections.emptyList());  // mock the repository response

	     mockMvc.perform(MockMvcRequestBuilders.get("/crm")
	            .with(user("user").password("user").roles("USER")))  // mock authentication
	           .andExpect(status().isOk())
	           .andExpect(view().name("meetinglist"))
	           .andExpect(model().attributeExists("meetings"));
	 }

}
