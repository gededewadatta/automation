package admin.fe.controller.Maintenance.Question;

import admin.fe.controller.common.CommonController;
import org.zkoss.zk.ui.Component;

public class QuestionSearchController extends CommonController {
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Question/Question.zul",null,self);

    }
}
