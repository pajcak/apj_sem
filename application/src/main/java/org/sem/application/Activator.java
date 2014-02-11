package org.sem.application;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.sem.business.FacadeService;
import org.sem.view.MainFrame;

public class Activator implements BundleActivator {

    @Override
    public void start(final BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        //context.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance().setContext(context);
                MainFrame.getInstance().setVisible(true);
                MainFrame.getInstance().notifyActions();
                if (FacadeService.getDefault().isAvailable()) {
                    MainFrame.getInstance().refresh();
                }
            }
        });
    }
    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
