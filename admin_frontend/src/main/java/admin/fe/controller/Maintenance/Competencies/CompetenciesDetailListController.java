package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.PopupCallerGradeInterface;
import admin.fe.engine.PopupCallerSubGradeInterface;
import admin.fe.model.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

public class CompetenciesDetailListController extends CommonController implements PopupCallerDepartmentInterface,PopupCallerGradeInterface,PopupCallerSubGradeInterface {

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idCompetencyCode;

    Textbox idCompetencyName;


    Window competenciesDetailListWdw;

    Division div = new Division();

    Departement dep = new Departement();

    Grade grd = new Grade();

    SubGrade subGrd = new SubGrade();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, false);
        competenciesDetailListWdw = (Window) comp;
    }

    public Map<String, Object> getArgs() {

        Map<String, Object> args = new HashMap<String, Object>();
        Competency competency = new Competency();
        competency.setGradeCode(idGrade.getValue());
        competency.setSubGradeCode(idSubGrade.getValue());
        competency.setCompetencyCode(idCompetencyCode.getValue());
        competency.setCompetencyName(idCompetencyName.getValue());
        competency.setDepartementCode(idDepartment.getValue());
        args.put("Competencies", competency);

        return  args;
    }

    public void onClick$btnDepartement(){

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Departement/DeptPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void onClick$btnGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();
        args.put("objectGrade", grade);
        args.put("departement", dep);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Grade/GradePopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }
    public void onClick$btnSubGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        SubGrade subgrd = new SubGrade();
        args.put("object", subgrd);
        args.put("grade",grd);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Grade/SubGradePopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }


    @Override
    public void afterSelectDepartement(Departement departement) {
        if(departement != null){
            dep = departement;
            idDepartment.setValue(departement.getDepartementCode());
        }
    }

    @Override
    public void afterSelectGrade(Grade grade) {
        if(grade != null){
            grd = grade;
            idGrade.setValue(grd.getGradeCode());
        }

    }

    @Override
    public void afterSelectSubGrade(SubGrade subGrade) {

        if(subGrade != null){
            subGrd = subGrade;
            idSubGrade.setValue(subGrd.getSubGradeCode());
        }
    }
}
