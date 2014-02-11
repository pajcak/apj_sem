package org.sem.controller.actions;

import java.awt.event.ActionEvent;
import java.util.Collection;
import org.sem.business.FacadeService;
import org.sem.controller.dialogs.ChangeGameDialog;
import org.sem.model.Game;
import org.sem.utils.Messages;
import org.sem.view.MainFrame;

/**
 *
 * @author Skarab
 */
public class ChangeGameAction extends MyAbstractAction {

    public ChangeGameAction() {
        super(Messages.Change_Game.cm(), Messages.Game.cm());
    }

    @Override
    public void setEnabled() {
        setEnabled(FacadeService.getDefault().isAvailable()
                && MainFrame.getInstance().getSelectedGames().size() == 1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Collection<Game> games = MainFrame.getInstance().getSelectedGames();
        if (games.size() == 1) {
            new ChangeGameDialog(games.iterator().next());
        }
    }
}
