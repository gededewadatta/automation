package admin.fe.controller.popup;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.model.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author FikriAsandhita
 */

public class PopupShowController extends CommonController {

    public void showPopUpDivision(Object object){
        Map<String, Object> args = new HashMap<String, Object>();
        Division div = new Division();

        args.put("object", div);
        args.put("caller", object);
        Component c = Executions.createComponents(Resources.divisionPopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }
    }

    public void showPopUpDepartement(Object object,Division div){
        Map<String, Object> args = new HashMap<String, Object>();
        Departement dep = new Departement();

        args.put("object", dep);
        args.put("division", div);
        args.put("caller", object);
        Component c = Executions.createComponents(Resources.departementPopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }
    }

    public void showPopUpGrade(Object object,Departement dep){
        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();

        args.put("objectGrade", grade);
        args.put("departement", dep);
        args.put("caller", object);
        Component c = Executions.createComponents(Resources.gradePopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }
    }

    public void showPopUpSubGrade(Object object,Grade grd){
        Map<String, Object> args = new HashMap<String, Object>();
        SubGrade subgrd = new SubGrade();

        args.put("object", subgrd);
        args.put("grade",grd);
        args.put("caller", object);
        Component c = Executions.createComponents(Resources.subgradeopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }
    }

    public void showPopUpCompetencies(Object object,Grade grd,SubGrade subGrd){
        Map<String, Object> args = new HashMap<String, Object>();
        Competency competency = new Competency();

        args.put("grade",grd);
        args.put("subgrade",subGrd);
        args.put("object", competency);
        args.put("caller", object);
        Component c = Executions.createComponents(Resources.competenciesPopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }
    }

    public void showPopUpEmployee(Object object){

    }


}
