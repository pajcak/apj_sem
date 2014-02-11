package org.sem.derbydb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sem.integration.GameDAO;
import org.sem.model.Game;
import org.sem.model.GameId;
import org.sem.model.Server;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class DerbyGameDAO implements GameDAO {

    public static int id = 0;
    PreparedStatement createPS;
    private PreparedStatement findAllPS;
    private PreparedStatement deletePS;

    public DerbyGameDAO(Connection connection) {
        try {
            createPS = connection.prepareStatement("INSERT INTO GAMES VALUES(?, ?, ?, ?, ?)");
            deletePS = connection.prepareStatement("DELETE FROM GAMES WHERE ID = ?");
            findAllPS = connection.prepareStatement("SELECT * FROM GAMES");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyGameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GameId create(Server server, String name, String map, int capacity, int players) throws MyException {
        try {
            id++;
            createPS.setInt(1, id);
            createPS.setString(2, name);
            createPS.setString(3, map);
            createPS.setInt(4, capacity);
            createPS.setInt(5, players);
            createPS.execute();
            return new GameId(id);
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }

    @Override
    public void delete(GameId id) throws MyException {
        try {
            deletePS.setInt(1, id.getId());
            deletePS.execute();
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }

    @Override
    public void update(Game game) throws MyException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Game find(GameId id) throws MyException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Game> getAll() throws MyException {
        try {
            ResultSet rs = findAllPS.executeQuery();
            ArrayList<Game> games = new ArrayList<>();
            while (rs.next()) {
                games.add(new Game(new GameId(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        Integer.parseInt(rs.getString(4)),
                        Integer.parseInt(rs.getString(5))));
            }
            return games;
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }
}
