package admin.fe.controller.Maintenance.Departement;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class DepartementViewEditController extends CommonController {

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
        }
        else if((String) arg.get("type") == TYPE_SHOW_EDIT){
            idDivisionView.setVisible(false);
            idDepartementView.setVisible(false);
            nameDepartementView.setVisible(false);
            idDivisionEdit.setDisabled(true);
            idDepartementEdit.setDisabled(true);
            backButton.setVisible(false);
        }

    }

    public void onClick$backButton(){
        navigateTo("layout/Departement/Departement.zul",null,self);
    }

    public void onClick$submitButton(){
        dep.setDivisionCode(idDivisionEdit.getValue());
        dep.setDepartementCode(idDepartementEdit.getValue());
        dep.setDepartementName(nameDepartementEdit.getValue());
        Messagebox.show("Are you sure want to Update?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {
                        String resultGrade = send.insertDepartement(dep);

                        if (resultGrade.equals("200")) {
                            Messagebox.show("Data Already Updated", "Information", Messagebox.OK, Messagebox.INFORMATION, new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Departement/Departement.zul", null, self);
                                }
                            });
                        } else if (!resultGrade.equals("200")) {
                            Messagebox.show("Data Failed To update to Table Division");
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
