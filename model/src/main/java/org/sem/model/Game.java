package org.sem.model;

import java.io.Serializable;

/**
 *
 * @author Skarab
 */
public class Game implements Serializable{
    private GameId id;
    private String name;
    private String map;
    private int capacity;
    private int players;

    public Game(GameId id, String name, String map, int capacity, int players) {
        this.id = id;
        this.name = name;
        this.map = map;
        this.capacity = capacity;
        this.players = players;
    }

    public GameId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMap() {
        return map;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPlayers() {
        return players;
    }

    public void setId(GameId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
