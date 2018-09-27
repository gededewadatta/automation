/**
 * 
 */
package admin.fe.model;

import java.util.Date;
import java.util.List;

//import javax.persistence.*; 

/**
 * @author gederanadewadatta
 *
 */
//@Entity
public class Grade {


    Long idGrade;
    private String departementCode;
    private String divisionCode;
    private String gradeCode;
    private String gradeName;
    private List<SubGrade> subgrades;
    private Date createdDate;
    private String createdBy;

    public Long getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Long idGrade) {
        this.idGrade = idGrade;
    }

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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public List<SubGrade> getSubgrades() {
        return subgrades;
    }

    public void setSubgrades(List<SubGrade> subgrades) {
        this.subgrades = subgrades;
    }
}
