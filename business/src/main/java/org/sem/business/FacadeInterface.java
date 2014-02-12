package org.sem.business;

import java.util.Collection;
import org.sem.model.Game;
import org.sem.model.GameId;
import org.sem.model.Server;
import org.sem.model.ServerId;
import org.sem.utils.Maps;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public interface FacadeInterface {

    void createGame(ServerId serverId, String name, Maps map, int players, int capacity) throws MyException;

    void changeGame(Game game) throws MyException;

    void deleteGames(Collection<GameId> games) throws MyException;

    void createServer(String name, String address, int socket) throws MyException;

    void deleteServers(Collection<ServerId> servers) throws MyException;
    /*
     void returnBooks(ReaderId id, Collection<Server> books) throws LibraryException;

     void updateBook(Book book) throws LibraryException;

     void updateReader(Reader reader) throws LibraryException;
     */

    Collection<Server> getServers() throws MyException;

    Collection<Game> getGames() throws MyException;

    boolean isAvailable();
}
