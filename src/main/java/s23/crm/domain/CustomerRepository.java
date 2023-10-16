package s23.crm.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>  {

	List<Customer> findByName(String name);

	
}
