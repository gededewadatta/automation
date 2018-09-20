package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerEmployeeInterface;
import admin.fe.engine.PopupCallerGradeInterface;
import admin.fe.engine.PopupCallerSubGradeInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

public class SubGradePopupController extends CommonController {

    List<SubGrade> subGrds = new ArrayList<>();

    SubGrade subgrd = new SubGrade();

    Grid hGrid;

    ListModelList modelList;

    Radiogroup rgrSearchResult;

    Window subGradePopup;

    SendJSON send = new SendJSON();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("SubGradePopupController",this, true);
        SubGrade subgrd = new SubGrade();

        if(subgrd.getSubGradeCode() == null){
            subgrd.setSubGradeCode("");
        }

        if (subgrd.getSubGradeName() == null){
            subgrd.setSubGradeName("");
        }

        subGrds = send.getSubGrade(subgrd);
        modelList = new ListModelList(subGrds);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(SubGrade) data);
            }

            private void renderDataRow(Row row, SubGrade subgrd){

                row.setValue(subgrd);
                Radio rdo = new Radio();
                rdo.setValue(subgrd.getId());
                rdo.setParent(row);
                new Label(subgrd.getSubGradeCode()).setParent(row);
                new Label(subgrd.getSubGradeName()).setParent(row);

            }
        };
    }

    public void onClick$idSelect(){

        if (rgrSearchResult.getSelectedIndex() != -1) {
            SubGrade grade = (SubGrade) arg.get("object");
            SubGrade subgradeData = (SubGrade) modelList.get(rgrSearchResult.getSelectedIndex());
            grade.setId(subgradeData.getId());
            grade.setSubGradeCode(subgradeData.getSubGradeCode());
            grade.setSubGradeName(subgradeData.getSubGradeName());
            grade.setGradeCode(subgradeData.getGradeCode());

            PopupCallerSubGradeInterface caller = (PopupCallerSubGradeInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectSubGrade(grade);

            subGradePopup.onClose();
        }

    }



}
