package admin.fe.controller.Maintenance.Division;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Division;
import admin.fe.model.Grade;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.List;

public class DivisionController extends CommonController {

    protected Grid hGrid;
    protected ListModelList modelList;
    List<Division> divisionList;

    Textbox idCompanyName;
    Textbox idDivision;

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Division/DivisionDetail.zul",null,self);

    }

    public void onClick$searchButton(){

        Division dvs = new Division();
        SendJSON send = new SendJSON();

        dvs.setDivisionCode(idDivision.getValue());

        divisionList = send.getDivision(dvs);

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
            }
        };
    }
}
//test
