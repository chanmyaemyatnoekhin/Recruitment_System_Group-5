package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	private static final String URL = "jdbc:mysql://localhost:3307/recruitment_system";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
		} catch (ClassNotFoundException e) {
			System.err.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbConnection.getConnection();

			if (connection != null && !connection.isClosed()) {
				System.out.println("connection successfully.");
			} else {
				System.out.println("Connection Fail.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
