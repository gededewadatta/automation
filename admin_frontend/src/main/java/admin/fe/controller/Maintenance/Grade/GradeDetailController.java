package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GradeDetailController extends CommonController implements PopupCallerDivisionInterface,PopupCallerDepartmentInterface {

    Textbox idDivision;

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idGradeName;

    Textbox idSubGradeName;

    Division div = new Division();
    Departement dep = new Departement();

    @Value("${led.Grade.insert}")
    protected String gradeInsert;

    EventListener event = null;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){

        GradeJson grdJson = new GradeJson();
        SendJSON send = new SendJSON();

        if(div.getDivisionCode() == null||div.getDivisionCode().equals("")){
            grdJson.setDivisionCode("");
        }else{
            grdJson.setDivisionCode(div.getDivisionCode());
        }

        if(dep.getDepartementCode() == null||dep.getDepartementCode().equals("")){
            grdJson.setDepartementCode("");
            grdJson.setDepartementCode("");
        }else{
            grdJson.setDepartementCode(dep.getDepartementCode());
            grdJson.setDepartementCode(dep.getDepartementCode());
        }

        grdJson.setGradeName(idGradeName.getValue());
        grdJson.setGradeCode(idGrade.getValue());
        grdJson.setSubGradeCode(idSubGrade.getValue());
        grdJson.setSubGradeName(idSubGradeName.getValue());
        grdJson.setCreatedDate(new Date());
        grdJson.setCreatedBy("Admin");
        grdJson.setCreatedDate(grdJson.getCreatedDate());
        grdJson.setCreatedBy(grdJson.getCreatedBy());

        Messagebox.show("Are you sure want to save?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event = new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {
                        String resultGrade = send.insertGrade(grdJson);

                        if(resultGrade.equals("200")){
                            Messagebox.show("Data Already Saved", "Information", Messagebox.OK , Messagebox.INFORMATION, event = new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Grade/Grade.zul",null,self);
                                }
                            });
                        }else if(resultGrade.equals("Failure")){
                            Messagebox.show("Data Already exists");

                        }else{
                            Messagebox.show("Data Failed To save");
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void clearTextBox(){

        idDivision.setValue("");
        idGradeName.setValue("");
        idGrade.setValue("");
        idDepartment.setValue("");
        idSubGrade.setValue("");
        idSubGradeName.setValue("");

    }

    public void onClick$clearButton(){
        clearTextBox();
    }

    public void onClick$btnDepartment(){

        Map<String, Object> args = new HashMap<String, Object>();
        Departement dep = new Departement();
        args.put("object", dep);
        args.put("division", div);
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
    public void onClick$btnDivision(){

        Map<String, Object> args = new HashMap<String, Object>();
        Division div = new Division();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Division/DivisionPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }


    @Override
    public void afterSelectDivision(Division division) {

        if(division != null){
            div = division;
            idDivision.setValue(div.getDivisionName());
        }

    }

    @Override
    public void afterSelectDepartement(Departement departement) {
        if(departement != null){
            dep = departement;
            idDepartment.setValue(dep.getDepartementName());
        }
    }

    public void onClick$cancelButton(){
        navigateTo("layout/Grade/GradeDetail.zul",null,self);
    }

}
