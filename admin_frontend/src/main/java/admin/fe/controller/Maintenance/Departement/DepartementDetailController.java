package admin.fe.controller.Maintenance.Departement;

/**
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.controller.popup.PopupShowController;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.*;

public class DepartementDetailController extends CommonController implements PopupCallerDivisionInterface {

    Textbox idDivision;
    Textbox idDepartement;
    Textbox nameDepartement;

    Division div = new Division();
    PopupShowController popup = new PopupShowController();

    @Value("${led.Departement.insert}")
    protected String departementInsert;

    EventListener event = null;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        Departement dep = new Departement();

        SendJSON send = new SendJSON();

        if(div.getDivisionCode().equals("") || div.getDivisionCode() == null){
            dep.setDivisionCode("");
        }else{
            dep.setDivisionCode(div.getDivisionCode());
            System.out.println("division global terisi :"+div.getDivisionCode());
        }

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
                                    navigateTo(Resources.departementHome,null,self);
                                }
                            });
                        }else if(result.equals("Failure")){
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

    public void onClick$clearButton(){
        idDivision.setValue("");
        idDepartement.setValue("");
        nameDepartement.setValue("");
    }

    public void onClick$idDivision(){
        popup.showPopUpDivision(this);
    }

    public void onChanging$idDivision(){
        popup.showPopUpDivision(this);
    }

    public void onClick$btnDivSearch(){
        popup.showPopUpDivision(this);
    }

    @Override
    public void afterSelectDivision(Division division) {
        if(division != null){
            div = division;
            idDivision.setValue(division.getDivisionName());
        }
    }

    public void onClick$cancelButton(){
        navigateTo(Resources.departementHome,null,self);
    }

}
