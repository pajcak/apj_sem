package org.sem.derbydb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sem.integration.ServerDAO;
import org.sem.model.Server;
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class DerbyServerDAO implements ServerDAO {

    private PreparedStatement createPs;
    private PreparedStatement findAllPs;
    private PreparedStatement deletePs;
    private PreparedStatement findPs;

    public DerbyServerDAO(Connection connection) {
        try {
            deletePs = connection.prepareStatement("DELETE FROM SERVERS WHERE ID = ?");
            createPs = connection.prepareStatement("INSERT INTO SERVERS VALUES(DEFAULT, ?, ?, ?)");
            findAllPs = connection.prepareStatement("SELECT * FROM SERVERS");
            findPs = connection.prepareStatement("SELECT * FROM SERVERS WHERE ID = ?");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(String name, String address, int socket) throws MyException {
        try {
            createPs.setString(1, name);
            createPs.setString(2, address.toString());
            createPs.setInt(3, socket);
            createPs.execute();
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }

    @Override
    public void delete(ServerId id) throws MyException {
        try {
            deletePs.setInt(1, id.getId());
            deletePs.executeUpdate();
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }

    @Override
    public Server find(ServerId id) throws MyException {
        try {
            ResultSet rs = findPs.executeQuery();
            return new Server(new ServerId(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4));
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }

    @Override
    public Collection<Server> getAll() throws MyException {
        try {
            ResultSet rs = findAllPs.executeQuery();
            ArrayList<Server> servers = new ArrayList<>();
            while (rs.next()) {
                servers.add(new Server(
                        new ServerId(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
            return servers;
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }

    @Override
    public void update(Server reader) throws MyException {
        //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
