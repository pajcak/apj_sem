package org.sem.controller.actions;

import java.awt.event.ActionEvent;
import java.util.Collection;
import org.sem.business.FacadeService;
import org.sem.controller.dialogs.DeleteServerDialog;
import org.sem.model.Server;
import org.sem.utils.Messages;
import org.sem.view.MainFrame;

/**
 *
 * @author Skarab
 */
public class DeleteServerAction extends MyAbstractAction {

    public DeleteServerAction() {
        super(Messages.Delete_Server.cm(), Messages.Server.cm());
    }

    @Override
    public void setEnabled() {
        setEnabled(FacadeService.getDefault().isAvailable()
                && !MainFrame.getInstance().getSelectedServers().isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Collection<Server> servers = MainFrame.getInstance().getSelectedServers();
        if (servers.isEmpty()) {
            MainFrame.getInstance().showError(Messages.No_selected_server.cm());
        } else {
            new DeleteServerDialog(servers);
        }
    }
}
