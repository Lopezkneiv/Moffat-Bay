package Connector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String user = "team";
        String password = "silver";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
               
            }
        } catch (SQLException e) {
            System.out.println("Could not connect to the database.");
            e.printStackTrace();
        }
    }

	public static Connection getConnection() {
	
		return null;
	}
}
