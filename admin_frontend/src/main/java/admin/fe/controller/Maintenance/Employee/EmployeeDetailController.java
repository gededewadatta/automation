package admin.fe.controller.Maintenance.Employee;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.*;
import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*

Author Muhammad Burhanudin

 */

@Controller
public class EmployeeDetailController extends CommonController implements PopupCallerDepartmentInterface,PopupCallerDivisionInterface,PopupCallerGradeInterface,PopupCallerSubGradeInterface {

    @Autowired
    ServletRegistrationBean dHtmlLayoutServlet;

    private final Object HttpServletRequest = dHtmlLayoutServlet;
    @Wire
    private Textbox idDivision;

    @Wire
    private Textbox idDepartment;

    @Wire
    private Textbox idGrade;

    @Wire
    private Textbox idSubGrade;

    @Wire
    private Textbox idCEmployeeId;

    @Wire
    private Textbox idEmployeeName;

    @Value("${led.Employee.insert}")
    protected String employeeInsert;

    Division div = new Division();

    Departement dep = new Departement();

    Grade grd = new Grade();

    SubGrade subGrd = new SubGrade();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        Employee emp = new Employee();

        SendJSON send = new SendJSON();

            System.out.println("Ini Fucking Submit2");
            emp.setDepartementCode(idDepartment.getValue());
            emp.setGradeCode(idGrade.getValue());
            emp.setDivisionCode(idDivision.getValue());
            emp.setSubGradeCode(idSubGrade.getValue());
            emp.setEmployeeCode(idCEmployeeId.getValue());
            emp.setEmployeeName(idEmployeeName.getValue());
            emp.setCreatedDate(new Date());
            emp.setCreatedBy("Burhan");

        try {
          String result = send.insertEmployee(emp);

          if(result.equals("200")){
              Messagebox.show("Data Already Saved");
          }else{
              Messagebox.show("Data Failed To save");
              idDepartment.setText("");
              idGrade.setText("");
              idDivision.setText("");
              idSubGrade.setText("");
              idCEmployeeId.setText("");
              idEmployeeName.setText("");
          }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void onClick$btnDepartment(){

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
        args.put("division", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Departement/DeptPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }
    public void onClick$btnDivision(){

        Map<String, Object> args = new HashMap<String, Object>();
        Division div = new Division();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Division/DivisionPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void onClick$btnGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();
        args.put("objectGrade", grade);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Grade/GradePopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }
    public void onClick$btnSubGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        SubGrade div = new SubGrade();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Grade/SubGradePopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void onClick$clearButton(){
        idDivision.setValue("");
        idDepartment.setValue("");
        idGrade.setValue("");
        idSubGrade.setValue("");
        idCEmployeeId.setValue("");
        idEmployeeName.setValue("");
    }

    @Override
    public void afterSelectDivision(Division division) {

        if(division != null){
            div = division;
            idDivision.setValue(division.getDivisionCode());
        }

    }

    @Override
    public void afterSelectDepartement(Departement departement) {
        if(departement != null){
            dep = departement;
            idDepartment.setValue(departement.getDepartementCode());
        }
    }

    @Override
    public void afterSelectGrade(Grade grade) {
        if(grade != null){
            grd = grade;
            idGrade.setValue(grd.getGradeCode());
        }

    }

    @Override
    public void afterSelectSubGrade(SubGrade subGrade) {

        if(subGrade != null){
            subGrd = subGrade;
            idSubGrade.setValue(subGrd.getSubGradeCode());
        }
    }
}
