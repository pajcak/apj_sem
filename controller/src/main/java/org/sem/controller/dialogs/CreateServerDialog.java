package org.sem.controller.dialogs;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import org.sem.business.FacadeService;
import org.sem.utils.GBCBuilder;
import org.sem.utils.Messages;
import org.sem.utils.MyException;
import org.sem.utils.ValidatedTF;
import org.sem.utils.Validator;
import org.sem.view.MainFrame;

/**
 *
 * @author Skarab
 */
public class CreateServerDialog extends MyAbstractDialog implements Validator {

    private ValidatedTF name;
    private ValidatedTF address;
    private ValidatedTF socket;

    public CreateServerDialog() {
        super(Messages.Create_Server.cm());
        name = new ValidatedTF(this);
        address = new ValidatedTF(this);
        socket = new ValidatedTF(this);
        getContent().setLayout(new GridBagLayout());
        getContent().add(new JLabel(Messages.Name.cm() + ": "), new GBCBuilder().build());
        getContent().add(name, new GBCBuilder().setXRel().build());
        getContent().add(new JLabel(Messages.Address.cm() + ": "), new GBCBuilder().setY(1).build());
        getContent().add(address, new GBCBuilder().setY(1).setXRel().build());
        getContent().add(new JLabel(Messages.Socket.cm() + ": "), new GBCBuilder().setY(2).build());
        getContent().add(socket, new GBCBuilder().setY(2).setXRel().build());
        pack();
        validateDialog();
        setVisible(true);
    }

    @Override
    public final boolean validateDialog() {
        blockOk();
        if (name.getText().isEmpty()) {
            error(Messages.Empty_name.cm());
            return false;
        } else if (address.getText().isEmpty()) {
            error(Messages.Empty_address.cm());
            return false;
        } else if (socket.getText().isEmpty()) {
            error(Messages.Empty_socket.cm());
            return false;
        } else {
            try {
                Integer.parseInt(socket.getText());
            } catch (Exception ex) {
                error(Messages.Bad_format_socket.cm());
                return false;
            }
        }
        clearError();
        return true;
    }

    @Override
    public void okAction() {
        try {
            if (!validateDialog()) {
                throw new MyException("Input data are not valid");
            }
            FacadeService.getDefault().createServer(
                    name.getText(),
                    address.getText(),
                    Integer.parseInt(socket.getText()));
            MainFrame.getInstance().refresh();
        } catch (MyException le) {
            MainFrame.getInstance().showError(le);
        }
    }
}