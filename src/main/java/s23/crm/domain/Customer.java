package s23.crm.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customer_id;
	
	@Size(min = 2, max = 255, message = "Nimessä tulee olla 2-255 merkkiä")
    @Column(name = "customer_name") 
	private String name;
    @Pattern(regexp = "^\\d{7}-\\d$", message = "Y-tunnuksen tulee olla muodossa 1234567-8")
    @Column(name = "org_num") 
	private String orgNum;
	
	public Customer() {
	}

	public Customer(@NotBlank String name, String orgNum) {
		super();
		this.name = name;
		this.orgNum = orgNum;
	}

	public Long getId() {
		return customer_id;
	}

	public void setId(Long id) {
		this.customer_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	@Override
	public String toString() {
		return "Customer [id=" + customer_id + ", name=" + name + ", orgNum=" + orgNum + "]";
	}
	
	
	
	

}
