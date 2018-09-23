package admin.fe.controller.Maintenance.Division;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import admin.fe.model.Employee;
import admin.fe.model.Grade;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivisionController extends CommonController implements PopupCallerDivisionInterface {

    private Window divisionWindow;

    Division div = new Division();

    protected Grid hGrid;
    protected ListModelList modelList;
    List<Division> divisionList;

    Textbox idCompanyName;
    Textbox idDivision;

    SendJSON send = new SendJSON();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Division/DivisionDetail.zul",null,self);

    }

    public void onClick$btnSearchDivision(){
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

    public void onClick$searchButton() throws Exception {

        if(div.getDivisionCode() == null || div.getDivisionCode().equals("")){
            div.setDivisionCode("");
        }

        if(div.getDivisionName() == null|| div.getDivisionName().equals("")){
            div.setDivisionName("");
        }

        divisionList = send.getDivision(div);

        modelList = new ListModelList(divisionList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Division) data);
            }

            private void renderDataRow(Row row, Division division){

                row.setValue(division);
                new Label(division.getDivisionCode()).setParent(row);
                new Label(division.getDivisionName()).setParent(row);

                Hbox hbox = new Hbox();

                Button bView = new Button("View");
                Button bEdit = new Button("Edit");

                bView.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Division/DivisionViewEdit.zul",getArgs(division,"VIEW"),divisionWindow);
                                }
                            }
                        });

                bEdit.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Division/DivisionViewEdit.zul",getArgs(division,"EDIT"),divisionWindow);
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

    public Map<String, Object> getArgs(Division obj, String type) {

        Map<String, Object> args = new HashMap<String, Object>();

        args.put("id",obj.getId());
        args.put("divisionCode",obj.getDivisionCode());
        args.put("divisionName",obj.getDivisionName());
        args.put("createdBy",obj.getCreatedBy());
        args.put("type",type);

        return args;
    }

    @Override
    public void afterSelectDivision(Division division) {

        if(division != null){
            div = division;
            idDivision.setValue(division.getDivisionName());
        }

    }

}
//test
