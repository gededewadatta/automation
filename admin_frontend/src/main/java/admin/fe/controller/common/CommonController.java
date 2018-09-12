package admin.fe.controller.common;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import java.util.Map;

public class CommonController extends GenericForwardComposer {

    protected void navigateTo(String url, Map<String, Object> arg,
                              Component from) {
        Component parent = from.getParent();
        Executions.createComponents(url, parent, arg);
        from.detach();
    }

    protected void backTo(Component to, Component from) {
        to.setParent(from.getParent());
        from.detach();
    }
}
