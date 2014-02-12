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
public enum Maps {

    PLAINS,
    CAVES,
    TOWN;

    @Override
    public String toString() {
        ResourceBundle bnd = ResourceBundle.getBundle("org.sem.utils.maps");
        try {
            String tmpl = bnd.getString(name());
            return MessageFormat.format(tmpl, new Object[1]);
        } catch (MissingResourceException | IllegalArgumentException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Missing resource: " + name());
            return name().replace('_', ' ');
        }
    }
}
