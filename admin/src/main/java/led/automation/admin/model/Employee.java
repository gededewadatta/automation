/**
 * 
 */
package led.automation.admin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author gederanadewadatta
 *
 */
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
	@SequenceGenerator(sequenceName = "employee_seq", allocationSize = 1, name = "EMPLOYEE_SEQ")
	private Long id;
	@Column(name = "DEPARTEMENT_CODE")
	private String departementCode;
	@Column(name = "GRADE_CODE")
	private String gradeCode;
	@Column(name = "DIVISION_CODE")
	private String divisionCode;
	@Column(name = "SUBGRADE_CODE")
	private String subGradeCode;
	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;
	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private String createdDate;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", departementCode=" + departementCode + ", gradeCode=" + gradeCode
				+ ", divisionCode=" + divisionCode + ", subGradeCode=" + subGradeCode + ", employeeCode=" + employeeCode
				+ ", employeeName=" + employeeName + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
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
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}
	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
