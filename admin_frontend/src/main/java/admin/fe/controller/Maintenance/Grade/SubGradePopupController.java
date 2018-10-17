package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerSubGradeInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Grade;
import admin.fe.model.SubGrade;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

public class SubGradePopupController extends CommonController {

    List<SubGrade> subGradeList = new ArrayList<>();
    SubGrade subgrd = new SubGrade();

    Grid hGrid;
    ListModelList modelList;
    Radiogroup rgrSearchResult;
    Window subGradePopup;

    SendJSON send = new SendJSON();

    Textbox idSubGradeCode;
    Textbox idSubGradeName;

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("SubGradePopupController",this, true);
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

        if(rgrSearchResult.getSelectedItem()!=null){
            Long id = rgrSearchResult.getSelectedItem().getValue();
            SubGrade grade = (SubGrade) arg.get("object");
            List<SubGrade> subGrdList = modelList;
            for (SubGrade subgradeData :  subGrdList){
                if(id == subgradeData.getId()){
                    grade.setId(subgradeData.getId());
                    grade.setSubGradeCode(subgradeData.getSubGradeCode());
                    grade.setSubGradeName(subgradeData.getSubGradeName());
                    grade.setGradeCode(subgradeData.getGradeCode());
                }
            }

            PopupCallerSubGradeInterface caller = (PopupCallerSubGradeInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectSubGrade(grade);

            subGradePopup.onClose();
        }
    }

    public void onClick$searchButton() throws Exception {
        Grade grade = (Grade) arg.get("grade");

        if(grade!=null&&grade.getGradeCode()!=null){
            subgrd.setGradeCode(grade.getGradeCode());
        } else {
            subgrd.setGradeCode("");
        }

        if(idSubGradeCode!=null||idSubGradeCode.equals("")){
            subgrd.setSubGradeCode(idSubGradeCode.getValue());
        } else {
            subgrd.setSubGradeCode("");
        }

        if(idSubGradeName!=null ||idSubGradeName.equals("")){
            subgrd.setSubGradeName(idSubGradeName.getValue());
        } else {
            subgrd.setSubGradeName("");
        }

        subGradeList = send.getSubGradePopUp(subgrd);

        if(subGradeList.size()<1){
            Messagebox.show("Data is not found");
        }

        modelList = new ListModelList(subGradeList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());

    }


    public void onClick$cancelButton(){
        subGradePopup.onClose();
    }



}
