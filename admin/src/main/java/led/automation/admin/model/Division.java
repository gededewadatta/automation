/**
 * 
 */
package led.automation.admin.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener; 

/**
 * @author gederanadewadatta
 *
 */
@Entity
@Table(name = "DIVISION")
@EntityListeners(AuditingEntityListener.class)

public class Division {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQ")
    @SequenceGenerator(sequenceName = "division_seq", allocationSize = 1, name = "DIVISION_SEQ")
	private Long id;
	
	@Column(name = "DIVISION_CODE")
	private String divisionCode;
    
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
		return "Division [id=" + id + ", divisionCode=" + divisionCode + ", divisionName=" + divisionName
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
	public String getDivisionCode() {
		return divisionCode;
	}

	/**
	 * @param divisionCode the divisonCode to set
	 */
	public void setDivisonCode(String divisionCode) {
		this.divisionCode = divisionCode;
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
