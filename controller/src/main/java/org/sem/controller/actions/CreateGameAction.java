package org.sem.controller.actions;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sem.business.FacadeService;
import org.sem.controller.dialogs.CreateGameDialog;
import org.sem.utils.Messages;
import org.sem.utils.MyException;
import org.sem.view.MainFrame;

/**
 *
 * @author Skarab
 */
public class CreateGameAction extends MyAbstractAction {

    public CreateGameAction() {
        super(Messages.Create_Game.cm(), Messages.Game.cm());
    }

    @Override
    public void setEnabled() {
        try {
            setEnabled(FacadeService.getDefault().isAvailable()&&
                    !FacadeService.getDefault().getServers().isEmpty());
        } catch (MyException ex) {
            Logger.getLogger(CreateGameAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new CreateGameDialog();
    }
}
