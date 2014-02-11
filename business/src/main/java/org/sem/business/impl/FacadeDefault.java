package org.sem.business.impl;

import java.util.Collection;
import org.sem.business.FacadeService;
import org.sem.integration.DAOFactoryService;
import org.sem.model.Game;
import org.sem.model.GameId;
import org.sem.model.Server;
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class FacadeDefault extends FacadeService {

    @Override
    public void createServer(String name, String address, int socket) throws MyException {
        DAOFactoryService.getDefault().getServerDAO().create(name, address, socket);
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public void deleteServers(Collection<ServerId> servers) throws MyException {
        for (ServerId id : servers) {
            DAOFactoryService.getDefault().getServerDAO().delete(id);
        }
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public void createGame(Server server, String name, String map, int players, int capacity) throws MyException {
        GameId id = DAOFactoryService.getDefault().getGameDAO().create(server, name, map, players, capacity);
        DAOFactoryService.getDefault().getHostDAO().createHost(server.getId(), id);
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public void deleteGames(Collection<GameId> games) throws MyException {
        for (GameId id : games) {
            DAOFactoryService.getDefault().getGameDAO().delete(id);
        }
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public void hostGame(ServerId serverId, GameId gameId) throws MyException {
        DAOFactoryService.getDefault().getHostDAO().createHost(serverId, gameId);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public Collection<Server> getServers() throws MyException {
        return DAOFactoryService.getDefault().getServerDAO().getAll();
    }

    @Override
    public Collection<Game> getGames() throws MyException {
        return DAOFactoryService.getDefault().getGameDAO().getAll();
    }
}
