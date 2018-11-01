package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerCompetencyInterface;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.List;

public class CompetenciesPopupController extends CommonController {

    Textbox idCompetencyCode;
    Textbox idCompetencyName;
    ListModelList modelList;
    Grid hGrid;
    Window compPopup;
    Radiogroup rgrSearchResult;

    List<Competency> competencyList;
    SendJSON send = new SendJSON();
    Competency competency = new Competency();


    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("DepartementPopupController",this, true);
        Division division = (Division) arg.get("division");
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Competency) data);
            }

            private void renderDataRow(Row row, Competency competency){
                row.setValue(competency);
                Radio rdo = new Radio();
                rdo.setValue(competency.getId());
                rdo.setParent(row);
                new Label(competency.getCompetencyCode()).setParent(row);
                new Label(competency.getCompetencyName()).setParent(row);
            }
        };
    }

    public void onClick$addSelect(){
        if(rgrSearchResult.getSelectedItem()!=null){
            String id = rgrSearchResult.getSelectedItem().getValue();
            Competency competency = (Competency) arg.get("object");
            List<Competency> compList = modelList;
            for (Competency competencyData :  compList){
                if(id.equalsIgnoreCase(competencyData.getId())){
                    competency.setId(competencyData.getId());
                    competency.setCompetencyName(competencyData.getCompetencyName());
                    competency.setCompetencyCode(competencyData.getCompetencyCode());
                }
            }

            PopupCallerCompetencyInterface caller = (PopupCallerCompetencyInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectCompetencies(competency);

            compPopup.onClose();
        }
    }

    public void onClick$searchButton() throws Exception {
        Grade grade = (Grade) arg.get("grade");
        SubGrade subGrade = (SubGrade) arg.get("subgrade");

        if(grade!=null&&grade.getGradeCode()!=null){
            competency.setGradeCode(grade.getGradeCode());
            competency.setDepartementCode(grade.getDepartementCode());
        }else{
            competency.setGradeCode("");
            competency.setDepartementCode("");
        }
        if(subGrade!=null&&subGrade.getSubGradeCode()!=null){
            competency.setSubGradeCode(subGrade.getSubGradeCode());
            if(competency.getGradeCode().equals("")){
                competency.setGradeCode(subGrade.getGradeCode());
                competency.setDepartementCode(subGrade.getDepartementCode());
            }
        }else {
            competency.setSubGradeCode("");
        }

        if(idCompetencyCode != null || !(idCompetencyCode.equals(""))){
            competency.setCompetencyCode(idCompetencyCode.getValue());
        }else {
            competency.setCompetencyCode("");
        }

        if(idCompetencyName != null || !(idCompetencyName.equals(""))){
            competency.setCompetencyName(idCompetencyName.getValue());
        }else {
            competency.setCompetencyName("");
        }

        competencyList = send.getCompetencyPopup(competency);

        if(competencyList.size()<1){
            Messagebox.show("Data is not found");
        }

        modelList = new ListModelList(competencyList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }


    public void onClick$cancelButton(){
        compPopup.onClose();
    }
}
