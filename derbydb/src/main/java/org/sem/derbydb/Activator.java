package org.sem.derbydb;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.sem.derbydb.impl.DerbyDAOFactory;
import org.sem.integration.DAOFactoryService;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        context.registerService(DAOFactoryService.class.getName(), new DerbyDAOFactory(context), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
