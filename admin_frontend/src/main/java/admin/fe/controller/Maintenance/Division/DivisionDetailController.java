package admin.fe.controller.Maintenance.Division;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Division;
import admin.fe.model.Grade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class DivisionDetailController extends CommonController {

    @Wire
    Textbox idDivision;

    @Wire
    Textbox nameDivision;

    @Value("${led.Division.insert}")
    protected String divisionInsert;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

        Division dvs = new Division();

        SendJSON send = new SendJSON();

        System.out.println("Ini Fucking Submit2");
        dvs.setDivisionCode(idDivision.getValue());
        dvs.setDivisionName(nameDivision.getValue());
        dvs.setCreatedDate(new Date());
        dvs.setCreatedBy("test");

        try {
            String result = send.insertDivision(dvs);

            if(result.equals("200")){
                Messagebox.show("Data Already Saved");
            }else{
                Messagebox.show("Data Failed To save");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
//test
}
