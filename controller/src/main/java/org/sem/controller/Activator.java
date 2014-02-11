package org.sem.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.sem.controller.actions.ChangeGameAction;
import org.sem.controller.actions.CreateGameAction;
import org.sem.controller.actions.CreateServerAction;
import org.sem.controller.actions.DeleteGameAction;
import org.sem.controller.actions.DeleteServerAction;
import org.sem.view.MainFrame;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        MainFrame.getInstance().addActionToMenu(new CreateServerAction());
        MainFrame.getInstance().addActionToMenu(new DeleteServerAction());
        MainFrame.getInstance().addActionToMenu(new CreateGameAction());
        MainFrame.getInstance().addActionToMenu(new ChangeGameAction());
        MainFrame.getInstance().addActionToMenu(new DeleteGameAction());
    }

    public void stop(BundleContext context) throws Exception {
    }
}
