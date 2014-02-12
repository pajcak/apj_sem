package org.sem.integration;

import java.util.Collection;
import org.sem.model.Game;
import org.sem.model.GameId;
import org.sem.model.ServerId;
import org.sem.utils.Maps;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public interface GameDAO {

    void create(ServerId serverId, String name, Maps map, int capacity, int players) throws MyException;

    void delete(GameId id) throws MyException;

    void update(Game game) throws MyException;

    Game find(GameId id) throws MyException;

    Collection<Game> findServer(ServerId id) throws MyException;

    Collection<Game> getAll() throws MyException;
}
