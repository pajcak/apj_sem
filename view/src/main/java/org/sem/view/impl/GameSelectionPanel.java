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
import org.sem.model.Game;
import org.sem.utils.Messages;
import org.sem.view.GameModel;
import org.sem.view.MainFrame;

/**
 *
 * @author Skarab
 */
public class GameSelectionPanel extends JPanel {

    private JTable tbl;
    private GameModel model;

    public GameSelectionPanel() {
        super();
        setBorder(BorderFactory.createTitledBorder(Messages.Games.cm()));
        setLayout(new BorderLayout());
        model = new GameModel();
        tbl = new JTable(model);
        add(new JScrollPane(tbl), BorderLayout.CENTER);
        tbl.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ListSelectionModel selectionModel = tbl.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                MainFrame.getInstance().notifyActions();
            }
        });
    }

    public Collection<Game> getSelectedGames() {
        int[] sr = tbl.getSelectedRows();
        return model.getGames(sr);
    }
}
