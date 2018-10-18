package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GradeEditController extends CommonController implements PopupCallerDepartmentInterface, PopupCallerDivisionInterface {

    Textbox idDivision;

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idGradeName;

    Textbox idSubGradeName;

    Textbox idGradeId;

    Textbox idSubGradeId;

    Division div = new Division();

    Departement dep = new Departement();

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
        idGradeId.setValue(String.valueOf(arg.get("idGrade")));
        idSubGradeId.setValue(String.valueOf(arg.get("idSubGrade")));

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

                                GradeJson grdJson = new GradeJson();
                                SendJSON send = new SendJSON();

                                grdJson.setIdGrade(Long.valueOf(idGradeId.getValue()));
                                grdJson.setGradeName(idGradeName.getValue());
                                grdJson.setGradeCode(idGrade.getValue());
                                grdJson.setDepartementCode(idDepartment.getValue());
                                grdJson.setDivisionCode(idDivision.getValue());
                                grdJson.setIdSubGrade(Long.valueOf(idSubGradeId.getValue()));
                                grdJson.setGradeCode(idGrade.getValue());
                                grdJson.setSubGradeCode(idSubGrade.getValue());
                                grdJson.setSubGradeName(idSubGradeName.getValue());
                                grdJson.setDepartementCode(idDepartment.getValue());
                                grdJson.setCreatedDate(new Date());
                                grdJson.setCreatedBy("Admin");
                                try {
                                    String resultGrade = send.updateGrade(grdJson);

                                    if(resultGrade.equals("200")){
                                        Messagebox.show("Data Already Updated");
                                        navigateTo(Resources.gradeHome,null,self);
                                    }else{
                                        Messagebox.show("Data Failed To Update ");
                                        navigateTo(Resources.gradeHome,null,self);
                                    }

                                } catch (JsonProcessingException e) {
                                    Messagebox.show("All data Failed To Update");
                                    navigateTo(Resources.gradeHome,null,self);
                                }

                        }
                    }
                });
    }

    public void onClick$clearButton(){

        idDivision.setValue("");
        idGradeName.setValue("");
        idGrade.setValue("");
        idDepartment.setValue("");
        idSubGrade.setValue("");
        idSubGradeName.setValue("");

    }

    public void onClick$btnDepartment() {

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

        Map<String, Object> args = new HashMap<String, Object>();
        Division div = new Division();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.divisionPopup, self, args);
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
            idDivision.setValue(division.getDivisionCode());
        }

    }

    public void afterSelectDepartement(Departement departement) {
        if (departement != null) {
            dep = departement;
            idDepartment.setValue(departement.getDepartementCode());
        }
    }

    public void onClick$cancelButton(){
        navigateTo(Resources.gradeHome,null,self);
    }

}
