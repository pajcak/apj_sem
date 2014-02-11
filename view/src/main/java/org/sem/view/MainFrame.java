package org.sem.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;
import org.sem.model.Game;
import org.sem.model.Server;
import org.sem.utils.Messages;
import org.sem.utils.MyAction;
import org.sem.utils.MyException;
import org.sem.view.impl.MainPanel;
import org.sem.view.impl.Refreshable;

/**
 *
 * @author Skarab
 */
public class MainFrame extends JFrame {

    private static MainFrame instance;
    private static Collection<Refreshable> refresh = new ArrayList<>(4);

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public static void addRefreshable(Refreshable r) {
        refresh.add(r);
    }
    private Collection<MyAction> actions = new ArrayList<>();
    private MainPanel mainPanel;
    private JMenuBar menuBar;
    private BundleContext context;

    public MainFrame() {
        super(Messages.Main_Frame.cm());
        menuBar = new JMenuBar();
        //menuBar.add(new JMenu(Messages.File.cm()));
        setJMenuBar(menuBar);

        mainPanel = new MainPanel();
        add(mainPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                exit();
            }
        });
        pack();
        setLocationRelativeTo(null);
    }

    public void exit() {
        try {
            Bundle bnd = context.getBundle(0);
            Framework fw = (Framework) bnd;
            fw.stop();
            fw.waitForStop(0);
        } catch (BundleException | InterruptedException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.INFO, null, ex);
        }
    }

    public void showError(Exception ex) {
        showError(ex.toString());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public Collection<Server> getSelectedServers() {
        return mainPanel.getSelectedServers();
    }

    public Collection<Game> getSelectedGames() {
        return mainPanel.getSelectedGames();
    }

    public void refresh() {
        for (Refreshable r : refresh) {
            try {
                r.refresh();
            } catch (MyException ex) {
                showError(ex);
            }
        }
        notifyActions();
    }

    public void addActionToMenu(MyAction libraryAction) {
        actions.add(libraryAction);
        JMenu mnu;
        JMenuBar mnuBar = getJMenuBar();
        for (int i = 0; i < mnuBar.getMenuCount(); i++) {
            mnu = mnuBar.getMenu(i);
            if (mnu.getText().equals(libraryAction.getMenuName())) {
                mnu.add((Action) libraryAction);
                return;
            }
        }
        mnu = new JMenu(libraryAction.getMenuName());
        mnuBar.add(mnu);
        mnu.add((Action) libraryAction);
    }

    public void notifyActions() {
        for (MyAction te : actions) {
            te.setEnabled();
        }
    }

    public void setContext(BundleContext context) {
        this.context = context;
    }
}
