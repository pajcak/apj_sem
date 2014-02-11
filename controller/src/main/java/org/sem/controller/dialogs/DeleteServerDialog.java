package org.sem.controller.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.sem.business.FacadeService;
import org.sem.model.Server;
import org.sem.model.ServerId;
import org.sem.utils.Messages;
import org.sem.utils.MyException;
import org.sem.utils.Validator;
import org.sem.view.MainFrame;
import org.sem.view.ServerModel;

/**
 *
 * @author Skarab
 */
public class DeleteServerDialog extends MyAbstractDialog implements Validator {

    private Collection<Server> servers;

    public DeleteServerDialog(final Collection<Server> servers) {
        super(Messages.Delete_Server.cm());
        this.servers = servers;
        getContent().setLayout(new BorderLayout());
        JTable tab = new JTable(new ServerModel(servers));
        tab.setRowSelectionAllowed(false);
        tab.setPreferredScrollableViewportSize(new Dimension(300, 100));
        JScrollPane sp = new JScrollPane(tab);
        getContent().add(sp);
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
            for (Server server : servers) {
                serverIds.add(server.getId());
            }
            FacadeService.getDefault().deleteServers(serverIds);
            MainFrame.getInstance().refresh();
        } catch (MyException ex) {
            MainFrame.getInstance().showError(ex);
        }
    }
}