package s23.crm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "app_user")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long user_id;
	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	@Column(name = "user_role", nullable = false)
	private String role;

	
	public AppUser() {
		super();
	}

	public AppUser(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public AppUser(Long user_id, String username, String passwordHash, String role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AppUser [user_id=" + user_id + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ "]";
	}

	
	
}
