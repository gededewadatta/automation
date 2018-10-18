package admin.fe.controller.Maintenance.Employee;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.engine.*;
import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EmployeeEditController extends CommonController implements PopupCallerDepartmentInterface, PopupCallerDivisionInterface, PopupCallerGradeInterface,
        PopupCallerSubGradeInterface {

    private Textbox idDivision;

    private Textbox idDepartment;

    private Textbox idGrade;

    private Textbox idSubGrade;

    private Textbox idCEmployee;

    private Textbox idEmployeeName;

    private Textbox idEmployeeId;

    private Textbox idUserName;

    Division div = new Division();

    Departement dep = new Departement();

    Grade grd = new Grade();

    SubGrade subGrd = new SubGrade();


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
                                        navigateTo(Resources.employeeHome,null,self);
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

    public void onClick$cancelButton(){
        navigateTo(Resources.employeeHome,null,self);
    }

    public void onClick$btnDepartment() {

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
        args.put("division", div);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.departementPopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void onClick$btnDivision() {

        Map<String, Object> args = new HashMap<String, Object>();
        Division div = new Division();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.divisionPopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void onClick$btnGrade() {

        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();
        grade.setDepartementCode(idDepartment.getValue());
        grade.setDivisionCode(idDivision.getValue());
        args.put("objectGrade", grade);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.gradePopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void onClick$btnSubGrade() {

        Map<String, Object> args = new HashMap<String, Object>();
        SubGrade div = new SubGrade();
        div.setSubGradeCode(idGrade.getValue());
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.subgradeopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void afterSelectDivision(Division division) {

        if (division != null) {
            div = division;
            idDivision.setValue(division.getDivisionCode());
        }

    }

    public void afterSelectDepartement(Departement departement) {
        if (departement != null) {
            dep = departement;
            idDepartment.setValue(departement.getDepartementCode());
        }
    }

    public void afterSelectGrade(Grade grade) {
        if (grade != null) {
            grd = grade;
            idGrade.setValue(grd.getGradeCode());
        }

    }

    public void afterSelectSubGrade(SubGrade subGrade) {

        if (subGrade != null) {
            subGrd = subGrade;
            idSubGrade.setValue(subGrd.getSubGradeCode());
        }
    }

}
