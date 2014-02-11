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
import org.sem.model.Server;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class GameDAOimpl implements GameDAO {

    private static int keyCount = 0;
    private Map<GameId, Game> games = new ConcurrentHashMap<>();

    public GameDAOimpl() {
        /*try {
            create("Center1234", "Field", 0, 4);
            create("SideGame", "Area", 2, 5);
        } catch (MyException ex) {
            Logger.getLogger(GameDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @Override
    public GameId create(Server server, String name, String map, int capacity, int players)  throws MyException {
        GameId id = new GameId(++keyCount);
        Game game = new Game(id, name, map, capacity, players);
        games.put(game.getId(), game);
        return id;
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
    public Collection<Game> getAll() throws MyException {
        return new ArrayList<Game>(games.values());
    }
}
