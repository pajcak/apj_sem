package org.sem.view;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.sem.business.FacadeService;
import org.sem.model.Server;
import static org.sem.utils.Messages.*;
import org.sem.utils.MyException;
import org.sem.view.impl.Refreshable;

/**
 *
 * @author Skarab
 */
public final class ServerModel extends AbstractTableModel implements Refreshable {

    private List<Server> servers;

    public ServerModel() {
        this.servers = new ArrayList<>();
        MainFrame.addRefreshable(this);
    }

    public ServerModel(Collection<Server> servers) {
        this.servers = new ArrayList<>(servers);
    }

    public Server getServer(int row) {
        return servers.get(row);
    }

    public Collection<Server> getServers(int[] rows) {
        ArrayList<Server> sbs = new ArrayList<>();
        for (int row : rows) {
            sbs.add(servers.get(row));
        }
        return sbs;
    }

    @Override
    public void refresh() throws MyException {
        Collection<Server> rs = FacadeService.getDefault().getServers();
        servers = new ArrayList<>(rs);
        Collections.sort(servers, new Comparator<Server>() {
            @Override
            public int compare(Server t, Server t1) {
                return Collator.getInstance().compare(t.getName(), t1.getName());
            }
        });
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return servers.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Server r = getServer(row);
        switch (col) {
            case 0:
                return r.getName();
            case 1:
                return r.getAddress();
            case 2:
                return r.getSocket();
            default:
                assert false;
                return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return Name.cm();
            case 1:
                return Address.cm();
            case 2:
                return Socket.cm();
            default:
                assert false;
                return null;
        }
    }
}
