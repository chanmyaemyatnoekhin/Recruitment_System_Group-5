package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	
	private static Connection con = null;
	private static String conStr = "jdbc:mysql://localhost:3306/recruitment_system";
	private static String userName = "root";
	private static String password = "";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(conStr, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Connection connection = dbConnection.getConnection();
		try {
			if (connection != null || !connection.isClosed()) {
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
