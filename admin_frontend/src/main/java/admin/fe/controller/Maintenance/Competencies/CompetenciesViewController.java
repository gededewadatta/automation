package admin.fe.controller.Maintenance.Competencies;

import admin.fe.constant.AppProperties;
import admin.fe.controller.common.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;

public class CompetenciesViewController extends CommonController {

    @Autowired
    private ApplicationContext context;

    AppProperties appProperties = (AppProperties) context.getBean("appProperties");

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
        navigateTo(appProperties.getCompetencyHome(),null,self);
    }


}
