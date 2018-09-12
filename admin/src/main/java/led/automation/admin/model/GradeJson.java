package led.automation.admin.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
public class GradeJson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_SEQ")
    @SequenceGenerator(sequenceName = "grade_seq", allocationSize = 1, name = "GRADE_SEQ")
    Long id;

    private String divisionCode;

    private String departementCode;

    private String gradeCode;

    private String gradeName;

    private Date createdDate;

    private String createdBy;

    private String subGradeCode;

    private String subGradeName;

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
