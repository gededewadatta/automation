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
public class Competency {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_SEQ")
    @SequenceGenerator(sequenceName = "grade_seq", allocationSize = 1, name = "GRADE_SEQ")
    Long id;
	
	@Column(name = "DEPARTEMENT_CODE")
	private String departementCode;
    
	@Column(name = "GRADE_CODE")
	private String gradeCode;
    
    @Column(name = "COMPETENCY")
    private String competency;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
    
    @Column(name = "CREATED_BY")
    private String createdBy;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Competency [id=" + id + ", departementCode=" + departementCode + ", gradeCode=" + gradeCode
				+ ", competency=" + competency + ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
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
	 * @return the gradeCode
	 */
	public String getGradeCode() {
		return gradeCode;
	}

	/**
	 * @param gradeCode the gradeCode to set
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	/**
	 * @return the competency
	 */
	public String getCompetency() {
		return competency;
	}

	/**
	 * @param competency the competency to set
	 */
	public void setCompetency(String competency) {
		this.competency = competency;
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
