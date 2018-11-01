package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerGradeInterface;
import admin.fe.engine.PopupCallerSubGradeInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.*;
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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CompetenciesDetailController extends CommonController {

    private Window competenciesCont;
    private AbstractMainWindowTransaction parent;
    Vbox boxList;
    Textbox idDepartment;
    Textbox idGrade;
    Textbox idSubGrade;
    Textbox idCompetencies;

    List<Component> competenciesDetailListController = new ArrayList<Component>();
    Division div = new Division();
    Departement dep = new Departement();
    Grade grd = new Grade();
    SubGrade subGrd = new SubGrade();

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

    public void onClick$deleteButton(){
        deleteTransaction();
    }

    public void onClick$submitButton(){
        showConfirmDialog("Do you want to save the data?");
    }

    private void deleteTransaction(){
        int size = competenciesDetailListController.size();
        if(size>1){
            boxList.removeChild(competenciesDetailListController.get(size - 1));
            competenciesDetailListController.remove(size - 1);
        }
    }

    private void addTransaction() {
        System.out.println("TOTAL SIZE : "+competenciesDetailListController.size());
        Component component = Executions.createComponents(Resources.competenciesDetailList,
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
                                    List<Competency> comp = new ArrayList<>();

                                    for (Map<String, Object> cmpList : competencyList) {
                                        Competency cmp = (Competency) cmpList.get("Competencies");

                                        result = send.insertCompetency(cmp);
                                        if(result.equals("200")){
                                            comp.add(cmp);
                                        }
                                    }

                                    if(comp.size() > 0){
                                        Messagebox.show("Data Already Saved");
                                        navigateTo(Resources.competenciesHome,null,self);
                                    }else{
                                        Messagebox.show("Data Already Exists");
                                        navigateTo(Resources.competenciesHome,null,self);
                                    }

                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                }
                        }
                    }
                });
    }

    public void onClick$cancelButton(){
        navigateTo(Resources.competenciesHome,null,self);
    }
}
