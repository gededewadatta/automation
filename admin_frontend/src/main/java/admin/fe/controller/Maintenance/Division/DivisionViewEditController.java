package admin.fe.controller.Maintenance.Division;

/**
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.engine.SendJSON;
import admin.fe.model.Division;
import admin.fe.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    EventListener event = null;
    List<Textbox> textboxes = new ArrayList<>();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this,true);
        getAllData();
        initView();
    }

    public void getAllData() {
        div.setId((Long) arg.get("id"));
        div.setDivisionCode((String) arg.get("divisionCode"));
        div.setDivisionName((String) arg.get("divisionName"));
        div.setCreatedBy((String) arg.get("createdBy"));
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
            backButton.setLabel("Back");
        }
        else if((String) arg.get("type") == TYPE_SHOW_EDIT){
            idDivisionView.setVisible(false);
            nameDivisionView.setVisible(false);
            idDivisionEdit.setDisabled(true);
            backButton.setLabel("Cancel");
        }
    }


    public void onClick$backButton(){
        navigateTo(Resources.divisionHome,null,self);
    }

    public void onClick$submitButton(){
        div.setDivisionCode(idDivisionEdit.getValue());
        div.setDivisionName(nameDivisionEdit.getValue());
        div.setCreatedDate(new Date());

        Messagebox.show("Are you sure want to Update?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event = new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {

                        textboxes.add(idDivisionEdit);
                        textboxes.add(nameDivisionEdit);

                        if(FileUtil.validateRegex(textboxes) == 0){
                            String resultGrade = send.updateDivision(div);

                            if (resultGrade.equals("200")) {
                                Messagebox.show("Data Already Updated", "Information", Messagebox.OK, Messagebox.INFORMATION, event = new EventListener() {
                                    public void onEvent(Event evt) throws InterruptedException {
                                        navigateTo(Resources.divisionHome, null, self);
                                    }
                                });
                            } else if (!resultGrade.equals("200")) {
                                Messagebox.show("Data Failed To update to Table Division" , "Information", Messagebox.OK, Messagebox.INFORMATION, event = new EventListener(){
                                    public void onEvent(Event evt) throws InterruptedException {
                                        navigateTo(Resources.divisionHome, null, self);
                                    }
                                });
                            }
                        }else{

                            Messagebox.show("there is an illegal character or has a white space");
                        }


                    } catch (JsonProcessingException e) {
                        Messagebox.show("All data Failed To Update");
                    }
                }
            }
        });
    }

    public void onClick$clearButton(){
        nameDivisionEdit.setValue("");
    }

}
