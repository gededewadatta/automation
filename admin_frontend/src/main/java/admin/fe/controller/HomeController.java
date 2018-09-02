package admin.fe.controller;

import admin.fe.engine.PageNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

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
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        
    }

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

            Tab newTab = new Tab();
            newTab.setParent(tabs);
            newTab.setLabel(pProgr);
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
