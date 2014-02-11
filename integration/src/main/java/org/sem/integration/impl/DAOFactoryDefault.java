package org.sem.integration.impl;

import org.sem.integration.DAOFactoryService;
import org.sem.integration.GameDAO;
import org.sem.integration.ServerDAO;

/**
 *
 * @author Skarab
 */
public class DAOFactoryDefault extends DAOFactoryService {

    private ServerDAO serverDAO;
    private GameDAO gameDAO;

    @Override
    public ServerDAO getServerDAO(){
        if (serverDAO == null) {
            serverDAO = new ServerDAOImpl();
        }
        return serverDAO;
    }

    @Override
    public GameDAO getGameDAO(){
        if (gameDAO == null) {
            gameDAO = new GameDAOimpl();
        }
        return gameDAO;
    }

    @Override
    public void commit() {
    }

    @Override
    public void rollback() {
    }
}
