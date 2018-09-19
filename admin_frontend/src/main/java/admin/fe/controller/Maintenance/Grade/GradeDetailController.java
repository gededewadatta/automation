package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class GradeDetailController extends CommonController {

    Textbox idDivision;

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idGradeName;

    Textbox idSubGradeName;

    @Value("${led.Grade.insert}")
    protected String gradeInsert;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){


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

                                grd.setGradeName(idGradeName.getValue());
                                grd.setGradeCode(idGrade.getValue());
                                grd.setDepartementCode(idDepartment.getValue());
                                grd.setDivisionCode(idDivision.getValue());
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

}
