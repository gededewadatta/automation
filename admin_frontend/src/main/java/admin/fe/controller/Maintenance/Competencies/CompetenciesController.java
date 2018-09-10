package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Competency;
import admin.fe.model.Employee;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

public class CompetenciesController extends CommonController {

    Textbox idDepartment;
    Textbox idGrade;
    Textbox idSubGrade;

    protected Grid hGrid;
    protected ListModelList modelList;

    List<Competency> competencies = new ArrayList<>();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/CompetenciesDetail.zul",null,self);
    }

    public void onClick$searchButton(){

        Competency comp = new Competency();
        SendJSON send = new SendJSON();

        comp.setGradeCode(idGrade.getValue());

        competencies = send.getCompetency(comp);

        modelList = new ListModelList(competencies);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());


    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Competency)data);
            }

            private void renderDataRow(Row row, Competency competency){

                row.setValue(competency);
                new Label(competency.getDepartementCode()).setParent(row);
                new Label(competency.getGradeCode()).setParent(row);
                new Label("").setParent(row);
                new Label(competency.getCompetencyName()).setParent(row);

            }
        };
    }

}
