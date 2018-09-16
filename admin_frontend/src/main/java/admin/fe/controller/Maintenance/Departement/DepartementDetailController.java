package admin.fe.controller.Maintenance.Departement;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class DepartementDetailController extends CommonController {

    @Wire
    Textbox idDivision;

    @Wire
    Textbox idDepartement;

    @Wire
    Textbox nameDepartement;

    @Value("${led.Departement.insert}")
    protected String departementInsert;

    EventListener event = null;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

        Departement dep = new Departement();

        SendJSON send = new SendJSON();

        System.out.println("Ini Fucking Submit2");
        dep.setDivisionCode(idDivision.getValue());
        dep.setDepartementCode(idDepartement.getValue());
        dep.setDepartementName(nameDepartement.getValue());
        dep.setCreatedDate(new Date());
        dep.setCreatedBy("test");

        Messagebox.show("Are you sure want to save?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event = new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {
                        String result = send.insertDepartement(dep);

                        if(result.equals("200")){
                            Messagebox.show("Data Already Saved", "Information", Messagebox.OK , Messagebox.INFORMATION, event = new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Departement/Departement.zul",null,self);
                                }
                            });
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

    public void onClick$clearButton(){
        idDivision.setValue("");
        idDepartement.setValue("");
        nameDepartement.setValue("");
    }
//test
}
