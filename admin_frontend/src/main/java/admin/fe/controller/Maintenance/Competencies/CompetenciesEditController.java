package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.engine.*;
import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CompetenciesEditController  extends CommonController implements PopupCallerDepartmentInterface, PopupCallerDivisionInterface, PopupCallerGradeInterface,
        PopupCallerSubGradeInterface {

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idCompetencies;

    Textbox idCompetenciesId;

    Textbox idCompetenciesName;

//    Textbox idDivision;

    Division div = new Division();

    Departement dep = new Departement();

    Grade grd = new Grade();

    SubGrade subGrd = new SubGrade();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        initView();
    }

    public void initView(){

        idCompetenciesId.setValue((String)arg.get("id"));
        idDepartment.setValue((String) arg.get("departmentCode"));
        idSubGrade.setValue((String)arg.get("subGradeCode"));
        idGrade.setValue((String)arg.get("gradeCode"));
        idCompetencies.setValue((String)arg.get("competencyName"));

    }

    public void onClick$submitButton(){

        showConfirmDialog("Do you want Update Data");

    }

    public void showConfirmDialog(String message) {
        Messagebox.show(message, "confirm",
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                Messagebox.NO, new SerializableEventListener() {

                    private static final long serialVersionUID = -8695776168749565854L;

                    @Override
                    public void onEvent(Event event) throws Exception {
                        int data = (Integer) event.getData();
                        switch (data) {
                            case Messagebox.YES:

                                SendJSON send = new SendJSON();


                                try {
                                    String result = "";

                                    Competency cmp = new Competency();

                                    cmp.setId(idCompetenciesId.getValue());
                                    cmp.setSubGradeCode(idSubGrade.getValue());
                                    cmp.setDepartementCode(idDepartment.getValue());
                                    cmp.setGradeCode(idGrade.getValue());
                                    cmp.setCompetencyCode(idCompetencies.getValue());
                                    cmp.setCompetencyName(idCompetenciesName.getValue());

                                    result = send.updateCompetency(cmp);

                                    if(result.equals("200")){
                                        Messagebox.show("Data Already Saved");
                                        navigateTo(Resources.competenciesHome,null,self);
                                    }

                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                }

                        }
                    }
                });
    }

    public void onClick$clearButton(){

        idCompetenciesId.setValue("");
        idDepartment.setValue("");
        idSubGrade.setValue("");
        idGrade.setValue("");
        idCompetencies.setValue("");

    }

    public void onClick$btnDepartement() {

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
        args.put("division", div);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.departementPopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void onClick$btnDivision() {

//        Map<String, Object> args = new HashMap<String, Object>();
//        Division div = new Division();
//        args.put("object", div);
//        args.put("caller", this);
//        Component c = Executions.createComponents("layout/Division/DivisionPopup.zul", self, args);
//        try {
//            onModalToTop((Window) c);
//        } catch (SuspendNotAllowedException e1) {
//            Messagebox.show(e1.getMessage());
//        } catch (InterruptedException e1) {
//            Messagebox.show(e1.getMessage());
//        }

    }

    public void onClick$btnGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();
        args.put("objectGrade", grade);
        args.put("departement", dep);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.gradePopup, self, args);
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
        Component c = Executions.createComponents(Resources.subgradeopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }


    public void afterSelectDivision(Division division) {

        if (division != null) {
            div = division;
//            idDivision.setValue(division.getDivisionCode());
        }

    }

    public void afterSelectDepartement(Departement departement) {
        if (departement != null) {
            dep = departement;
            idDepartment.setValue(departement.getDepartementCode());
        }
    }

    public void afterSelectGrade(Grade grade) {
        if (grade != null) {
            grd = grade;
            idGrade.setValue(grd.getGradeCode());
        }

    }

    public void afterSelectSubGrade(SubGrade subGrade) {

        if (subGrade != null) {
            subGrd = subGrade;
            idSubGrade.setValue(subGrd.getSubGradeCode());
        }
    }

    public void onClick$cancelButton(){
        navigateTo(Resources.competenciesHome,null,self);
    }

}
