package org.sem.controller.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.sem.business.FacadeService;
import org.sem.model.Game;
import org.sem.model.GameId;
import org.sem.utils.Messages;
import org.sem.utils.MyException;
import org.sem.utils.Validator;
import org.sem.view.GameModel;
import org.sem.view.MainFrame;

/**
 *
 * @author Skarab
 */
public class DeleteGameDialog extends MyAbstractDialog implements Validator {

    private Collection<Game> games;

    public DeleteGameDialog(final Collection<Game> games) {
        super(Messages.Delete_Game.cm());
        this.games = games;
        getContent().setLayout(new BorderLayout());
        JTable tab = new JTable(new GameModel(games));
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
            ArrayList<GameId> gameIds = new ArrayList<>();
            for (Game game : games) {
                gameIds.add(game.getId());
            }
            FacadeService.getDefault().deleteGames(gameIds);
            MainFrame.getInstance().refresh();
        } catch (MyException ex) {
            MainFrame.getInstance().showError(ex);
        }
    }
}