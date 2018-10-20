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
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CompetenciesViewEditController extends CommonController implements PopupCallerDepartmentInterface, PopupCallerGradeInterface,
        PopupCallerSubGradeInterface {

    Textbox idDepartment;
    Textbox idGrade;
    Textbox idSubGrade;
    Textbox idCompetencies;
    Textbox idCompetenciesName;
    Label idDepartmentView;
    Label idGradeView;
    Label idSubGradeView;
    Label idCompetenciesView;
    Label idCompetenciesNameView;
    Button btnDepartement;
    Button btnGrade;
    Button btnSubGrade;
    Button backButton;
    Button submitButton;
    Button clearButton;

    private final String TYPE_SHOW_VIEW = "VIEW";
    private final String TYPE_SHOW_EDIT = "EDIT";

    Departement dep = new Departement();
    Grade grd = new Grade();
    SubGrade subGrd = new SubGrade();
    Competency comp = new Competency();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        getAllData();
        initView();
    }

    public void getAllData(){
        comp.setId((String)arg.get("id"));
        comp.setDepartementCode((String) arg.get("departmentCode"));
        comp.setSubGradeCode((String)arg.get("subGradeCode"));
        comp.setGradeCode((String)arg.get("gradeCode"));
        comp.setCompetencyCode((String)arg.get("competencyCode"));
        comp.setCompetencyName((String)arg.get("competencyName"));
        comp.setCreatedBy((String)arg.get("createdBy"));
    }

    public void initView(){
        idDepartment.setValue(comp.getDepartementCode());
        idDepartmentView.setValue(comp.getDepartementCode());
        idGrade.setValue(comp.getGradeCode());
        idGradeView.setValue(comp.getGradeCode());
        idSubGrade.setValue(comp.getSubGradeCode());
        idSubGradeView.setValue(comp.getSubGradeCode());
        idCompetencies.setValue(comp.getCompetencyCode());
        idCompetenciesView.setValue(comp.getCompetencyCode());
        idCompetenciesName.setValue(comp.getCompetencyName());
        idCompetenciesNameView.setValue(comp.getCompetencyName());
        disableComponent();
    }

    public void disableComponent(){
        if((String) arg.get("type") == TYPE_SHOW_VIEW){
            idDepartment.setVisible(false);
            idGrade.setVisible(false);
            idSubGrade.setVisible(false);
            idCompetencies.setVisible(false);
            idCompetenciesName.setVisible(false);
            btnDepartement.setVisible(false);
            btnGrade.setVisible(false);
            btnSubGrade.setVisible(false);
            submitButton.setVisible(false);
            clearButton.setVisible(false);
            backButton.setLabel("Cancel");
        }
        else if((String) arg.get("type") == TYPE_SHOW_EDIT ){
            idDepartmentView.setVisible(false);
            idGradeView.setVisible(false);
            idSubGradeView.setVisible(false);
            idCompetenciesView.setVisible(false);
            idCompetenciesNameView.setVisible(false);
            backButton.setLabel("Cancel");
        }
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

                                    cmp.setId(comp.getId());
                                    cmp.setSubGradeCode(idSubGrade.getValue());
                                    cmp.setDepartementCode(idDepartment.getValue());
                                    cmp.setGradeCode(idGrade.getValue());
                                    cmp.setCompetencyCode(idCompetencies.getValue());
                                    cmp.setCompetencyName(idCompetenciesName.getValue());
                                    cmp.setCreatedBy(comp.getCreatedBy());

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

        idDepartment.setValue("");
        idSubGrade.setValue("");
        idGrade.setValue("");
        idCompetencies.setValue("");
        idCompetenciesName.setValue("");

    }

    public void onClick$btnDepartement() {

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
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

    public void onClick$backButton(){
        navigateTo(Resources.competenciesHome,null,self);
    }

}
