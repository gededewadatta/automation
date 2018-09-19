package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Competency;
import admin.fe.model.Employee;
import admin.fe.model.GradeJson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        navigateTo("layout/Competencies/CompetenciesDetail.zul",null,self);
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
                new Label(competency.getSubGradeCode()).setParent(row);
                new Label(competency.getCompetencyName()).setParent(row);

                Hbox hbox1 = new Hbox();

                Button view = new Button("View");
                view.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            /**
                             *
                             */
                            private static final long serialVersionUID = 2726307190666012319L;

                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Competencies/CompetenciesView.zul",getArg(competency),self);
//                                            getArg(InvestmentModelObj),
//                                            winBancaFinTransactionSelection);
                                }
                            }
                        });
                hbox1.appendChild(view);

                Separator separator = new Separator("vertical");
                separator.setWidth("10px");
                separator.setParent(hbox1);

                Button edit = new Button("Edit");
                edit.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            /**
                             *
                             */
                            private static final long serialVersionUID = 5680895575895846548L;

                            public void onEvent(Event event)
                                    throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Competencies/CompetenciesEdit.zul",getArg(competency),self);
                                }
                            }
                        });
                hbox1.appendChild(edit);
                row.appendChild(hbox1);
            }
        };
    }

    public Map<String, Object> getArg(Competency obj) {

        Map<String, Object> args = new HashMap<String, Object>();

        args.put("id",obj.getId());
        args.put("subGradeCode",obj.getSubGradeCode());
        args.put("competencyName",obj.getCompetencyName());
        args.put("gradeCode",obj.getGradeCode());
        args.put("departmentCode",obj.getDepartementCode());
        return args;
    }

}
