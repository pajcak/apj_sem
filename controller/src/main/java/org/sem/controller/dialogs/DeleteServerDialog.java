package org.sem.controller.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.sem.business.FacadeService;
import org.sem.integration.DAOFactoryService;
import org.sem.model.Game;
import org.sem.model.Server;
import org.sem.model.ServerId;
import org.sem.utils.Messages;
import org.sem.utils.MyException;
import org.sem.utils.Validator;
import org.sem.view.GameModel;
import org.sem.view.MainFrame;
import org.sem.view.ServerModel;

/**
 *
 * @author Skarab
 */
public class DeleteServerDialog extends MyAbstractDialog implements Validator {

    private Server server;
    private Collection<Game> games;

    public DeleteServerDialog(final Server server) {
        super(Messages.Delete_Server.cm());
        this.server = server;
        games = null;
        try {
            games = DAOFactoryService.getDefault().getGameDAO().findServer(server.getId());
        } catch (MyException ex) {
            Logger.getLogger(DeleteServerDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        getContent().setLayout(new BorderLayout());

        ArrayList<Server> s = new ArrayList<>(1);
        s.add(server);
        JTable tab = new JTable(new ServerModel(s));
        tab.setRowSelectionAllowed(false);
        tab.setPreferredScrollableViewportSize(new Dimension(300, 20));
        JScrollPane sp = new JScrollPane(tab);
        sp.setBorder(BorderFactory.createTitledBorder(Messages.Server.cm()));
        getContent().add(sp, BorderLayout.NORTH);

        tab = new JTable(new GameModel(games));
        tab.setRowSelectionAllowed(false);
        tab.setPreferredScrollableViewportSize(new Dimension(300, 100));
        sp = new JScrollPane(tab);
        sp.setBorder(BorderFactory.createTitledBorder(Messages.Games.cm()));
        getContent().add(sp, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    @Override
    public boolean validateDialog() {
        return true;
    }

    @Override
    public void okAction() {
        try {
            ArrayList<ServerId> serverIds = new ArrayList<>();
            serverIds.add(server.getId());
            FacadeService.getDefault().deleteServers(serverIds);
            MainFrame.getInstance().refresh();
        } catch (MyException ex) {
            MainFrame.getInstance().showError(ex);
        }
    }
}