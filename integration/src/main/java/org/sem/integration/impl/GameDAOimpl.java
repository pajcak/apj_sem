package org.sem.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sem.integration.GameDAO;
import org.sem.model.Game;
import org.sem.model.GameId;
import org.sem.model.ServerId;
import org.sem.utils.Maps;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class GameDAOimpl implements GameDAO {

    private static int keyCount = 0;
    private Map<GameId, Game> games = new ConcurrentHashMap<>();

    public GameDAOimpl() {
        try {
            create(new ServerId(1), "ExGame1", Maps.CAVES, 0, 4);
            create(new ServerId(1), "ExGame2", Maps.PLAINS, 2, 5);
            create(new ServerId(2), "ExGame3", Maps.CAVES, 2, 5);
        } catch (MyException ex) {
            Logger.getLogger(GameDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(ServerId serverId, String name, Maps map, int capacity, int players) throws MyException {
        GameId id = new GameId(++keyCount);
        Game game = new Game(id, serverId, name, map, capacity, players);
        games.put(game.getId(), game);
    }

    @Override
    public void delete(GameId id) throws MyException {
        games.remove(id);
    }

    @Override
    public void update(Game game) throws MyException {
        games.put(game.getId(), game);
    }

    @Override
    public Game find(GameId id) throws MyException {
        return games.get(id);
    }

    @Override
    public Collection<Game> findServer(ServerId id) throws MyException {
        Collection<Game> res = new ArrayList<>();
        for (Game game : games.values()) {
            if (game.getServerId().equals(id)) {
                res.add(game);
            }
        }
        return res;
    }

    @Override
    public Collection<Game> getAll() throws MyException {
        return new ArrayList<Game>(games.values());
    }
}
