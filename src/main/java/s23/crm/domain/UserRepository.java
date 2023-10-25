package s23.crm.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
}