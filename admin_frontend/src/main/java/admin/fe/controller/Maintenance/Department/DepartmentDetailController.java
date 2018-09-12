package admin.fe.controller.Maintenance.Department;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Department;
import admin.fe.model.Division;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class DepartmentDetailController extends CommonController {

    @Wire
    Textbox idDivision;

    @Wire
    Textbox idDepartment;

    @Wire
    Textbox nameDepartment;

    @Value("${led.Department.insert}")
    protected String departmentInsert;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

        Department dpr = new Department();

        SendJSON send = new SendJSON();

        System.out.println("Ini Fucking Submit2");
        dpr.setDivisionCode(idDivision.getValue());
        dpr.setDepartementCode(idDepartment.getValue());
        dpr.setDepartmentName(nameDepartment.getValue());
        dpr.setCreatedDate(new Date());
        dpr.setCreatedBy("test");

        try {
            String result = send.insertDepartment(dpr);

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
