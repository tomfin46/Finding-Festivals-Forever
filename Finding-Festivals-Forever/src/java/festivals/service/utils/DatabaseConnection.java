/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.service.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String driver = config.getPropertyValue("dbDriver");

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        }
    }

    public boolean executeSQL(String sqlToExecute, Object... params) {
        PreparedStatement ps = createPreparedStatement(sqlToExecute, params);
        return executeSQL(ps);
    }

    private boolean executeSQL(PreparedStatement ps) {
        try {
            if (conn == null) {
                initConnectionToDb();
            }
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql statement", ex);
            return false;
        }
    }

    public List<Map<String, Object>> queryDB(String sqlQuery, List<String> props, Object... params) throws SQLException {
        PreparedStatement ps = null;
        List<Map<String, Object>> returnedProps = new ArrayList<>();

        try {
            ps = createPreparedStatement(sqlQuery, params);
            ResultSet res = queryDB(ps);

            //if (res.isBeforeFirst()) {
                
              while(  res.next()) {//;
                
                  Map<String, Object> result = new HashMap<>();
                  
                for (String prop : props) {
                    result.put(prop, res.getObject(prop));
                }
                
                returnedProps.add(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

        return returnedProps;
    }

    private ResultSet queryDB(PreparedStatement ps) throws SQLException { // Throws from the finally block
        ResultSet res = null;

        if (conn == null) {
            initConnectionToDb();
        }

        if (ps != null) {
            res = ps.executeQuery();
        }

        return res;
    }

    public void updateDB(String sqlUpdate, Object... params) {
        try {
            PreparedStatement ps = createPreparedStatement(sqlUpdate, params);
            updateDB(ps);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDB(PreparedStatement ps) throws SQLException {
        try {
            if (conn == null) {
                initConnectionToDb();
            }
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error updating sql db", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    private PreparedStatement createPreparedStatement(String sqlString, Object... params) {
        PreparedStatement ps = null;

        try {
            if (conn == null) {
                initConnectionToDb();
            }

            ps = conn.prepareStatement(sqlString);

            for (int i = 0; i < params.length; ++i) {
                Object param = params[i];
                if (param != null) {

                    Class paramClass = param.getClass();

                    int dotIdx = paramClass.getName().lastIndexOf('.');
                    String paramClassName = "";

                    if (dotIdx > 0) {
                        paramClassName = paramClass.getName().substring(dotIdx + 1);
                    } else {
                        paramClassName = paramClass.getName();
                    }

                    Method methodToFind = null;
                    methodToFind = PreparedStatement.class.getMethod("set" + paramClassName, new Class[]{int.class, paramClass});

                    if (methodToFind == null) {
                        // Method not found.
                    } else {
                        // Method found therefore invoke it
                        methodToFind.invoke(ps, i + 1, param);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ps;
    }
}
