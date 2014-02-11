package org.sem.model;

import java.io.Serializable;

/**
 *
 * @author Skarab
 */
public class Server implements Serializable {

    private ServerId id;
    private String name;
    private String address;
    private int socket;

    public Server(ServerId id, String name, String address, int socket) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getSocket() {
        return socket;
    }

    public ServerId getId() {
        return id;
    }

    public void setId(ServerId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSocket(int socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return getName();
    }
}
