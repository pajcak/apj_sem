package org.sem.business;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        ServiceTracker st = new ServiceTracker(context, FacadeService.class, null);
        st.open();
        FacadeService.setServiceTracker(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
