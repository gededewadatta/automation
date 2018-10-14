package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
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

    Textbox idGradeId;

    Textbox idSubGradeId;

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
        idGradeId.setValue(String.valueOf(arg.get("idGrade")));
        idSubGradeId.setValue(String.valueOf(arg.get("idSubGrade")));

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

                                Grade grd = new Grade();
                                SubGrade subGrd = new SubGrade();

                                SendJSON send = new SendJSON();

                                grd.setIdGrade(Long.valueOf(idGradeId.getValue()));
                                grd.setGradeName(idGradeName.getValue());
                                grd.setGradeCode(idGrade.getValue());
                                grd.setDepartementCode(idDepartment.getValue());
                                grd.setDivisionCode(idDivision.getValue());
                                subGrd.setId(Long.valueOf(idSubGradeId.getValue()));
                                subGrd.setGradeCode(idGrade.getValue());
                                subGrd.setSubGradeCode(idSubGrade.getValue());
                                subGrd.setSubGradeName(idSubGradeName.getValue());
                                subGrd.setDepartementCode(idDepartment.getValue());
                                grd.setCreatedDate(new Date());
                                grd.setCreatedBy("Burhan");
                                subGrd.setCreatedDate(grd.getCreatedDate());
                                subGrd.setCreatedBy(grd.getCreatedBy());

                                try {
                                    String resultGrade = send.updateGrade(grd);
                                    String resultSubGrade = send.updateSubGrade(subGrd);

                                    if(resultGrade.equals("200") && resultSubGrade.equals("200")){
                                        Messagebox.show("Data Already Updated");
                                        navigateTo("layout/Grade/Grade.zul",null,self);
                                    }else{
                                        Messagebox.show("Data Failed To save ");
                                        navigateTo("layout/Grade/Grade.zul",null,self);
                                    }

                                } catch (JsonProcessingException e) {
                                    Messagebox.show("All data Failed To Save");
                                    navigateTo("layout/Grade/Grade.zul",null,self);
                                }

                        }
                    }
                });
    }

    public void onClick$clearButton(){

        idDivision.setValue("");
        idGradeName.setValue("");
        idGrade.setValue("");
        idDepartment.setValue("");
        idSubGrade.setValue("");
        idSubGradeName.setValue("");

    }

}
