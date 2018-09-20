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

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){

        showConfirmDialog("Do you want to Insert Data ?");
    }

    public void clearTextBox(){

        idDivision.setValue("");
        idGradeName.setValue("");
        idGrade.setValue("");
        idDepartment.setValue("");
        idSubGrade.setValue("");
        idSubGradeName.setValue("");

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

                                Grade grd = new Grade();
                                SubGrade subGrd = new SubGrade();

                                SendJSON send = new SendJSON();

                                if(div.getDivisionCode() == null||div.getDivisionCode().equals("")){
                                    grd.setDivisionCode("");
                                }else{
                                    grd.setDivisionCode(div.getDivisionCode());
                                }

                                if(dep.getDepartementCode() == null||dep.getDepartementCode().equals("")){
                                    grd.setDepartementCode("");
                                }else{
                                    grd.setDepartementCode(dep.getDepartementCode());
                                }

                                grd.setGradeName(idGradeName.getValue());
                                grd.setGradeCode(idGrade.getValue());
                                subGrd.setGradeCode(grd.getGradeCode());
                                subGrd.setSubGradeCode(idSubGrade.getValue());
                                subGrd.setSubGradeName(idSubGradeName.getValue());
                                grd.setCreatedDate(new Date());
                                grd.setCreatedBy("Burhan");
                                subGrd.setCreatedDate(grd.getCreatedDate());
                                subGrd.setCreatedBy(grd.getCreatedBy());

                                try {
                                    String resultGrade = send.insertGrade(grd);
                                    String resultSubGrade =  send.insertSubGrade(subGrd);

                                    if(resultGrade.equals("200") && resultSubGrade.equals("200")){
                                        Messagebox.show("Data Already Saved");
                                        clearTextBox();
                                    }else if(!resultGrade.equals("200")){
                                        Messagebox.show("Data Failed To save to Table Grade");
                                    }else if(!resultSubGrade.equals("200")){
                                        Messagebox.show("Data Failed To save to Table Sub Grade");
                                    }else{
                                        Messagebox.show("All data Failed To Save");
                                    }

                                } catch (JsonProcessingException e) {
                                    Messagebox.show("All data Failed To Save");
                                }

                        }
                    }
                });
    }

    public void onClick$clearButton(){
        clearTextBox();
    }

    public void onClick$btnDepartment(){

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
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

}
