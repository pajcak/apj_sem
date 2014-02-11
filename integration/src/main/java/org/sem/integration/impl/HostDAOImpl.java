package org.sem.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.sem.integration.HostDAO;
import org.sem.model.GameId;
import org.sem.model.Host;
import org.sem.model.HostId;
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public class HostDAOImpl implements HostDAO {

    private List<Host> hosts;

    @Override
    public void createHost(ServerId serverId, GameId gameId) throws MyException {
        hosts.add(new Host(serverId, gameId));
    }

    @Override
    public void destroyHost(ServerId serverId, GameId gameId) throws MyException {
        Host tmp = new Host(serverId, gameId);
        hosts.remove(tmp);
    }

    @Override
    public Collection<Host> hosts(ServerId serverId) throws MyException {
        Collection<Host> res = new ArrayList<>();
        for (Host host : hosts) {
            if (host.serverId.equals(serverId)) {
                res.add(host);
            }
        }
        return res;
    }

    @Override
    public Host findGame(GameId gameId) throws MyException {
        for (Host host : hosts) {
            if (host.gameId.equals(gameId)) {
                return host;
            }
        }
        return null;
    }
}
