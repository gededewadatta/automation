package admin.fe.controller.Maintenance.Departement;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartementController extends CommonController implements PopupCallerDivisionInterface,PopupCallerDepartmentInterface {

    private Window departementWindow;

    protected Grid hGrid;
    protected ListModelList modelList;
    List<Departement> departementList;

    Textbox idCompanyName;
    Textbox idDivision;
    Textbox idDepartement;


    Division div = new Division();
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

        if(div.getDivisionCode() == null||div.getDivisionCode().equals("")){
            dep.setDivisionCode("");
        }else{
            dep.setDivisionCode(div.getDivisionCode());
        }

        if(dep.getDepartementCode() == null||dep.getDepartementCode().equals("")){
            dep.setDepartementCode("");
        }

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

    public void onClick$btnDepartement(){

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
        args.put("division", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Departement/DeptPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }
    public void onClick$btnDivision(){

        Map<String, Object> args = new HashMap<String, Object>();
        Division div = new Division();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Division/DivisionPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }


    @Override
    public void afterSelectDivision(Division division) {

        if(division != null){
            div = division;
            idDivision.setValue(division.getDivisionName());
        }

    }

    @Override
    public void afterSelectDepartement(Departement departement) {
        if(departement != null){
            dep = departement;
            idDepartement.setValue(departement.getDepartementName());
        }
    }
}
//test