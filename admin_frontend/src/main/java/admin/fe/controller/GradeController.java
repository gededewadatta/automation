package admin.fe.controller;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;

public class GradeController extends CommonController {

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/GradeDetail.zul",null,self);
    }

}
