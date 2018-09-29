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
@Table(name = "SUBGRADE")
@EntityListeners(AuditingEntityListener.class)

public class SubGrade {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBGRADE_SEQ")
	@SequenceGenerator(sequenceName = "subgrade_seq", allocationSize = 1, name = "SUBGRADE_SEQ")
	private Long idSubGrade;

	@Column(name = "GRADE_CODE")
	private String gradeCode;
	@Column(name = "SUB_GRADE_CODE")
	private String subGradeCode;
	@Column(name = "SUB_GRADE_NAME")
	private String subGradeName;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "CREATED_BY")
	private String createdBy;

	/**
	 * @return the id
	 */
	public Long getIdSubGrade() {
		return idSubGrade;
	}

	public void setIdSubGrade(Long idSubGrade) {
		this.idSubGrade = idSubGrade;
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
	 * @return the subGradeCode
	 */
	public String getSubGradeCode() {
		return subGradeCode;
	}
	/**
	 * @param subGradeCode the subGradeCode to set
	 */
	public void setSubGradeCode(String subGradeCode) {
		this.subGradeCode = subGradeCode;
	}
	/**
	 * @return the subGradeName
	 */
	public String getSubGradeName() {
		return subGradeName;
	}
	/**
	 * @param subGradeName the subGradeName to set
	 */
	public void setSubGradeName(String subGradeName) {
		this.subGradeName = subGradeName;
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
		return "SubGrade [id=" + idSubGrade + ", gradeCode=" + gradeCode + ", subGradeCode=" + subGradeCode + ", subGradeName="
				+ subGradeName + ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
	}

	
}
