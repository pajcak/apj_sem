package org.sem.controller.actions;

import java.awt.event.ActionEvent;
import java.util.Collection;
import org.sem.business.FacadeService;
import org.sem.controller.dialogs.DeleteGameDialog;
import org.sem.model.Game;
import org.sem.utils.Messages;
import org.sem.view.MainFrame;

/**
 *
 * @author Skarab
 */
public class DeleteGameAction extends MyAbstractAction {

    public DeleteGameAction() {
        super(Messages.Delete_Game.cm(), Messages.Game.cm());
    }

    @Override
    public void setEnabled() {
        setEnabled(FacadeService.getDefault().isAvailable()
                && !MainFrame.getInstance().getSelectedGames().isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Collection<Game> games = MainFrame.getInstance().getSelectedGames();
        if (games.isEmpty()) {
            MainFrame.getInstance().showError(Messages.No_selected_server.cm());
        } else {
            new DeleteGameDialog(games);
        }
    }
}
