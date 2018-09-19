package admin.fe.controller.Maintenance.Employee;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.model.Employee;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class EmployeeViewController extends CommonController {

    private Window employeeViewCont;
    private AbstractMainWindowTransaction parent;

    Label idDivision;
    Label idDepartment;
    Label idGrade;
    Label idSubGrade;
    Label idEmployeeId;
    Label idEmployeeName;


    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        initView();

    }

    public void initView(){

        idDivision.setValue((String) arg.get("divisionCode"));
        idDepartment.setValue((String) arg.get("departementCode"));
        idEmployeeId.setValue((String) arg.get("employeeCode"));
        idEmployeeName.setValue((String) arg.get("employeeName"));
        idSubGrade.setValue((String)arg.get("subGradeCode"));
        idGrade.setValue((String)arg.get("gradeCode"));

    }

    public void onClick$backButton(){
        navigateTo("layout/Employee/Employee.zul",null,self);
    }
}
