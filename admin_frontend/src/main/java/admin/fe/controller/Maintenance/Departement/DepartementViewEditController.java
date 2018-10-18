package admin.fe.controller.Maintenance.Departement;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DepartementViewEditController extends CommonController implements PopupCallerDivisionInterface {

    Label idDivisionView;
    Label idDepartementView;
    Label nameDepartementView;
    Textbox idDivisionEdit;
    Textbox idDepartementEdit;
    Textbox nameDepartementEdit;
    Button backButton;
    Button submitButton;
    Button clearButton;
    Button divisionButton;


    Division div = new Division();

    private final String TYPE_SHOW_VIEW = "VIEW";
    private final String TYPE_SHOW_EDIT = "EDIT";

    Departement dep = new Departement();
    SendJSON send = new SendJSON();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this,true);
        getAllData();
        initView();
    }

    public void getAllData(){
        dep.setId((Long) arg.get("id"));
        dep.setDivisionCode((String) arg.get("divisionCode"));
        dep.setDepartementCode((String) arg.get("departmentCode"));
        dep.setDepartementName((String) arg.get("departmentName"));
        dep.setCreatedBy((String) arg.get("createdBy"));
    }

    public void initView() {
        idDivisionView.setValue(dep.getDivisionCode());
        idDepartementView.setValue(dep.getDepartementCode());
        nameDepartementView.setValue(dep.getDepartementName());
        idDivisionEdit.setValue(dep.getDivisionCode());
        idDepartementEdit.setValue(dep.getDepartementCode());
        nameDepartementEdit.setValue(dep.getDepartementName());
        disableComponent();
    }

    public void disableComponent() {
        if((String) arg.get("type") == TYPE_SHOW_VIEW){
            idDivisionEdit.setVisible(false);
            idDepartementEdit.setVisible(false);
            nameDepartementEdit.setVisible(false);
            divisionButton.setVisible(false);
            submitButton.setVisible(false);
            clearButton.setVisible(false);
            backButton.setLabel("Back");
        }
        else if((String) arg.get("type") == TYPE_SHOW_EDIT){
            idDivisionView.setVisible(false);
            idDepartementView.setVisible(false);
            nameDepartementView.setVisible(false);
            idDivisionEdit.setDisabled(true);
            idDepartementEdit.setDisabled(true);
            backButton.setVisible(true);
            backButton.setLabel("Cancel");
        }
    }

    public void onClick$divisionButton(){
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

    @Override
    public void afterSelectDivision(Division division) {
        if(division != null){
            div = division;
            idDivisionEdit.setValue(division.getDivisionCode());
        }

    }

    public void onClick$backButton(){
        navigateTo(Resources.departementHome,null,self);
    }

    public void onClick$submitButton(){
        dep.setDivisionCode(idDivisionEdit.getValue());
        dep.setDepartementCode(idDepartementEdit.getValue());
        dep.setDepartementName(nameDepartementEdit.getValue());
        dep.setCreatedDate(new Date());

        Messagebox.show("Are you sure want to Update?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {
                        String resultGrade = send.updateDepartement(dep);

                        if (resultGrade.equals("200")) {
                            Messagebox.show("Data Already Updated", "Information", Messagebox.OK, Messagebox.INFORMATION, new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo(Resources.departementHome, null, self);
                                }
                            });
                        } else if (!resultGrade.equals("200")) {
                            Messagebox.show("Data Failed To update to Table Departement");
                        }

                    } catch (JsonProcessingException e) {
                        Messagebox.show("All data Failed To Update");
                    }
                }
            }
        });
    }

    public void onClick$clearButton(){
        nameDepartementEdit.setValue("");
    }

}
