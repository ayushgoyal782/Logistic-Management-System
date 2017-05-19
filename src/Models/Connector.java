package Models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
	
	static Connection connection = null;
	
public static Connection getConnection() {
		
		try
		{
			connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Acer/Documents/Database1.accdb");
		}
		
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		return connection;
		}


}
