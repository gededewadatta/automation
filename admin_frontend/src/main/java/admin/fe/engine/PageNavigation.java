package admin.fe.engine;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

/*

Author Muhammad Burhanudin

 */

@Component
public class PageNavigation {

    public Tabs tabs;
    public Tabbox tabbox;
    public Tabpanels tabpanels;
    public Tabpanel tabpanel;
    public Tab tab;

    public void setParameter(Tabs tabs, Tabbox tabbox, Tabpanels tabpanels, Tabpanel tabpanel, Tab tab){

        this.tabs =tabs;
        this.tabbox = tabbox;
        this.tabpanels = tabpanels;
        this.tabpanel = tabpanel;
        this.tab =  tab;
    }
}
