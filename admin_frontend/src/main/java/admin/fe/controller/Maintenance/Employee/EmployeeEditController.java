package admin.fe.controller.Maintenance.Employee;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class EmployeeEditController extends CommonController {

    private Textbox idDivision;

    private Textbox idDepartment;

    private Textbox idGrade;

    private Textbox idSubGrade;

    private Textbox idCEmployee;

    private Textbox idEmployeeName;

    private Textbox idEmployeeId;

    private Textbox idUserName;


    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        initView();
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

                                Employee emp = new Employee();

                                SendJSON send = new SendJSON();

                                emp.setId(idEmployeeId.getValue());
                                emp.setDepartementCode(idDepartment.getValue());
                                emp.setGradeCode(idGrade.getValue());
                                emp.setDivisionCode(idDivision.getValue());
                                emp.setSubGradeCode(idSubGrade.getValue());
                                emp.setEmployeeCode(idCEmployee.getValue());
                                emp.setEmployeeName(idEmployeeName.getValue());
                                emp.setUserName(idUserName.getValue());
                                emp.setCreatedDate(new Date());
                                emp.setCreatedBy("ADMIN");

                                try {
                                    String result = send.updateEmployee(emp);

                                    if(result.equals("200")){
                                        Messagebox.show("Data Already Updated");
                                        navigateTo("layout/Employee/Employee.zul",null,self);
                                    }else{
                                        Messagebox.show("Data Failed To save");
                                        idDepartment.setText("");
                                        idGrade.setText("");
                                        idDivision.setText("");
                                        idSubGrade.setText("");
                                        idCEmployee.setText("");
                                        idEmployeeName.setText("");
                                    }

                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                }

                        }
                    }
                });
    }

    public void initView(){

        idEmployeeId.setValue((String) arg.get("id"));
        idDivision.setValue((String) arg.get("divisionCode"));
        idDepartment.setValue((String) arg.get("departementCode"));
        idSubGrade.setValue((String)arg.get("subGradeCode"));
        idGrade.setValue((String)arg.get("gradeCode"));
        idCEmployee.setValue((String)arg.get("employeeCode"));
        idEmployeeName.setValue((String)arg.get("employeeName"));
        idUserName.setValue((String)arg.get("userName"));

    }

    public void onClick$submitButton(){
        showConfirmDialog("Do you want to update Data");
    }

    public void onClick$clearButton(){
        idDivision.setValue("");
        idDepartment.setValue("");
        idGrade.setValue("");
        idSubGrade.setValue("");
        idCEmployee.setValue("");
        idEmployeeName.setValue("");
        idEmployeeId.setValue("");
        idUserName.setValue("");
    }

}
