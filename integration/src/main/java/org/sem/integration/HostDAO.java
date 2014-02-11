package org.sem.integration;

import java.util.Collection;
import org.sem.model.GameId;
import org.sem.model.Host;
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public interface HostDAO {

    void createHost(ServerId serverId, GameId gameId) throws MyException;

    void destroyHost(ServerId readerId, GameId gameId) throws MyException;

    Collection<Host> hosts(ServerId ServerId) throws MyException;

    Host findGame(GameId gameId) throws MyException;
}
