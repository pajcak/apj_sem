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
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class DerbyGameDAO implements GameDAO {

    private static int id = 0;
    private PreparedStatement createPS;
    private PreparedStatement findAllPS;
    private PreparedStatement findOnePS;
    private PreparedStatement findServerPS;
    private PreparedStatement deletePS;

    public DerbyGameDAO(Connection connection) {
        try {
            createPS = connection.prepareStatement("INSERT INTO GAMES VALUES(DEFAULT, ?, ?, ?, ?, ?)");
            deletePS = connection.prepareStatement("DELETE FROM GAMES WHERE ID = ?");
            findAllPS = connection.prepareStatement("SELECT * FROM GAMES");
            findServerPS = connection.prepareStatement("SELECT * FROM GAMES WHERE SER_ID = ?");
            findOnePS = connection.prepareStatement("SELECT * FROM GAMES WHERE ID = ?");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyGameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(ServerId serverId, String name, String map, int capacity, int players) throws MyException {
        try {
            createPS.setInt(1, serverId.getId());
            createPS.setString(2, name);
            createPS.setString(3, map);
            createPS.setInt(4, capacity);
            createPS.setInt(5, players);
            createPS.execute();
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
        delete(game.getId());
        create(game.getServerId(), game.getName(), game.getMap(), game.getPlayers(), game.getCapacity());
    }

    @Override
    public Game find(GameId id) throws MyException {
        try {
            findOnePS.setInt(1, id.getId());
            ResultSet rs = findOnePS.executeQuery();
            if (rs.next()) {
                return new Game(new GameId(rs.getInt(1)),
                        new ServerId(Integer.parseInt(rs.getString(2))),
                        rs.getString(3),
                        rs.getString(4),
                        Integer.parseInt(rs.getString(5)),
                        Integer.parseInt(rs.getString(6)));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyGameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Collection<Game> findServer(ServerId id) throws MyException {

        try {
            findServerPS.setInt(1, id.getId());
            ResultSet rs = findServerPS.executeQuery();
            ArrayList<Game> games = new ArrayList<>();
            while (rs.next()) {
                games.add(new Game(new GameId(rs.getInt(1)),
                        new ServerId(Integer.parseInt(rs.getString(2))),
                        rs.getString(3),
                        rs.getString(4),
                        Integer.parseInt(rs.getString(5)),
                        Integer.parseInt(rs.getString(6))));
            }
            return games;
        } catch (SQLException ex) {
            Logger.getLogger(DerbyGameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Collection<Game> getAll() throws MyException {
        try {
            ResultSet rs = findAllPS.executeQuery();
            ArrayList<Game> games = new ArrayList<>();
            while (rs.next()) {
                games.add(new Game(new GameId(rs.getInt(1)),
                        new ServerId(Integer.parseInt(rs.getString(2))),
                        rs.getString(3),
                        rs.getString(4),
                        Integer.parseInt(rs.getString(5)),
                        Integer.parseInt(rs.getString(6))));
            }
            return games;
        } catch (SQLException ex) {
            throw new MyException(ex);
        }
    }
}
