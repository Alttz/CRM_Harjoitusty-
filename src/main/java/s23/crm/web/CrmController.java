package s23.crm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import s23.crm.domain.Customer;
import s23.crm.domain.CustomerRepository;
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
	private UserRepository userRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	

	private static final Logger log = LoggerFactory.getLogger(CrmController.class);

	@RequestMapping("index")
	@ResponseBody
	public String showMainPage() {
		return "Tämä on pääsivu";
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
	
	@RequestMapping(value = "/addcustomer")
	public String addCustomer(Model model) {
		log.info("adding customer");
		model.addAttribute("customer", new Customer());
		return "addcustomer";
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("meeting") Meeting meeting, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", meeting);
			model.addAttribute("customers", customerRepository.findAll());
			model.addAttribute("appusers", userRepository.findAll());
		}
		meetingRepository.save(meeting);
	 return "redirect:crm";
	}
	
	@RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", customer);
		}
		customerRepository.save(customer);
	 return "redirect:crm";
	}

}
