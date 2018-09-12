package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.*;
import org.apache.poi.sl.usermodel.TextBox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeController extends CommonController {

    private Window gradeCont;
    private AbstractMainWindowTransaction parent;

    protected Grid hGrid;
    protected ListModelList modelList;
    List<GradeJson> gradeList;

    Textbox idCompanyName;
    Textbox idDivision;
    Textbox idDepartment;
    SendJSON send = new SendJSON();
    GradeJson grd = new GradeJson();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Grade/GradeDetail.zul",null,self);

    }

    public void onClick$searchButton(){




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
                renderDataRow(row,(GradeJson)data);
            }

            private void renderDataRow(Row row, GradeJson gradeJson){

                row.setValue(gradeJson);
                new Label(gradeJson.getDivisionCode()).setParent(row);
                new Label(gradeJson.getDepartementCode()).setParent(row);
                new Label(gradeJson.getGradeCode()).setParent(row);
                new Label(gradeJson.getSubGradeCode()).setParent(row);

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
                                        navigateTo("layout/Grade/GradeView.zul",getArg(gradeJson),gradeCont);
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
                                    navigateTo("",null,null);
                                }
                            }
                        });
                hbox1.appendChild(edit);
                row.appendChild(hbox1);
            }
        };
    }

    public Map<String, Object> getArg(GradeJson obj) {

        Map<String, Object> args = new HashMap<String, Object>();

        args.put("divisionCode",obj.getDivisionCode());
        args.put("departementCode",obj.getDepartementCode());
        args.put("gradeCode",obj.getGradeCode());
        args.put("subGradeCode",obj.getSubGradeCode());
        args.put("gradeName",obj.getGradeName());
        args.put("subGradeName",obj.getSubGradeName());

        return args;
    }

}
