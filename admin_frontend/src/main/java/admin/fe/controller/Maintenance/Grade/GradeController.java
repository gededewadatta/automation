package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Dashboard;
import admin.fe.model.Grade;
import org.apache.poi.sl.usermodel.TextBox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.List;

public class GradeController extends CommonController {

    protected Grid hGrid;
    protected ListModelList modelList;
    List<Grade> gradeList;

    Textbox idCompanyName;
    Textbox idDivision;
    Textbox idDepartment;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/GradeDetail.zul",null,self);

    }

    public void onClick$searchButton(){

        Grade grd = new Grade();
        SendJSON send = new SendJSON();

        grd.setDepartementCode(idDepartment.getValue());
        grd.setDivisionCode(idDivision.getValue());

        gradeList = send.getGrade(grd);

        modelList = new ListModelList(gradeList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());


    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Grade)data);
            }

            private void renderDataRow(Row row, Grade grade){

                row.setValue(grade);
                new Label(grade.getDivisionCode()).setParent(row);
                new Label(grade.getDepartementCode()).setParent(row);
                new Label(grade.getGradeCode()).setParent(row);
                new Label(grade.getGradeName()).setParent(row);

            }
        };
    }

}
