package UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	private static final String URL = "jdbc:mysql://localhost:3307/recruitment_system";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static final String SELECT_ALL_USER = "SELECT r.full_name, r.status, u.email " + "FROM recruitment r "
			+ "INNER JOIN user u ON r.user_id = u.user_id";

	private static final String SEARCH_USERS = "SELECT r.full_name, r.status, u.email "
			+ "FROM recruitment r INNER JOIN user u ON r.user_id = u.user_id "
			+ "WHERE r.full_name LIKE ? OR u.email LIKE ?";
	private static final String DELETE_USER = "DELETE FROM user WHERE user_id = ?";

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					PreparedStatement stm = connection.prepareStatement(SELECT_ALL_USER);
					ResultSet rs = stm.executeQuery()) {

				while (rs.next()) {
					// int user_id = rs.getInt("user_id");
					String full_name = rs.getString("full_name");

					String email = rs.getString("email");
					String status = rs.getString("status");
					users.add(new User(full_name, email, status));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception details
		}
		return users;
	}

	public List<User> searchUsersByNameOrEmail(String searchQuery) throws SQLException {
		List<User> users = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		try {
			PreparedStatement ps = conn.prepareStatement(SEARCH_USERS);
			String searchPattern = "%" + searchQuery + "%";
			ps.setString(1, searchPattern);
			ps.setString(2, searchPattern);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				users.add(new User(rs.getString("full_name"), rs.getString("email"), rs.getString("status")

				));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

//		    public boolean deleteUser(int id)
//		    {
//		    	boolean rowdelete=false;
//		    	try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			             PreparedStatement stm = conn.prepareStatement(SEARCH_USERS)) {
//		    		stm.setInt(1,id);
//		    		rowdelete=stm.executeUpdate()>0;
//		    		
//		        } catch (SQLException e) {
//		            e.printStackTrace(); // Log the exception details
//		        }
//		    	return rowdelete;
//		    }
	public boolean deleteUser(int user_id) {
		boolean rowDeleted = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stm = conn.prepareStatement(DELETE_USER)) {

			// Set the recruitment_id to the prepared statement
			stm.setInt(1, user_id);

			// Execute the deletion
			rowDeleted = stm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace(); // Log SQL errors
		}
		return rowDeleted;
	}

}
