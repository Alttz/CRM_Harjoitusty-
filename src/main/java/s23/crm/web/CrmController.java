package s23.crm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Optional;

import s23.crm.domain.AppUser;
import s23.crm.domain.Customer;
import s23.crm.domain.CustomerRepository;
import s23.crm.domain.Employee;
import s23.crm.domain.EmployeeRepository;
import s23.crm.domain.Meeting;
import s23.crm.domain.MeetingRepository;
import s23.crm.domain.UserRepository;

@Controller
public class CrmController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private MeetingRepository meetingRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(CrmController.class);
	
	@GetMapping("/login")
    public String login() {
        return "loginpage";
    }

	@RequestMapping(value = { "/", "/crm" })
	public String meetingList(Model model) {
		model.addAttribute("meetings", meetingRepository.findAll());
		return "meetinglist";
	}

	@RequestMapping(value = { "/customerlist" })
	public String customerList(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "customerlist";
	}

	@RequestMapping(value = "/addmeeting")
	public String addMeeting(Model model) {
		log.info("adding meeting");
		model.addAttribute("meeting", new Meeting());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "addmeeting";
	}

	@RequestMapping(value = "/editmeeting/{meetingid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editMeeting(@PathVariable Long meetingid, Model model) {
		log.info("editing meeting");

		Optional<Meeting> optionalMeeting = meetingRepository.findById(meetingid);
		if (optionalMeeting.isPresent()) {
			model.addAttribute("meeting", optionalMeeting.get());
		} else {
			// Handle the case where the meeting with the given ID does not exist.
			// For example, you can redirect to a 404 page or back to the list of meetings
			// with an error message.
			return "redirect:/crm";
		}

		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());

		return "editmeeting";
	}

	@RequestMapping(value = "/addcustomer")
	public String addCustomer(Model model) {
		log.info("adding customer");
		model.addAttribute("customer", new Customer());
		return "addcustomer";
	}

	@RequestMapping(value = "/adduser")
	public String addUser(Model model) {
		log.info("adding user & employee");
		model.addAttribute("appuser", new AppUser());
		model.addAttribute("employee", new Employee());
		return "adduser";
	}

	@PostMapping("/saveuser")
	public String saveUserAndEmployee(
	        @Validated @ModelAttribute("appuser") AppUser appuser, 
	        BindingResult appUserBindingResult,
	        @Validated @ModelAttribute("employee") Employee employee,
	        BindingResult employeeBindingResult,
	        Model model) {
	    System.out.println("Entered saveUserAndEmployee method.");

	    // If there are validation errors for AppUser or Employee, we return the form
	    if (appUserBindingResult.hasErrors() || employeeBindingResult.hasErrors()) {
	        // Assuming you have some reference data to add for the form rendering
	        model.addAttribute("appuser", appuser);
	        model.addAttribute("employee", employee);
	        // Add more attributes if needed (similar to how you added customers and employees for the meeting)
	        
	        return "adduser"; // replace with your actual form template name
	    }
	    
	    // Encode the password before saving
	    appuser.setPasswordHash(passwordEncoder.encode(appuser.getPasswordHash()));

	    // Your logic for saving appuser and employee
	    // Example:
	    userRepository.save(appuser);
	    employeeRepository.save(employee);

	    return "redirect:crm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("meeting") Meeting meeting, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("meeting", meeting);
			model.addAttribute("customers", customerRepository.findAll());
			model.addAttribute("employees", employeeRepository.findAll());
			return "addmeeting";
		}
		meetingRepository.save(meeting);
		return "redirect:crm";
	}

	@RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult,
			Model model) {
		   if (bindingResult.hasErrors()) {
		        model.addAttribute("customer", customer); // You were using "edit" before. It should be "customer" to match the form's th:object.
		        return "addcustomer"; // Replace with the name of your Thymeleaf template that displays the form.
		    }
		customerRepository.save(customer);
		return "redirect:crm";
	}

	@RequestMapping(value = "/delete/{meetingid}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("meetingid") Long meetingid, Model model) {
		meetingRepository.deleteById(meetingid);
		return "redirect:../crm";
	}

}
