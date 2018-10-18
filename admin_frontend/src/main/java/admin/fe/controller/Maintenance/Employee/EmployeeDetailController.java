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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
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

    private Textbox idDivision;

    private Textbox idDepartment;

    private Textbox idGrade;

    private Textbox idSubGrade;

    private Textbox idCEmployeeId;

    private Textbox idEmployeeName;

    private Textbox idUserName;

    @Value("${led.Employee.insert}")
    protected String employeeInsert;

    Division div = new Division();

    Departement dep = new Departement();

    Grade grd = new Grade();

    SubGrade subGrd = new SubGrade();
    EventListener event = null;

    Employee emp = new Employee();

    SendJSON send = new SendJSON();


    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){


            System.out.println("Ini Fucking Submit2");
            emp.setDepartementCode(idDepartment.getValue());
            emp.setGradeCode(idGrade.getValue());
            emp.setDivisionCode(idDivision.getValue());
            emp.setSubGradeCode(idSubGrade.getValue());
            emp.setEmployeeCode(idCEmployeeId.getValue());
            emp.setEmployeeName(idEmployeeName.getValue());
            emp.setUserName(idUserName.getValue());
            emp.setCreatedDate(new Date());
            emp.setCreatedBy("Admin");

        Messagebox.show("Are you sure want to save?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event = new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {
                        String result = send.insertEmployee(emp);

                        if(result.equals("200")){
                            Messagebox.show("Data Already Saved", "Information", Messagebox.OK , Messagebox.INFORMATION, event = new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Employee/Employee.zul",null,self);
                                }
                            });
                        }else if(result.equals("Failure")){
                            Messagebox.show("Data Already exists","Information", Messagebox.OK , Messagebox.INFORMATION, event = new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Employee/Employee.zul",null,self);
                                }
                            });

                        }else{
                            Messagebox.show("Data Failed to Save","Error",Messagebox.OK, Messagebox.ERROR,event = new EventListener(){
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo("layout/Employee/Employee.zul",null,self);
                                }
                            });
                        }
                    } catch (JsonProcessingException e) {
                        Messagebox.show("Data Failed to Save","Error",Messagebox.OK, Messagebox.ERROR,event = new EventListener(){
                            public void onEvent(Event evt) throws InterruptedException {
                                navigateTo("layout/Employee/Employee.zul",null,self);
                            }
                        });
                    }
                }
            }
        });

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
        args.put("object", grade);
        args.put("departement", dep);
        args.put("division", div);
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
        args.put("grade",grd);
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

    public void onClick$cancelButton(){
        navigateTo("layout/Employee/EmployeeDetail.zul",null,self);
    }
}
