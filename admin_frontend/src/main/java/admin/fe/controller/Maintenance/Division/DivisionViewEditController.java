package admin.fe.controller.Maintenance.Division;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Division;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class DivisionViewEditController extends CommonController {

    Label idDivisionView;
    Label nameDivisionView;
    Textbox idDivisionEdit;
    Textbox nameDivisionEdit;
    Button backButton;
    Button submitButton;
    Button clearButton;

    private final String TYPE_SHOW_VIEW = "VIEW";
    private final String TYPE_SHOW_EDIT = "EDIT";

    Division div = new Division();
    SendJSON send = new SendJSON();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this,true);
        getAllData();
        initView();
    }

    public void getAllData(){
        div.setId((Long) arg.get("id"));
        div.setDivisionCode((String) arg.get("divisionCode"));
        div.setDivisionName((String) arg.get("divisionName"));
    }

    public void initView() {
        idDivisionView.setValue(div.getDivisionCode());
        nameDivisionView.setValue(div.getDivisionName());
        idDivisionEdit.setValue(div.getDivisionCode());
        nameDivisionEdit.setValue(div.getDivisionName());
        disableComponent();
    }

    public void disableComponent() {
        if((String) arg.get("type") == TYPE_SHOW_VIEW){
            idDivisionEdit.setVisible(false);
            nameDivisionEdit.setVisible(false);
            submitButton.setVisible(false);
            clearButton.setVisible(false);
        }
        else if((String) arg.get("type") == TYPE_SHOW_EDIT){
            idDivisionView.setVisible(false);
            nameDivisionView.setVisible(false);
            idDivisionEdit.setDisabled(true);
            backButton.setVisible(false);
        }

    }

    public void onClick$backButton(){
        navigateTo("layout/Division/Division.zul",null,self);
    }

    public void onClick$submitButton(){
        div.setDivisionCode(idDivisionEdit.getValue());
        div.setDivisionName(nameDivisionEdit.getValue());
        try {
            String resultGrade = send.insertDivision(div);

            if(resultGrade.equals("200")){
                Messagebox.show("Data Already Updated", "Confirm Dialog", Messagebox.OK , Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
                    public void onEvent(Event evt) throws InterruptedException {
                        navigateTo("layout/Division/Division.zul",null,self);
                    }
                });
            }else if(!resultGrade.equals("200")){
                Messagebox.show("Data Failed To save to Table Grade");
            }

        } catch (JsonProcessingException e) {
            Messagebox.show("All data Failed To Save");
        }
    }

    public void onClick$clearButton(){
        nameDivisionEdit.setValue("");
    }

}
