package org.sem.model;

import java.io.Serializable;
import org.sem.utils.Maps;

/**
 *
 * @author Skarab
 */
public class Game implements Serializable{
    private GameId id;
    private ServerId serId;
    private String name;
    private Maps map;
    private int players;
    private int capacity;

    public Game(GameId id, ServerId serId, String name, Maps map, int players, int capacity) {
        this.id = id;
        this.serId = serId;
        this.name = name;
        this.map = map;
        this.players = players;
        this.capacity = capacity;
    }

    public GameId getId() {
        return id;
    }

    public ServerId getServerId() {
        return serId;
    }

    public String getName() {
        return name;
    }

    public Maps getMap() {
        return map;
    }

    public int getPlayers() {
        return players;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(GameId id) {
        this.id = id;
    }

    public void setSerId(ServerId serId) {
        this.serId = serId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMap(Maps map) {
        this.map = map;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
