/**
 * 
 */
package led.automation.admin.model;

import java.util.Date;

import javax.persistence.*; 

/**
 * @author gederanadewadatta
 *
 */
@Entity
public class Division {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQ")
    @SequenceGenerator(sequenceName = "division_seq", allocationSize = 1, name = "DIVISION_SEQ")
	private Long id;
	
	@Column(name = "DIVISION_CODE")
	private String divisonCode;
    
    @Column(name = "DIVISION_NAME")
    private String divisionName;
      
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    
    @Column(name = "CREATED_BY")
    private String createdBy;
    

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Division [id=" + id + ", divisonCode=" + divisonCode + ", divisionName=" + divisionName
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the divisonCode
	 */
	public String getDivisonCode() {
		return divisonCode;
	}

	/**
	 * @param divisonCode the divisonCode to set
	 */
	public void setDivisonCode(String divisonCode) {
		this.divisonCode = divisonCode;
	}

	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}

	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
    
    
    
}
