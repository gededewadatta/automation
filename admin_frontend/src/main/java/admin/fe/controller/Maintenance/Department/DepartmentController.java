package admin.fe.controller.Maintenance.Department;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Department;
import admin.fe.model.Division;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.List;

public class DepartmentController extends CommonController {

    protected Grid hGrid;
    protected ListModelList modelList;
    List<Department> departmentList;

    Textbox idCompanyName;
    Textbox idDivision;
    Textbox idDepartment;


    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Department/DepartmentDetail.zul",null,self);
    }

    public void onClick$searchButton(){

        Department dpr = new Department();
        SendJSON send = new SendJSON();

        dpr.setDivisionCode(idDivision.getValue());
        dpr.setDepartementCode(idDepartment.getValue());
        departmentList = send.getDepartment(dpr);

        modelList = new ListModelList(departmentList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Department) data);
            }

            private void renderDataRow(Row row, Department department){

                row.setValue(department);
                new Label(department.getDivisionCode()).setParent(row);
                new Label(department.getDepartementCode()).setParent(row);
                new Label(department.getDepartmentName()).setParent(row);
                new Label("").setParent(row);
            }
        };
    }
}
//test