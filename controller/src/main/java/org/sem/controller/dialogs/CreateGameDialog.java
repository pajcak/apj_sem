package org.sem.controller.dialogs;

import java.awt.GridBagLayout;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import org.sem.business.FacadeService;
import org.sem.model.Server;
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
public class CreateGameDialog extends MyAbstractDialog implements Validator {

    private JComboBox<Server> server;
    private ValidatedTF name;
    private JComboBox<String> map;
    private ValidatedTF capacity;

    public CreateGameDialog() {
        super(Messages.Create_Game.cm());
        server = new JComboBox<>();
        try {
            for (Server s : FacadeService.getDefault().getServers()) {
                server.addItem(s);
            }
        } catch (MyException ex) {
            Logger.getLogger(CreateGameDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collection<Server> selectedServers = MainFrame.getInstance().getSelectedServers();
        if (selectedServers.size() > 0) {
            Server item = selectedServers.iterator().next();
            for (int i = 0; i < server.getItemCount(); i++) {
                if (item.getId().equals(server.getItemAt(i).getId())) {
                    server.setSelectedIndex(i);
                }
            }
        }
        name = new ValidatedTF(this);
        map = new JComboBox<>();
        map.addItem("Caves map");
        map.addItem("Plains map");
        map.addItem("Town map");
        capacity = new ValidatedTF(this);
        getContent().setLayout(new GridBagLayout());
        int row = 0;
        getContent().add(new JLabel(Messages.Server.cm() + ": "), new GBCBuilder().setY(row).build());
        getContent().add(server, new GBCBuilder().setY(row).setXRel().build());
        row++;
        getContent().add(new JLabel(Messages.Name.cm() + ": "), new GBCBuilder().setY(row).build());
        getContent().add(name, new GBCBuilder().setY(row).setXRel().build());
        row++;
        getContent().add(new JLabel(Messages.Map.cm() + ": "), new GBCBuilder().setY(row).build());
        getContent().add(map, new GBCBuilder().setY(row).setXRel().build());
        row++;
        getContent().add(new JLabel(Messages.Capacity.cm() + ": "), new GBCBuilder().setY(row).build());
        getContent().add(capacity, new GBCBuilder().setY(row).setXRel().build());
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
        } else {
            try {
                Integer.parseInt(capacity.getText());
            } catch (NumberFormatException ex) {
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
            FacadeService.getDefault().createGame(
                    (Server) server.getSelectedItem(),
                    name.getText(),
                    map.getSelectedItem().toString(),
                    0,
                    Integer.parseInt(capacity.getText()));
            MainFrame.getInstance().refresh();
        } catch (MyException le) {
            MainFrame.getInstance().showError(le);
        }
    }
}