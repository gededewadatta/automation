package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerEmployeeInterface;
import admin.fe.engine.PopupCallerGradeInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
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
    Textbox idGradeCode;
    Textbox idGradeName;

    Grade grade = new Grade();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("GradePopupController",this, true);
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

        if(rgrSearchResult.getSelectedItem()!=null){
            Long id = rgrSearchResult.getSelectedItem().getValue();
            List<Grade> gradeList = modelList;
            for(Grade gradeData: gradeList){
                if(id == gradeData.getIdGrade()){
                    grade.setIdGrade(gradeData.getIdGrade());
                    grade.setDivisionCode(gradeData.getDivisionCode());
                    grade.setDepartementCode(gradeData.getDepartementCode());
                    grade.setGradeCode(gradeData.getGradeCode());
                    grade.setGradeName(gradeData.getGradeName());
                }
            }

            PopupCallerGradeInterface caller = (PopupCallerGradeInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectGrade(grade);

            gradePopup.onClose();
        }
    }

    public void onClick$searchButton() throws Exception {
        Division division = (Division) arg.get("division");
        Departement departement = (Departement) arg.get("departement");

        if(division.getDivisionCode()!=null){
            grd.setDivisionCode(division.getDivisionCode());
        } else {
            grd.setDivisionCode("");
        }

        if(departement.getDepartementCode()!=null){
            grd.setDepartementCode(departement.getDepartementCode());
        } else {
            grd.setDepartementCode("");
        }

        if(idGradeCode != null || !(idGradeCode.equals(""))){
            grd.setGradeCode(idGradeCode.getValue());
        } else {
            grd.setGradeCode("");
        }

        if(idGradeName != null|| !(idGradeName.equals(""))){
            grd.setGradeName(idGradeName.getValue());
        } else {
            grd.setGradeName("");
        }

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
