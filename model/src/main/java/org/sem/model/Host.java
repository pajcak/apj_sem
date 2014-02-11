package org.sem.model;

import java.io.Serializable;

/**
 *
 * @author Skarab
 */
public class Host implements Serializable {

    public ServerId serverId;
    public GameId gameId;

    public Host() {
    }

    public Host(ServerId serverId, GameId gameId) {
        this.serverId = serverId;
        this.gameId = gameId;
    }
}
