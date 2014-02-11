package org.sem.derbydb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sem.integration.HostDAO;
import org.sem.model.GameId;
import org.sem.model.Host;
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class DerbyHostDAO implements HostDAO {

    private List<Host> hosts;
    PreparedStatement createPS;
    private PreparedStatement findAllPS;
    private PreparedStatement deletePS;

    public DerbyHostDAO(Connection connection) {
        try {
            createPS = connection.prepareStatement("INSERT INTO GAMES VALUES(?, ?, ?, ?, ?)");
            deletePS = connection.prepareStatement("DELETE FROM GAMES WHERE ID = ?");
            findAllPS = connection.prepareStatement("SELECT * FROM GAMES");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyGameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createHost(ServerId serverId, GameId gameId) throws MyException {
        hosts.add(new Host(serverId, gameId));
    }

    @Override
    public void destroyHost(ServerId serverId, GameId gameId) throws MyException {
        Host tmp = new Host(serverId, gameId);
        hosts.remove(tmp);
    }

    @Override
    public Collection<Host> hosts(ServerId serverId) throws MyException {
        Collection<Host> res = new ArrayList<>();
        for (Host host : hosts) {
            if (host.serverId.equals(serverId)) {
                res.add(host);
            }
        }
        return res;
    }

    @Override
    public Host findGame(GameId gameId) throws MyException {
        for (Host host : hosts) {
            if (host.gameId.equals(gameId)) {
                return host;
            }
        }
        return null;
    }
}
