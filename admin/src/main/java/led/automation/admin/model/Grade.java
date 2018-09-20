/**
 * 
 */
package led.automation.admin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener; 

/**
 * @author gederanadewadatta
 *
 */
@Entity
@Table(name = "GRADE")
@EntityListeners(AuditingEntityListener.class)

public class Grade {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_SEQ")
    @SequenceGenerator(sequenceName = "grade_seq", allocationSize = 1, name = "GRADE_SEQ")
    Long idGrade;

	@Column(name = "DIVISION_CODE")
	private String divisionCode;

	@Column(name = "DEPARTEMENT_CODE")
	private String departementCode;
    
	@Column(name = "GRADE_CODE")
	private String gradeCode;
    
    @Column(name = "GRADE_NAME")
    private String gradeName;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
    
    @Column(name = "CREATED_BY")
    private String createdBy;

	/**
	 * @return the id
	 */

	public Long getIdGrade() {
		return idGrade;
	}

	public void setIdGrade(Long idGrade) {
		this.idGrade = idGrade;
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
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * @param gradeName the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Grade [id=" + idGrade + ", departementCode=" + departementCode + ", gradeCode=" + gradeCode + ", gradeName="
				+ gradeName + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", divisionCode="+divisionCode+"]";
	}
 
    
	 
    
    
}
