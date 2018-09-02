package admin.fe.controller;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.engine.PageNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

/*

Author Muhammad Burhanudin

 */

@Controller
public class EmployeeController extends CommonController {

    private Window employeeCont;
    private AbstractMainWindowTransaction parent;


    @Autowired
    PageNavigation pageNavigation;

    @Wire
    Button addButton;


    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

//    @Command
//    public void addTabOne(@BindingParam("pProgr") String pProgr) {
//
//        navigateTo(pProgr+".zul",null,getSelf());
//
//    }

    public void onClick$btnSubmit(){
        System.out.println("Ini button Submit");
        navigateTo("layout/EmployeeDetail.zul",null,self);
    }
}
