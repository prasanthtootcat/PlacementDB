package placementDB;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class Connect {
	
	public static Connection connection = null;
	public static Statement stmt;
	Connect()
	{
		
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;

		}

		

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/placement", "postgres",
					"preshu123");

		} catch (SQLException e) {
			e.printStackTrace();
			return;

		}

	}

}
