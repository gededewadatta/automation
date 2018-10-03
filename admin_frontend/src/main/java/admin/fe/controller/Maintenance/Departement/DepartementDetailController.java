package admin.fe.controller.Maintenance.Departement;

/**
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
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
    Label idDivisionConfirm;
    Label idDepartementConfirm;
    Label nameDepartementConfirm;
    Button btnDivSearch;
    Button submitButton;
    Button confirmButton;
    Button clearButton;
    Button cancelButton;

    Division div = new Division();

    @Value("${led.Departement.insert}")
    protected String departementInsert;

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
        idDepartement.setValue("");
        nameDepartement.setValue("");
    }

    public void onClick$confirmButton(){
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

    public void onClick$cancelButton(){
        showInsert();
    }

    public void onClick$idDivision(){
        showPopUpDivision();
    }

    public void onChanging$idDivision(){
        showPopUpDivision();
    }


    public void onClick$btnDivSearch(){
        showPopUpDivision();
    }

    public void showPopUpDivision(){
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
            idDivision.setValue(division.getDivisionName());
        }
    }

    public void disabledComponent(){
        idDivisionConfirm.setVisible(false);
        idDepartementConfirm.setVisible(false);
        nameDepartementConfirm.setVisible(false);
        confirmButton.setVisible(false);
        cancelButton.setVisible(false);
    }

    public void showConfirm(){
        idDivisionConfirm.setValue(idDivision.getValue());
        idDepartementConfirm.setValue(idDepartement.getValue());
        nameDepartementConfirm.setValue(nameDepartement.getValue());

        idDivisionConfirm.setVisible(true);
        idDepartementConfirm.setVisible(true);
        nameDepartementConfirm.setVisible(true);
        confirmButton.setVisible(true);
        cancelButton.setVisible(true);

        idDivision.setVisible(false);
        idDepartement.setVisible(false);
        nameDepartement.setVisible(false);
        btnDivSearch.setVisible(false);
        submitButton.setVisible(false);
        clearButton.setVisible(false);
    }

    public void showInsert(){
        idDivisionConfirm.setVisible(false);
        idDepartementConfirm.setVisible(false);
        nameDepartementConfirm.setVisible(false);
        confirmButton.setVisible(false);
        cancelButton.setVisible(false);

        idDivision.setVisible(true);
        idDepartement.setVisible(true);
        nameDepartement.setVisible(true);
        btnDivSearch.setVisible(true);
        submitButton.setVisible(true);
        clearButton.setVisible(true);
    }
}
