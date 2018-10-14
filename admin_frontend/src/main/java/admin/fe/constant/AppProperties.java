package admin.fe.constant;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Component
public class AppProperties {

    // Competency : Start //
    @Value("${competencies.home}")
    private String competencyHome;

    @Value("${competencies.detail}")
    private String competencyDetail;

    @Value("${competencies.detail.list}")
    private String competencyDetailList;

    @Value("${competencies.edit}")
    private String competencyEdit;

    @Value("${competencies.view}")
    private String competencyView;

    @Value("${competencies.popup}")
    private String competencyPopup;

    // Competency : stop

    @Value("${department.view.edit}")
    private String departementViewEdit;

    public String getDepartementViewEdit() {
        return departementViewEdit;
    }

    public String getCompetencyHome() {
        return competencyHome;
    }

    public void setCompetencyHome(String competencyHome) {
        this.competencyHome = competencyHome;
    }

    public void setDepartementViewEdit(String departementViewEdit) {
        this.departementViewEdit = departementViewEdit;
    }

    public String getCompetencyDetail() {
        return competencyDetail;
    }

    public void setCompetencyDetail(String competencyDetail) {
        this.competencyDetail = competencyDetail;
    }

    public String getCompetencyDetailList() {
        return competencyDetailList;
    }

    public void setCompetencyDetailList(String competencyDetailList) {
        this.competencyDetailList = competencyDetailList;
    }

    public String getCompetencyEdit() {
        return competencyEdit;
    }

    public void setCompetencyEdit(String competencyEdit) {
        this.competencyEdit = competencyEdit;
    }

    public String getCompetencyView() {
        return competencyView;
    }

    public void setCompetencyView(String competencyView) {
        this.competencyView = competencyView;
    }

    public String getCompetencyPopup() {
        return competencyPopup;
    }

    public void setCompetencyPopup(String competencyPopup) {
        this.competencyPopup = competencyPopup;
    }
}
