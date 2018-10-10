package admin.fe.controller.Maintenance.Division;

/**
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Division;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class DivisionDetailController extends CommonController {

    Textbox idDivision;
    Textbox nameDivision;
    Label idDivisionConfirm;
    Label nameDivisionConfirm;
    Button submitButton;
    Button confirmButton;
    Button clearButton;
    Button cancelButton;

    @Value("${led.Division.insert}")
    protected String divisionInsert;

    EventListener event = null;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        disabledComponent();
    }

    public void onClick$submitButton(){
        showConfirm();
    }

    public void onClick$clearButton(){
        idDivision.setValue("");
        nameDivision.setValue("");
    }

    public void onClick$confirmButton(){
        Division div = new Division();

        SendJSON send = new SendJSON();

        div.setDivisionCode(idDivision.getValue());
        div.setDivisionName(nameDivision.getValue());
        div.setCreatedDate(new Date());
        div.setCreatedBy("test");

        Messagebox.show("Are you sure want to save?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event = new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {
                        String result = send.insertDivision(div);

                        if(result.equals("200")){
                            Messagebox.show("Data Already Saved", "Information", Messagebox.OK , Messagebox.INFORMATION, event = new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Division/Division.zul",null,self);
                                }
                            });
                        }else if(result.equals("Failure")){
                            Messagebox.show("Data Already exists","Information", Messagebox.OK , Messagebox.INFORMATION, event = new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Division/Division.zul",null,self);
                                }
                            });

                        }else{
                            Messagebox.show("Data Failed to Save","Error",Messagebox.OK, Messagebox.ERROR,event = new EventListener(){
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Division/Division.zul",null,self);
                                }
                            });
                        }
                    } catch (JsonProcessingException e) {
                        Messagebox.show("Data Failed to Save","Error",Messagebox.OK, Messagebox.ERROR,event = new EventListener(){
                            public void onEvent(Event evt) throws InterruptedException {
                                navigateTo("layout/Division/Division.zul",null,self);
                            }
                        });
                    }
                }
            }
        });
    }

    public void onClick$cancelButton(){
        showInsert();
    }

    public void disabledComponent(){
        idDivisionConfirm.setVisible(false);
        nameDivisionConfirm.setVisible(false);
        confirmButton.setVisible(false);
        cancelButton.setVisible(false);
    }

    public void showConfirm(){
        idDivisionConfirm.setValue(idDivision.getValue());
        nameDivisionConfirm.setValue(nameDivision.getValue());

        idDivisionConfirm.setVisible(true);
        nameDivisionConfirm.setVisible(true);
        confirmButton.setVisible(true);
        cancelButton.setVisible(true);

        idDivision.setVisible(false);
        nameDivision.setVisible(false);
        submitButton.setVisible(false);
        clearButton.setVisible(false);
    }

    public void showInsert(){
        idDivisionConfirm.setVisible(false);
        nameDivisionConfirm.setVisible(false);
        confirmButton.setVisible(false);
        cancelButton.setVisible(false);

        idDivision.setVisible(true);
        nameDivision.setVisible(true);
        submitButton.setVisible(true);
        clearButton.setVisible(true);
    }
}
