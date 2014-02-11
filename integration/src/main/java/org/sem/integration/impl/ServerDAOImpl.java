package org.sem.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sem.integration.ServerDAO;
import org.sem.model.Server;
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class ServerDAOImpl implements ServerDAO {

    private static int keyCount = 0;
    private Map<ServerId, Server> servers = new ConcurrentHashMap<>();

    public ServerDAOImpl() {
        try {
            create("America", "1.2.3.4", 3113);
            create("Europe", "1.2.3.4", 3113);
        } catch (MyException ex) {
            Logger.getLogger(ServerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void create(String name, String address, int socket) throws MyException {
        ServerId id = new ServerId(++keyCount);
        Server server = new Server(id, name, address, socket);
        servers.put(server.getId(), server);
    }

    @Override
    public void delete(ServerId id) throws MyException {
        servers.remove(id);
    }

    @Override
    public void update(Server server) throws MyException {
        servers.put(server.getId(), server);
    }

    @Override
    public Server find(ServerId id) throws MyException {
        return servers.get(id);
    }

    @Override
    public Collection<Server> getAll() throws MyException {
        return new ArrayList<Server>(servers.values());
    }
}
