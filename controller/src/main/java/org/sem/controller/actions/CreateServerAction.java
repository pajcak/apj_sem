package org.sem.controller.actions;

import java.awt.event.ActionEvent;
import org.sem.business.FacadeService;
import org.sem.controller.dialogs.CreateServerDialog;
import org.sem.utils.Messages;

/**
 *
 * @author Skarab
 */
public class CreateServerAction extends MyAbstractAction {

    public CreateServerAction() {
        super(Messages.Create_Server.cm(), Messages.Server.cm());
    }

    @Override
    public void setEnabled() {
        setEnabled(FacadeService.getDefault().isAvailable());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new CreateServerDialog();
    }
}
