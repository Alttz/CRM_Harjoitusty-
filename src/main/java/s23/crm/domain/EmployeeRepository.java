package s23.crm.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	List<Employee> findByFirstName(String firstName);


}
