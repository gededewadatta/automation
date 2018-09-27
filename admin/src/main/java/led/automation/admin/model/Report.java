package led.automation.admin.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Author FikriAsandhita
 *
 */
@Entity
@Table(name = "REPORT")
@EntityListeners(AuditingEntityListener.class)

public class Report {

    @Id
    private Long id;

    @Column(name = "EMPLOYEE_CODE")
    private String employeeCode; //employee

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName; //employee

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "COMPETENCIES")
    private String competencies;

    @Column(name = "MARK")
    private String mark;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Report [" +
                "employeeCode=" + employeeCode +
                ", employeeName=" + employeeName +
                ", grade=" + grade +
                ", competencies=" + competencies +
                ", mark=" + mark + "]";
    }

}
