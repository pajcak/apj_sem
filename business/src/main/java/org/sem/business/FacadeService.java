package org.sem.business;

import org.osgi.util.tracker.ServiceTracker;
import org.sem.business.impl.FacadeDefault;

/**
 *
 * @author Skarab
 */
public abstract class FacadeService implements FacadeInterface{
    
    private static ServiceTracker st;
    private static FacadeService libraryFacade;

    public static FacadeService getDefault() {
        if (libraryFacade == null) {
            libraryFacade = (FacadeService) st.getService();
            if (libraryFacade == null) {
                libraryFacade = new FacadeDefault();
            }
        }
        return libraryFacade;
    }

    public static void setServiceTracker(ServiceTracker aSt) {
        st = aSt;
    }
}
