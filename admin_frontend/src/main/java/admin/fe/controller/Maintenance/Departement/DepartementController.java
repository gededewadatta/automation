package admin.fe.controller.Maintenance.Departement;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartementController extends CommonController {

    private Window departementWindow;

    protected Grid hGrid;
    protected ListModelList modelList;
    List<Departement> departementList;

    Textbox idCompanyName;
    Textbox idDivision;
    Textbox idDepartement;

    Departement dep = new Departement();
    SendJSON send = new SendJSON();


    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        idCompanyName.setValue("PT COBA COBA");
        comp.setAttribute("controller",this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Departement/DepartementDetail.zul",null,self);
    }

    public void onClick$searchButton(){

        dep.setDivisionCode(idDivision.getValue());
        dep.setDepartementCode(idDepartement.getValue());
        dep.setDepartementName("");

        departementList = send.getDepartment(dep);

        modelList = new ListModelList(departementList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Departement) data);
            }

            private void renderDataRow(Row row, Departement departement){

                row.setValue(departement);
                new Label(departement.getDivisionCode()).setParent(row);
                new Label(departement.getDepartementCode()).setParent(row);
                new Label(departement.getDepartementName()).setParent(row);

                Hbox hbox = new Hbox();

                Button bView = new Button("View");
                Button bEdit = new Button("Edit");

                bView.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Departement/DepartementViewEdit.zul",getArgs(departement,"VIEW"),departementWindow);
                                }
                            }
                        });

                bEdit.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Departement/DepartementViewEdit.zul",getArgs(departement,"EDIT"),departementWindow);
                                }
                            }
                        });

                Separator separator = new Separator("vertical");
                separator.setWidth("10px");

                hbox.appendChild(bView);
                separator.setParent(hbox);
                hbox.appendChild(bEdit);
                row.appendChild(hbox);

            }
        };
    }

    public Map<String, Object> getArgs(Departement obj, String type) {

        Map<String, Object> args = new HashMap<String, Object>();

        args.put("id",obj.getId());
        args.put("divisionCode",obj.getDivisionCode());
        args.put("departmentCode",obj.getDepartementCode());
        args.put("departmentName",obj.getDepartementName());
        args.put("type",type);

        return args;
    }
}
//test