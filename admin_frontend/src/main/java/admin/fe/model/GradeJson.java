package admin.fe.model;

import java.util.Date;

public class GradeJson {

    private String idGrade;
    private String idSubGrade;
    private String gradeCode;
    private String subGradeCode;
    private String subGradeName;
    private String departementCode;
    private String divisionCode;
    private String gradeName;
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

    public String getDepartementCode() {
        return departementCode;
    }

    public void setDepartementCode(String departementCode) {
        this.departementCode = departementCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
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

    public String getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(String idGrade) {
        this.idGrade = idGrade;
    }

    public String getIdSubGrade() {
        return idSubGrade;
    }

    public void setIdSubGrade(String idSubGrade) {
        this.idSubGrade = idSubGrade;
    }
}
