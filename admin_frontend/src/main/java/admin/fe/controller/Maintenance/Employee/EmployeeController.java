package admin.fe.controller.Maintenance.Employee;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PageNavigation;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.List;

/*

Author Muhammad Burhanudin

 */

@Controller
public class EmployeeController extends CommonController {

    private Window employeeCont;
    private AbstractMainWindowTransaction parent;

    protected Grid hGrid;
    protected ListModelList modelList;

    Textbox idDivision;
    Textbox idDepartment;
    Textbox idEmployee;
    List<Employee> employeeList;


    @Autowired
    PageNavigation pageNavigation;

    @Wire
    Button addButton;


    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

//    @Command
//    public void addTabOne(@BindingParam("pProgr") String pProgr) {
//
//        navigateTo(pProgr+".zul",null,getSelf());
//
//    }

    public void onClick$btnSubmit(){
        System.out.println("Ini button Submit2");
        navigateTo("layout/Employee/EmployeeDetail.zul",null,self);
    }

    public void onClick$searchButton(){

        Employee emp = new Employee();
        SendJSON send = new SendJSON();

        emp.setEmployeeCode(idEmployee.getValue());
        emp.setEmployeeName("");

        employeeList = send.getEmployee(emp);

        modelList = new ListModelList(employeeList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());


    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Employee)data);
            }

            private void renderDataRow(Row row, Employee employee){

                row.setValue(employee);
                new Label(employee.getDivisionCode()).setParent(row);
                new Label(employee.getDepartementCode()).setParent(row);
                new Label(employee.getEmployeeCode()).setParent(row);
                new Label(employee.getEmployeeName()).setParent(row);

                Hbox hbox1 = new Hbox();

                Button view = new Button("View");
                view.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            /**
                             *
                             */
                            private static final long serialVersionUID = 2726307190666012319L;

                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("",null,null);
//                                            getArg(InvestmentModelObj),
//                                            winBancaFinTransactionSelection);
                                }
                            }
                        });
                hbox1.appendChild(view);

                Separator separator = new Separator("vertical");
                separator.setWidth("10px");
                separator.setParent(hbox1);

                Button edit = new Button("Edit");
                edit.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            /**
                             *
                             */
                            private static final long serialVersionUID = 5680895575895846548L;

                            public void onEvent(Event event)
                                    throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("",null,null);
                                }
                            }
                        });
                hbox1.appendChild(edit);
                row.appendChild(hbox1);


            }
        };
    }
}