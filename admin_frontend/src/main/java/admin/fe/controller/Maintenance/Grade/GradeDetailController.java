package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class GradeDetailController extends CommonController {

    @Wire
    Textbox idDivision;

    @Wire
    Textbox idDepartment;

    @Wire
    Textbox idGrade;

    @Wire
    Textbox idSubGrade;

    @Value("${led.Grade.insert}")
    protected String gradeInsert;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

        Grade grd = new Grade();

        SendJSON send = new SendJSON();

        System.out.println("Ini Fucking Submit2");
        grd.setGradeName(idGrade.getValue());
        grd.setGradeCode(idSubGrade.getValue());
        grd.setDepartementCode(idDepartment.getValue());
        grd.setDivisionCode(idDivision.getValue());
        grd.setCreatedDate(new Date());
        grd.setCreatedBy("Burhan");

        try {
            String result = send.insertGrade(grd);

            if(result.equals("200")){
                Messagebox.show("Data Already Saved");
            }else{
                Messagebox.show("Data Failed To save");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
