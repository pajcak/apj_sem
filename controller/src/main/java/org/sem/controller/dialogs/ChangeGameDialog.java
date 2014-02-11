package org.sem.controller.dialogs;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import org.sem.business.FacadeService;
import org.sem.model.Game;
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
public class ChangeGameDialog extends MyAbstractDialog implements Validator {

    private JLabel server;
    private JLabel oldName;
    private ValidatedTF name;
    private JLabel oldMap;
    private JComboBox<String> map;
    private JLabel oldCapacity;
    private ValidatedTF capacity;
    private Game game;

    public ChangeGameDialog(Game game) {
        super(Messages.Change_Game.cm());
        this.game = game;
        oldName = new JLabel(game.getName());
        oldMap = new JLabel(game.getMap());
        oldCapacity = new JLabel("" + game.getCapacity());
        name = new ValidatedTF(this);
        map = new JComboBox<>();
        map.addItem("Caves map");
        map.addItem("Plains map");
        map.addItem("Town map");
        capacity = new ValidatedTF(this);
        getContent().setLayout(new GridBagLayout());
        for (int i = 0; i < map.getItemCount(); i++) {
            if (map.getItemAt(i).equals(game.getMap())) {
                map.setSelectedIndex(i);
                break;
            }
        }
        int row = 0;
        /*getContent().add(new JLabel(Messages.Server.cm() + ": "), new GBCBuilder().setY(row).build());
         getContent().add(server, new GBCBuilder().setY(row).setXRel().build());
         row++;*/
        getContent().add(new JLabel(Messages.Name.cm() + ": "), new GBCBuilder().setY(row).build());
        getContent().add(oldName, new GBCBuilder().setY(row).setXRel().build());
        getContent().add(name, new GBCBuilder().setY(row).setXRel().build());
        row++;
        getContent().add(new JLabel(Messages.Map.cm() + ": "), new GBCBuilder().setY(row).build());
        getContent().add(oldMap, new GBCBuilder().setY(row).setXRel().build());
        getContent().add(map, new GBCBuilder().setY(row).setXRel().build());
        row++;
        getContent().add(new JLabel(Messages.Capacity.cm() + ": "), new GBCBuilder().setY(row).build());
        getContent().add(oldCapacity, new GBCBuilder().setY(row).setXRel().build());
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
            FacadeService.getDefault().changeGame(new Game(
                    game.getId(),
                    game.getServerId(),
                    name.getText(),
                    map.getSelectedItem().toString(),
                    0,
                    Integer.parseInt(capacity.getText())));
            MainFrame.getInstance().refresh();
        } catch (MyException ex) {
            MainFrame.getInstance().showError(ex);
        }
    }
}