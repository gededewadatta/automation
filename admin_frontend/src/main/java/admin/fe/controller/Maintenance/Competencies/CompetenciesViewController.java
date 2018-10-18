package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;

public class CompetenciesViewController extends CommonController {

    Label idDepartment;
    Label idGrade;
    Label idSubGrade;
    Label idCompetencies;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        initView();
    }

    public void initView(){

        idDepartment.setValue((String) arg.get("departmentCode"));
        idSubGrade.setValue((String)arg.get("subGradeCode"));
        idGrade.setValue((String)arg.get("gradeCode"));
        idCompetencies.setValue((String)arg.get("competencyName"));

    }

    public void onClick$backButton(){
        navigateTo(Resources.competenciesHome,null,self);
    }


}
