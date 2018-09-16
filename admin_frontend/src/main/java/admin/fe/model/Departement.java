package admin.fe.model;

/*
 * @Author FikriAsandhita
 *
 */

import java.util.Date;

public class Departement {

    private Long id;
    private String divisionCode;
    private String departementCode;
    private String departementName;
    private Date createdDate;
    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departmentName) {
        this.departementName = departmentName;
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
