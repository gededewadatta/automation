package admin.fe.controller;

import admin.fe.engine.PageNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.Iterator;

/*

Author Muhammad Burhanudin

 */

@org.springframework.stereotype.Component
public class HomeController extends SelectorComposer<Component>{

    @Autowired
    PageNavigation pageNavigation;

	@Wire
    Tabs tabs;
    @Wire
    Tabbox tabbox;
    @Wire
    Tabpanels tabpanels;
    @Wire
    Tabpanel tabpanel;
    @Wire
    Toolbarbutton toolbarbutton;
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
        super.doAfterCompose(view);
        Selectors.wireComponents(view, this, false);
        view.setAttribute("controller", this, true);
    }

    //dummy add

    @Command
    public void addTabOne(@BindingParam("pProgr") String pProgr){
        Boolean tabPresente=false;
        Iterator<Component> it;
        Tab chkTab;

        for (it = tabs.getChildren().iterator(); it.hasNext();)
        {
            chkTab = (Tab) it.next();

            if(chkTab.getId().equalsIgnoreCase(pProgr))
            {
                chkTab.setSelected(true);
                tabPresente=true;
            }
        }

        if(!tabPresente)
        {

            String label = "";
            label = pProgr.substring(pProgr.lastIndexOf("/")+1,pProgr.length());
            int index2 = pProgr.lastIndexOf("/");
            int index1 = label.lastIndexOf("/");
            if(label.lastIndexOf("/") > 0){
                label = label.substring(label.lastIndexOf("/")+1,label.length());
            }
            Tab newTab = new Tab();
            newTab.setParent(tabs);
            newTab.setLabel(label);
            newTab.setClosable(true);
            newTab.setId(pProgr);

            Tabpanel newTabpanel = new Tabpanel();
            newTabpanel.setParent(tabpanels);

            Include inc = new Include();
            inc.setSrc(pProgr+".zul");
            inc.setParent(newTabpanel);
            newTabpanel.focus();

        }

    }

}
