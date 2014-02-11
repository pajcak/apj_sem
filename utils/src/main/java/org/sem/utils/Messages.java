package org.sem.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skarab
 */
public enum Messages {

    Id,
    Name,
    Address,
    Socket,
    Map,
    Players,
    Capacity,
    Exit,
    File,
    Title,
    Main_Frame,
    No_conection,
    Create_Server,
    Change_Server,
    Delete_Server,
    Create_Game,
    Change_Game,
    Delete_Game,
    Server,
    Game,
    No_selected_server,
    Connection_Dialog,
    Invalid_port,
    Servers,
    Games,
    Cancel,
    No_connection,
    Error,
    Exit_application,
    Empty_name,
    Empty_address,
    Empty_socket,
    Bad_format_socket,
    Bad_format_capacity,
    Refresh,
    View,
    OK,
    Connect,
    Connect_Dialog,
    Connection,
    Disconnect,
    Host,
    Port;

    public String cm(Object... args) {
        ResourceBundle bnd = ResourceBundle.getBundle("org.sem.utils.messages");
        try {
            String tmpl = bnd.getString(name());
            return MessageFormat.format(tmpl, args);
        } catch (MissingResourceException | IllegalArgumentException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Missing resource: " + name());
            return name().replace('_', ' ');
        }
    }
}
