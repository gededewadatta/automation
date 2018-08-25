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
public class Departement {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTEMENT_SEQ")
    @SequenceGenerator(sequenceName = "departement_seq", allocationSize = 1, name = "DEPARTEMENT_SEQ")
    private Long id;
	
	@Column(name = "DIVISION_CODE")
	private String divisionCode;
    
	@Column(name = "DEPARTEMENT_CODE")
	private String departementCode;
    
    @Column(name = "DEPARTEMENT_NAME")
    private String departementName;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
    
    @Column(name = "CREATED_BY")
    private String createdBy;

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
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}

	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	/**
	 * @return the departementCode
	 */
	public String getDepartementCode() {
		return departementCode;
	}

	/**
	 * @param departementCode the departementCode to set
	 */
	public void setDepartementCode(String departementCode) {
		this.departementCode = departementCode;
	}

	/**
	 * @return the departementName
	 */
	public String getDepartementName() {
		return departementName;
	}

	/**
	 * @param departementName the departementName to set
	 */
	public void setDepartementName(String departementName) {
		this.departementName = departementName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Departement [id=" + id + ", divisionCode=" + divisionCode + ", departementCode=" + departementCode
				+ ", departementName=" + departementName + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ "]";
	}

	 
    
    
}
