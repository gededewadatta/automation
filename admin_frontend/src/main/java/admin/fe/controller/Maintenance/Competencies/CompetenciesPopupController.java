package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerCompetencyInterface;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Competency;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import admin.fe.model.SubGrade;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.List;

public class CompetenciesPopupController extends CommonController {

    List<Competency> competencyList;
    Textbox idCompetencyCode;
    Textbox idCompetencyName;
    SendJSON send = new SendJSON();
    ListModelList modelList;
    Grid hGrid;
    Window compPopup;
    Radiogroup rgrSearchResult;

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

        Competency competency = new Competency();

        competency.setCompetencyCode(idCompetencyCode.getValue());

        competency.setCompetencyName(idCompetencyName.getValue());

        competencyList = send.getCompetency(competency);
        modelList = new ListModelList(competencyList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());

    }


    public void onClick$cancelButton(){
        compPopup.onClose();
    }




}
