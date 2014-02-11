package org.sem.view.impl;

import java.awt.FlowLayout;
import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.sem.model.Game;
import org.sem.model.Server;

/**
 *
 * @author Skarab
 */
public class MainPanel extends JPanel {

    ServerSelectionPanel sp;
    GameSelectionPanel gp;
    
    public MainPanel() {
        super();
        setLayout(new FlowLayout());
        sp = new ServerSelectionPanel();
        add(new JScrollPane(sp));
        gp = new GameSelectionPanel();
        add(new JScrollPane(gp));
    }

    public Collection<Server> getSelectedServers() {
        return sp.getSelectedServers();
    }

    public Collection<Game> getSelectedGames() {
        return gp.getSelectedGames();
    }
}
