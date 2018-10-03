package admin.fe.controller.Maintenance.Division;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Division;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.List;

public class DivisionPopupController extends CommonController {

    ListModelList modelList;
    Grid hGrid;
    Window divPopup;
    Radiogroup rgrSearchResult;
    Textbox idDivision;
    Textbox nameDivision;

    Division div = new Division();
    List<Division> divisionList;
    SendJSON send = new SendJSON();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("DivisionPopupController",this, true);
    }

    protected SerializableRowRenderer createGridRowRenderer(){
        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Division) data);
            }

            private void renderDataRow(Row row, Division division){
                row.setValue(division);
                Radio rdo = new Radio();
                rdo.setValue(division.getId());
                rdo.setParent(row);
                new Label(division.getDivisionCode()).setParent(row);
                new Label(division.getDivisionName()).setParent(row);
            }
        };
    }

    public void onClick$addSelect(){
        Long id = rgrSearchResult.getSelectedItem().getValue();
        Division division = (Division) arg.get("object");
        List<Division> divList = modelList;
        for (Division divCheck :  divList){
            if(id == divCheck.getId()){
                division.setId(divCheck.getId());
                division.setDivisionCode(divCheck.getDivisionCode());
                division.setDivisionName(divCheck.getDivisionName());
            }
        }

        PopupCallerDivisionInterface caller = (PopupCallerDivisionInterface)arg.get("caller");

        if(caller != null)
            caller.afterSelectDivision(division);

        divPopup.onClose();
    }

    public void onClick$searchButton() throws Exception {
        if(idDivision != null || !(idDivision.equals(""))){
            div.setDivisionCode(idDivision.getValue());
        } else {
            div.setDivisionCode("");
        }

        if(nameDivision != null|| !(nameDivision.equals(""))){
            div.setDivisionName(nameDivision.getValue());
        } else {
            div.setDivisionName("");
        }

        divisionList = send.getDivision(div);

        modelList = new ListModelList(divisionList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    public void onClick$cancelButton(){
        divPopup.onClose();
    }

}
