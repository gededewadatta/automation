package led.automation.admin.model;

import javax.persistence.*;

@Entity
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
    @SequenceGenerator(sequenceName = "employee_seq", allocationSize = 1, name = "EMPLOYEE_SEQ")
    private Long id;
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    @Column(name = "GRADE")
    private String grade;
    @Column(name = "RESULT")
    private String result;

    @Override
    public String toString() {
        return "SubmitQuestion [id=" + id + ", employeeId=" + employeeId + ", result=" + result
                +"]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
