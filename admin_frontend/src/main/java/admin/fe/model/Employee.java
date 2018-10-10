/**
 * 
 */
package admin.fe.model;


import java.sql.Timestamp;
import java.util.Date;


/**
 * @author gederanadewadatta
 *
 */
 
public class Employee {

	private String id;
	private String departementCode;
	private String gradeCode;
	private String divisionCode;
	private String subGradeCode;
	private String employeeCode;
	private String employeeName;
	private Date createdDate;
	private String createdBy;
	private String userName;

	public String getDepartementCode() {
		return departementCode;
	}

	public void setDepartementCode(String departementCode) {
		this.departementCode = departementCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getSubGradeCode() {
		return subGradeCode;
	}

	public void setSubGradeCode(String subGradeCode) {
		this.subGradeCode = subGradeCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
