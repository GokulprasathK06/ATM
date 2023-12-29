package Process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DatabaseConnection {
	 private static Connection connection;
	    private static final ConfigureManger configure = new ConfigureManger();
	    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

	    public static Connection getConnection() {
	        if (connection == null) {
	            try {
	                String dbUrl = configure.getProperty("db.url");
	                String dbUser = configure.getProperty("db.username");
	                String dbPassword = configure.getProperty("db.password");

	                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	                Class.forName(configure.getProperty("db.Name"));
	            } catch (SQLException | ClassNotFoundException exception) {
	                logger.log(Level.SEVERE, "Error while establishing database connection.", exception);
	            }
	        }
	        return connection;
	    }
}
