package admin.fe.controller.Maintenance.Departement;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.List;

public class DepartementPupupController extends CommonController {

    List<Departement> departementList;
    SendJSON send = new SendJSON();
    ListModelList modelList;
    Grid hGrid;
    Window depPopup;
    Radiogroup rgrSearchResult;
    Textbox idDepartment;
    Textbox idDepartmentName;
    Departement dep = new Departement();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("DepartementPupupController",this, true);

    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Departement) data);
            }

            private void renderDataRow(Row row, Departement departement){


                row.setValue(departement);
                Radio rdo = new Radio();
                rdo.setValue(departement.getId());
                rdo.setParent(row);
                new Label(departement.getDepartementCode()).setParent(row);
                new Label(departement.getDepartementName()).setParent(row);

            }
        };
    }

    public void onClick$addSelect(){

        if (rgrSearchResult.getSelectedIndex() != -1) {
            Departement departement = (Departement) arg.get("object");
            Departement departementData = (Departement) modelList.get(rgrSearchResult.getSelectedIndex());
            departement.setId(departementData.getId());
            departement.setDivisionCode(departementData.getDivisionCode());
            departement.setDepartementCode(departementData.getDepartementCode());
            departement.setDepartementName(departementData.getDepartementName());

            PopupCallerDepartmentInterface caller = (PopupCallerDepartmentInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectDepartement(departement);

            depPopup.onClose();
        }

    }

    public void onClick$searchButton() throws Exception {
        Division division = (Division) arg.get("division");

            dep.setDepartementCode(idDepartment.getValue());

            dep.setDepartementName(idDepartmentName.getValue());

            dep.setDivisionCode(division.getDivisionCode());

        departementList = send.getDepartment(dep);

        modelList = new ListModelList(departementList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    public void onClick$cancelButton(){
        depPopup.onClose();
    }


}
