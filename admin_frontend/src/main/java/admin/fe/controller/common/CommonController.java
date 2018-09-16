package admin.fe.controller.common;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;

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

//    public static void showConfirmDialog(String message) {
//        try {
//            Messagebox.show(message, "confirm",
//                    Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
//                    Messagebox.NO, new SerializableEventListener() {
//
//                        private static final long serialVersionUID = -8695776168749565854L;
//
//                        @Override
//                        public void onEvent(Event event) throws Exception {
//                            int data = (Integer) event.getData();
//                            switch (data) {
//                                case Messagebox.YES:
//                                    Component parent = (Component) component
//                                            .getParent().getSpaceOwner();
//                                    BancaFinSimulationComposer composer = (BancaFinSimulationComposer) parent
//                                            .getAttribute("controller");
//                                    composer.getSimulations().remove(
//                                            winBancafinSimulationCreateDetail);
//                                    winBancafinSimulationCreateDetail
//                                            .setParent(null);
//                                    break;
//                            }
//                        }
//                    });
//        } catch (InterruptedException e) {
//            logger.error(e.getMessage(), e);
//        }
//    }


}
