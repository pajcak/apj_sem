package org.sem.integration;

import java.util.Collection;
import org.sem.model.Game;
import org.sem.model.GameId;
import org.sem.model.Server;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public interface GameDAO {

    GameId create(Server server, String name, String map, int capacity, int players) throws MyException;

    void delete(GameId id) throws MyException;

    void update(Game game) throws MyException;

    Game find(GameId id) throws MyException;

    Collection<Game> getAll() throws MyException;
}
