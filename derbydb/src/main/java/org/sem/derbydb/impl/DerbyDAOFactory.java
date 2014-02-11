package org.sem.derbydb.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.osgi.framework.BundleContext;
import org.sem.integration.DAOFactoryService;
import org.sem.integration.GameDAO;
import org.sem.integration.HostDAO;
import org.sem.integration.ServerDAO;

/**
 *
 * @author Skarab
 */
public class DerbyDAOFactory extends DAOFactoryService {

    private Connection dbConnection;
    private DerbyGameDAO derbyGameDAO;
    private DerbyServerDAO derbyServerDAO;
    private DerbyHostDAO derbyHostDAO;
    private BundleContext context;

    public DerbyDAOFactory(BundleContext context) {
        try {
            this.context = context;
            dbConnection = createConnection();
            dbConnection.setAutoCommit(false);
            DatabaseMetaData m = dbConnection.getMetaData();
            ResultSet rs = m.getTables(null, null, "GAMES", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Table GAMES generated");
                Statement s = dbConnection.createStatement();
                s.executeUpdate("CREATE TABLE GAMES"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "NAME VARCHAR(50),"
                        + "MAP VARCHAR(50),"
                        + "CAPACITY INT,"
                        + "PLAYERS INT,"
                        + "FOREIGN KEY (SER_ID) REFERENCES SERVERS (ID)"
                        + "PRIMARY KEY (ID))");

            }
            rs = m.getTables(null, null, "SERVERS", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Table SERVERS generated");
                Statement s = dbConnection.createStatement();
                s.executeUpdate("CREATE TABLE SERVERS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "NAME VARCHAR(50),"
                        + "ADDRESS VARCHAR(50),"
                        + "SOCKET INT,"
                        + "PRIMARY KEY (ID))");
            }
            dbConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            new EmbeddedDriver();
            String url = "jdbc:derby:" + System.getProperty("user.home") + "\\myDB;";
            //url += " create=true";
            //url += "jdbc:derby:" + System.getProperty("user.home") + "\\myDB; create=true";
            //org.apache.derby.jdbc.ClientDriver;
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Override
    public ServerDAO getServerDAO() {
        if (derbyServerDAO == null) {
            derbyServerDAO = new DerbyServerDAO(getDbConnection());
        }
        return derbyServerDAO;
    }

    @Override
    public GameDAO getGameDAO() {
        if (derbyGameDAO == null) {
            derbyGameDAO = new DerbyGameDAO(getDbConnection());
        }
        return derbyGameDAO;
    }

    @Override
    public HostDAO getHostDAO() {
        if (derbyHostDAO == null) {
            derbyHostDAO = new DerbyHostDAO(getDbConnection());
        }
        return derbyHostDAO;
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    @Override
    public void commit() {
        try {
            dbConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rollback() {
        try {
            dbConnection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
