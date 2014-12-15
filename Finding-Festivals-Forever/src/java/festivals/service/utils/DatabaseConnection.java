package festivals.service.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * Singleton implementation of a connection to a database
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

    /**
     * Return singleton instance of class
     *
     * @return
     */
    public static DatabaseConnection getInstance() {
        return DBConnectionHolder.INSTANCE;
    }

    /**
     * Initialise a connection to the database using the credentials stored in
     * the config file
     */
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

    /**
     * Execute SQL statement as a PreparedStatement with specified parameters
     *
     * @param sqlToExecute Statement to execute
     * @param params Params to use with statement
     * @return Execution of statement successful
     */
    public boolean executeSQL(String sqlToExecute, Object... params) {
        PreparedStatement ps = createPreparedStatement(sqlToExecute, params);
        try {
            return executeSQL(ps);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement", ex);
        }
        return false;
    }

    private boolean executeSQL(PreparedStatement ps) throws SQLException {
        try {
            if (conn == null) {
                initConnectionToDb();
            }

            if (ps != null) {
                ps.execute();
                return true;
            }

            return false;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql statement", ex);
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }

        }
    }

    /**
     * Execute SQL query statement as a PreparedStatement with specified
     * parameters
     *
     * @param sqlQuery Query statement to execute
     * @param props Properties to return out from query
     * @param params Params to use with statement
     * @return List of requested properties with values from database
     * @throws SQLException
     */
    public List<Map<String, Object>> queryDB(String sqlQuery, List<String> props, Object... params) throws SQLException {
        PreparedStatement ps = null;
        List<Map<String, Object>> returnedProps = new ArrayList<>();

        try {
            ps = createPreparedStatement(sqlQuery, params);
            ResultSet res = queryDB(ps);

            while (res.next()) {
                Map<String, Object> result = new HashMap<>();

                for (String prop : props) {
                    result.put(prop, res.getObject(prop));
                }

                returnedProps.add(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error manipulating ResultSet", ex);
        }
        finally {
            if (ps != null) {
                ps.close();
            }
        }

        return returnedProps;
    }

    private ResultSet queryDB(PreparedStatement ps) {
        ResultSet res = null;
        try {
            if (conn == null) {
                initConnectionToDb();
            }

            if (ps != null) {
                res = ps.executeQuery();

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql query statement", ex);
        }

        return res;
    }

    /**
     * Execute SQL update statement as a PreparedStatement with specified
     * parameters
     *
     * @param sqlUpdate Update statement to execute
     * @param params Params to use with statement
     */
    public void updateDB(String sqlUpdate, Object... params) {
        try {
            PreparedStatement ps = createPreparedStatement(sqlUpdate, params);
            updateDB(ps);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement", ex);
        }
    }

    private void updateDB(PreparedStatement ps) throws SQLException {
        try {
            if (conn == null) {
                initConnectionToDb();
            }
            if (ps != null) {
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql update statement", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    /**
     * Create a PreparedStatement for an SQL string statement
     *
     * @param sqlString SQL statement to create PreparedStatement from
     * @param params Parameters for the PreparedStatement
     * @return PreparedStatement of SQL statement
     */
    private PreparedStatement createPreparedStatement(String sqlString, Object... params) {
        PreparedStatement ps = null;

        try {
            if (conn == null) {
                initConnectionToDb();
            }

            ps = conn.prepareStatement(sqlString);

            if (ps != null) {
                for (int i = 0; i < params.length; ++i) {
                    Object param = params[i];
                    if (param != null) {

                        Class paramClass = param.getClass();

                        if (paramClass == String.class) {
                            ps.setString(i + 1, (String) param);
                        } else if (paramClass == Integer.class) {
                            ps.setInt(i + 1, (int) param);
                        } else if (paramClass == Float.class) {
                            ps.setFloat(i + 1, (float) param);
                        } else {
                            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "PreparedStatement set method not mapped");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error creating or manipulating new preparedStatement", ex);
        }

        return ps;
    }
}
