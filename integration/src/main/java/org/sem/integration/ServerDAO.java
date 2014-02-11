package org.sem.integration;

import java.util.Collection;
import org.sem.model.Server;
import org.sem.model.ServerId;
import org.sem.utils.MyException;

/**
 *
 * @author Skarab
 */
public interface ServerDAO {

    void create(String name, String address, int socket) throws MyException;

    void delete(ServerId id) throws MyException;

    void update(Server reader) throws MyException;

    Server find(ServerId id) throws MyException;

    Collection<Server> getAll() throws MyException;
}
