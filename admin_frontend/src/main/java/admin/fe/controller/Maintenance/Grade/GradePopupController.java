package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerEmployeeInterface;
import admin.fe.engine.PopupCallerGradeInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

public class GradePopupController extends CommonController {

    List<Grade> gradeList = new ArrayList<>();

    SendJSON send = new SendJSON();

    Grade grd = new Grade();

    Grid hGrid;

    ListModelList modelList;

    Radiogroup rgrSearchResult;

    Window gradePopup;

    Textbox idDepartmentCode;

    Textbox idDepartementName;

    Grade grade = new Grade();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("GradePopupController",this, true);
        grade = (Grade) arg.get("objectGrade");
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Grade) data);
            }

            private void renderDataRow(Row row, Grade grd){


                row.setValue(grd);
                Radio rdo = new Radio();
                rdo.setValue(grd.getIdGrade());
                rdo.setParent(row);
                new Label(grd.getGradeCode()).setParent(row);
                new Label(grd.getGradeName()).setParent(row);

            }
        };
    }

    public void onClick$idSelect(){

        if (rgrSearchResult.getSelectedIndex() != -1) {

            Grade gradeData = (Grade) modelList.get(rgrSearchResult.getSelectedIndex());
            grade.setIdGrade(gradeData.getIdGrade());
            grade.setDivisionCode(gradeData.getDivisionCode());
            grade.setDepartementCode(gradeData.getDepartementCode());
            grade.setGradeCode(gradeData.getGradeCode());
            grade.setGradeName(gradeData.getGradeName());

            PopupCallerGradeInterface caller = (PopupCallerGradeInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectGrade(grade);

            gradePopup.onClose();
        }

    }

    public void onClick$searchButton() throws Exception {

        idDepartmentCode.setValue(grade.getDepartementCode());
        grd.setDepartementCode(idDepartmentCode.getValue());

        grd.setDivisionCode("");

        gradeList = send.getGrade(grd);
        modelList = new ListModelList(gradeList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());

    }


    public void onClick$cancelButton(){
        gradePopup.onClose();
    }
}
