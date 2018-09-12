package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Textbox;

public class GradeEditController extends CommonController {

    Textbox idDivision;

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idGradeName;

    Textbox idSubGradeName;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        initView();
    }

    public void initView(){

        idDivision.setValue((String) arg.get("divisionCode"));
        idDepartment.setValue((String) arg.get("departementCode"));
        idSubGrade.setValue((String)arg.get("subGradeCode"));
        idGrade.setValue((String)arg.get("gradeCode"));
        idGradeName.setValue((String)arg.get("gradeName"));
        idSubGradeName.setValue((String)arg.get("subGradeName"));

    }

    public void onClick$submitButton(){




    }

}
