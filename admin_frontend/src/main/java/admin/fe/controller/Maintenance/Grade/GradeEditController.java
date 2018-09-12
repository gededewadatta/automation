package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class GradeEditController extends CommonController {

    Textbox idDivision;

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idGradeName;

    Textbox idSubGradeName;

    Textbox idNum;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        initView();
    }

    public void initView(){

        idDivision.setValue((String) arg.get("divisionCode"));
        idDepartment.setValue((String) arg.get("departementCode"));
        idSubGrade.setValue((String)arg.get("subGradeCode"));
        idGrade.setValue((String)arg.get("gradeCode"));
        idGradeName.setValue((String)arg.get("gradeName"));
        idSubGradeName.setValue((String)arg.get("subGradeName"));
        idNum.setValue((String)arg.get("id"));

    }

    public void onClick$submitButton(){

        Grade grd = new Grade();
        SubGrade subGrd = new SubGrade();

        SendJSON send = new SendJSON();

        System.out.println("Ini Fucking Submit2");
        grd.setId(Long.valueOf(idNum.getValue()));
        grd.setGradeName(idGradeName.getValue());
        grd.setGradeCode(idGrade.getValue());
        grd.setDepartementCode(idDepartment.getValue());
        grd.setDivisionCode(idDivision.getValue());
        subGrd.setGradeCode(grd.getGradeCode());
        subGrd.setSubGradeCode(idSubGrade.getValue());
        subGrd.setSubGradeName(idSubGradeName.getValue());
        grd.setCreatedDate(new Date());
        grd.setCreatedBy("Burhan");
        subGrd.setCreatedDate(grd.getCreatedDate());
        subGrd.setCreatedBy(grd.getCreatedBy());

        try {
            String resultGrade = send.insertGrade(grd);

            if(resultGrade.equals("200")){
                Messagebox.show("Data Already Updated");
            }else if(!resultGrade.equals("200")){
                Messagebox.show("Data Failed To save to Table Grade");
            }

        } catch (JsonProcessingException e) {
            Messagebox.show("All data Failed To Save");
        }


    }

}
