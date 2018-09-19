package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Competency;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CompetenciesDetailController extends CommonController {



    Vbox boxList;

    List<Component> competenciesDetailListController = new ArrayList<Component>();

    private Window competenciesCont;
    private AbstractMainWindowTransaction parent;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        addTransaction();
    }

    public void onClick$addButton(){
        addTransaction();
    }

    private ArrayList<Map<String, Object>> retrieveDetailsArg() {
        ArrayList<Map<String, Object>> args = new ArrayList<Map<String, Object>>();
        CompetenciesDetailListController composer = new CompetenciesDetailListController();
        for (Component c : competenciesDetailListController) {
             composer = (CompetenciesDetailListController) c.getAttribute("controller");
            args.add(composer.getArgs());
        }
        return args;
    }


    public void onClick$submitButton(){

        showConfirmDialog("Do you want to save the data?");
    }

    private void addTransaction() {

        Component component = Executions.createComponents("layout/Competencies/CompetenciesDetailList.zul",
                boxList, arg);
        competenciesDetailListController.add(component);
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

                                SendJSON send = new SendJSON();

                                ArrayList<Map<String, Object>> competencyList = retrieveDetailsArg();


                                try {
                                    String result = "";
                                    for (Map<String, Object> cmpList : competencyList) {

                                        Competency cmp = (Competency) cmpList.get("Competencies");
                                        result = send.insertCompetency(cmp);


                                    }

                                    if(result.equals("200")){
                                        Messagebox.show("Data Already Saved");
                                        navigateTo("layout/Competencies/Competencies.zul",null,self);
                                    }

                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                }

                        }
                    }
                });
    }

}
