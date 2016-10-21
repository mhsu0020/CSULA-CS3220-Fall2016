import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtils {
	
	public static Connection getMySQLConnection(String userName, String password, String serverName, int portNumber, String database) throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", userName);
	    connectionProps.put("password", password);

	    conn = DriverManager.getConnection(
	                   "jdbc:mysql://" + serverName + ":" + portNumber + "/" + database, connectionProps);
	    return conn;
	}

}
