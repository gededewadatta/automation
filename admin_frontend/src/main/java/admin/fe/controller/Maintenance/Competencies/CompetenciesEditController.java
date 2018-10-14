package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Competency;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CompetenciesEditController  extends CommonController {

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idCompetencies;

    Textbox idCompetenciesId;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        initView();
    }

    public void initView(){

        idCompetenciesId.setValue((String)arg.get("id"));
        idDepartment.setValue((String) arg.get("departmentCode"));
        idSubGrade.setValue((String)arg.get("subGradeCode"));
        idGrade.setValue((String)arg.get("gradeCode"));
        idCompetencies.setValue((String)arg.get("competencyName"));

    }

    public void onClick$submitButton(){

        showConfirmDialog("Do you want Update Data");

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


                                try {
                                    String result = "";

                                    Competency cmp = new Competency();

                                    cmp.setId(idCompetenciesId.getValue());
                                    cmp.setSubGradeCode(idSubGrade.getValue());
                                    cmp.setDepartementCode(idDepartment.getValue());
                                    cmp.setGradeCode(idGrade.getValue());
                                    cmp.setCompetencyName(idCompetencies.getValue());

                                    result = send.insertCompetency(cmp);

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

    public void onClick$clearButton(){

        idCompetenciesId.setValue("");
        idDepartment.setValue("");
        idSubGrade.setValue("");
        idGrade.setValue("");
        idCompetencies.setValue("");

    }

}
