package s23.crm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class MeetingRestTests {
	
	@Autowired
	private WebApplicationContext webAppContext;

	private MockMvc mockMvc;

	@BeforeEach // JUnit5
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void statusOk() throws Exception {
		mockMvc.perform(get("/meetings")).andExpect(status().isOk());
	}

	@Test
	public void responseTypeApplicationJson() throws Exception {
		mockMvc.perform(get("/meetings"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getAllMeetings() throws Exception {
		mockMvc.perform(get("/meetings"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// .andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getMeeting() throws Exception {
		mockMvc.perform(get("/meeting/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// .andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
	}
}
