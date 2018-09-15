package admin.fe.controller.Maintenance.Division;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Division;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivisionController extends CommonController {

    private Window divisionWindow;

    protected Grid hGrid;
    protected ListModelList modelList;
    List<Division> divisionList;

    Textbox idCompanyName;
    Textbox idDivision;

    Division div = new Division();
    SendJSON send = new SendJSON();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        idCompanyName.setValue("PT COBA COBA");
        comp.setAttribute("controller",this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Division/DivisionDetail.zul",null,self);

    }

    public void onClick$searchButton(){

        div.setDivisionCode(idDivision.getValue());

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

        args.put("divisionCode",obj.getDivisionCode());
        args.put("divisionName",obj.getDivisionName());
        args.put("type",type);

        return args;
    }
}
//test
