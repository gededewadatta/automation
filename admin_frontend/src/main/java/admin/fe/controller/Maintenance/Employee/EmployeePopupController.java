package admin.fe.controller.Maintenance.Employee;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerEmployeeInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import admin.fe.model.Employee;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeePopupController extends CommonController {

    Window empPopup;
    List<Employee> employeeList =  new ArrayList<>();

    SendJSON send = new SendJSON();

    Grid hGrid;

    ListModelList modelList;

    Radiogroup rgrSearchResult;

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("EmployeePopupController",this, true);
        Employee emp = new Employee();

        if(emp.getEmployeeCode() == null){
            emp.setEmployeeCode("");
        }

        if (emp.getEmployeeName() == null){
            emp.setEmployeeName("");
        }

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
                renderDataRow(row,(Employee) data);
            }

            private void renderDataRow(Row row, Employee employee){


                row.setValue(employee);
                Radio rdo = new Radio();
                rdo.setValue(employee.getId());
                rdo.setParent(row);
                new Label(employee.getEmployeeCode()).setParent(row);
                new Label(employee.getEmployeeName()).setParent(row);

            }
        };
    }

    public void onClick$idSelect(){

        if (rgrSearchResult.getSelectedIndex() != -1) {
            Employee employee = (Employee) arg.get("object");
            Employee employeeData = (Employee) modelList.get(rgrSearchResult.getSelectedIndex());
            employee.setId(employeeData.getId());
            employee.setDivisionCode(employeeData.getDivisionCode());
            employee.setDepartementCode(employeeData.getDepartementCode());
            employee.setEmployeeName(employeeData.getEmployeeName());
            employee.setEmployeeCode(employeeData.getEmployeeCode());

            PopupCallerEmployeeInterface caller = (PopupCallerEmployeeInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectEmployee(employee);

            empPopup.onClose();
        }

    }
}
