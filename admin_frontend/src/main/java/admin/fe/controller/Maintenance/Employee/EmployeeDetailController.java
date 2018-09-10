package admin.fe.controller.Maintenance.Employee;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.ProxyEngine;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;

/*

Author Muhammad Burhanudin

 */

@Controller
public class EmployeeDetailController extends CommonController {

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

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

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




}
