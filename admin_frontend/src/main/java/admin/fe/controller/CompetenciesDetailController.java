package admin.fe.controller;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Competency;
import admin.fe.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class CompetenciesDetailController extends CommonController {

    @Wire
    Textbox idDepartment;

    @Wire
    Textbox idGrade;

    @Wire
    Textbox idSubGrade;

    @Wire
    Textbox idCompetencies;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

        Competency cmp = new Competency();

        SendJSON send = new SendJSON();
        cmp.setDepartementCode(idDepartment.getValue());
        cmp.setGradeCode(idGrade.getValue());
        cmp.setCompetencyCode("null");
        cmp.setCompetencyName(idCompetencies.getValue());
        cmp.setCreatedBy("Burhan");
        cmp.setCreatedDate(new Date());


        try {
            String result = send.insertCompetency(cmp);

            if(result.equals("200")){
                Messagebox.show("Data Already Saved");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
