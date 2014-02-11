package org.sem.integration;

import org.osgi.util.tracker.ServiceTracker;
import org.sem.integration.impl.DAOFactoryDefault;

/**
 *
 * @author Skarab
 */
public abstract class DAOFactoryService {

    private static DAOFactoryService instance;
    private static ServiceTracker st;

    public static void setSt(ServiceTracker aSt) {
        st = aSt;
    }

    public static DAOFactoryService getDefault() {
        if (instance == null) {
            instance = (DAOFactoryService) st.getService();
            if (instance == null) {
                instance = new DAOFactoryDefault();
            }
        }
        return instance;
    }

    public abstract ServerDAO getServerDAO();

    public abstract GameDAO getGameDAO();

    public abstract void commit();

    public abstract void rollback();
}
