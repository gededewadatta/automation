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

public class DepartementPopupController extends CommonController {

    ListModelList modelList;
    Grid hGrid;
    Window depPopup;
    Radiogroup rgrSearchResult;
    Textbox idDepartment;
    Textbox idDepartmentName;

    Departement dep = new Departement();
    List<Departement> departementList;
    SendJSON send = new SendJSON();

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

        if(rgrSearchResult.getSelectedItem()!=null){
            Long id = rgrSearchResult.getSelectedItem().getValue();
            Departement departement = (Departement) arg.get("object");
            List<Departement> depList = modelList;
            for (Departement depCheck :  depList){
                if(id == depCheck.getId()){
                    departement.setId(depCheck.getId());
                    departement.setDivisionCode(depCheck.getDivisionCode());
                    departement.setDepartementCode(depCheck.getDepartementCode());
                    departement.setDepartementName(depCheck.getDepartementName());
                }
            }

            PopupCallerDepartmentInterface caller = (PopupCallerDepartmentInterface)arg.get("caller");

            if(caller != null)
                caller.afterSelectDepartement(departement);

            depPopup.onClose();
        }
    }

    public void onClick$searchButton() throws Exception {
        Division division = (Division) arg.get("division");

        if(division!=null&&division.getDivisionCode()!=null){
            dep.setDivisionCode(division.getDivisionCode());
        } else {
            dep.setDivisionCode("");
        }

        if(idDepartment != null || !(idDepartment.equals(""))){
            dep.setDepartementCode(idDepartment.getValue());
        } else {
            dep.setDepartementCode("");
        }

        if(idDepartmentName != null|| !(idDepartmentName.equals(""))){
            dep.setDepartementName(idDepartmentName.getValue());
        } else {
            dep.setDepartementName("");
        }

        departementList = send.getDepartmentPopUp(dep);

        if(departementList.size()<1){
            Messagebox.show("Data is not found");
        }

        modelList = new ListModelList(departementList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    public void onClick$cancelButton(){
        depPopup.onClose();
    }


}
