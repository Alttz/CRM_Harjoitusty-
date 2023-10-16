package s23.crm.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customer_id;
	
	@NotBlank
    @Column(name = "customer_name") // Specify the column name in the database
	private String name;
    @Column(name = "org_num") // Specify the column name in the database
	private int orgNum;
	
	public Customer() {
	}

	public Customer(@NotBlank String name, int orgNum) {
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

	public int getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(int orgNum) {
		this.orgNum = orgNum;
	}

	@Override
	public String toString() {
		return "Customer [id=" + customer_id + ", name=" + name + ", orgNum=" + orgNum + "]";
	}
	
	
	
	

}
