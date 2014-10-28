/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.service.utils;

import com.mysql.jdbc.ConnectionImpl;
import com.mysql.jdbc.ResultSetImpl;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tom
 */
@Service
public class DatabaseConnection {
        
    private static class DBConnectionHolder {
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }
    
    private Connection conn;

    private DatabaseConnection() {
        initConnectionToDb();
    }
    
    public static DatabaseConnection getInstance() {
        return DBConnectionHolder.INSTANCE;
    }

    private void initConnectionToDb() {
        ConfigFileProperties config = ConfigFileProperties.getInstance();
        String url = config.getPropertyValue("dbUrl");
        String dbName = config.getPropertyValue("dbName");
        String userName = config.getPropertyValue("dbUserName");
        String password = config.getPropertyValue("dbPassword");
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        }
    }

    public void executeSQL(String sqlToExecute) {
        try {
            if (conn == null) {
                initConnectionToDb();
            }
            Statement st = conn.createStatement();
            st.execute(sqlToExecute);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql statement", ex);
        }
    }

    public ResultSet queryDB(String sqlQuery) {
        ResultSet res = null;
        try {
            if (conn == null) {
                initConnectionToDb();
            }
            Statement st = conn.createStatement();
            res = st.executeQuery(sqlQuery);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error querying sql db", ex);
        }
        
        return res;
    }

    public void updateDB(String sqlUpdate) {
        try {
            if (conn == null) {
                initConnectionToDb();
            }
            Statement st = conn.createStatement();
            st.executeUpdate(sqlUpdate);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error updating sql db", ex);
        }
    }
}
