package org.sem.view.impl;

import java.awt.BorderLayout;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.sem.model.Server;
import org.sem.utils.Messages;
import org.sem.view.MainFrame;
import org.sem.view.ServerModel;

/**
 *
 * @author Skarab
 */
public class ServerSelectionPanel extends JPanel {

    private JTable tbl;
    private ServerModel model;

    public ServerSelectionPanel() {
        super();
        setBorder(BorderFactory.createTitledBorder(Messages.Servers.cm()));
        setLayout(new BorderLayout());
        model = new ServerModel();
        tbl = new JTable(model);
        add(new JScrollPane(tbl), BorderLayout.CENTER);
        tbl.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = tbl.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                MainFrame.getInstance().notifyActions();
            }
        });
    }

    public Collection<Server> getSelectedServers() {
        int[] sr = tbl.getSelectedRows();
        return model.getServers(sr);
    }
}
