/**
 * 
 */
package admin.fe.model;

import java.util.Date;

//import javax.persistence.*; 

/**
 * @author gederanadewadatta
 *
 */
//@Entity
public class SubGrade {
	private String gradeCode;
	private String subGradeCode;
	private String subGradeName;
	private Date createdDate;
	private String createdBy;

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getSubGradeCode() {
        return subGradeCode;
    }

    public void setSubGradeCode(String subGradeCode) {
        this.subGradeCode = subGradeCode;
    }

    public String getSubGradeName() {
        return subGradeName;
    }

    public void setSubGradeName(String subGradeName) {
        this.subGradeName = subGradeName;
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
}
