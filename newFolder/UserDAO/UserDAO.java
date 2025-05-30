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

//		    private static final String SELECT_ALL_USER =
//		            "SELECT r.full_name, r.status, u.email " +
//		            "FROM recruitment r " +
//		            "INNER JOIN user u ON r.user_id = u.user_id";

//		    private static final String SEARCH_USERS = "SELECT r.full_name, r.status, u.email " +
//			                     "FROM recruitment r INNER JOIN user u ON r.user_id = u.user_id " +
//			                     "WHERE r.full_name LIKE ? OR u.email LIKE ?";
//		    private static final String DELETE_USER = "DELETE FROM user WHERE user_id = ?";

	private static final String SELECT_ALL_USER = "SELECT  r.full_name, r.phone_number, r.nrc_number, r.address, "
			+ "r.marital_status, r.gender, r.position, r.upload_resume, r.education_background, r.status, "
			+ "r.user_id,r.office_branch, r.admin_comment, u.email " + "FROM recruitment r "
			+ "INNER JOIN user u ON r.user_id = u.user_id " + "ORDER BY r.recruitment_id ASC";

	private static final String SEARCH_USERS = "SELECT r.full_name, r.phone_number, r.nrc_number, r.address, r.marital_status, r.gender, "
			+ "r.position, r.upload_resume, r.education_background, r.status, r.user_id, r.office_branch, "
			+ "r.admin_comment, u.email " + "FROM recruitment r INNER JOIN user u ON r.user_id = u.user_id "
			+ "WHERE r.full_name LIKE ? OR u.email LIKE ?";

	String DELETE_USER = "DELETE FROM user WHERE user_id = ?";

	// Method to select all users and recruitment data
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					PreparedStatement stm = connection.prepareStatement(SELECT_ALL_USER);
					ResultSet rs = stm.executeQuery()) {

				while (rs.next()) {
					String fullname = rs.getString("full_name");
					String phone_number = rs.getString("phone_number");
					String nrc_number = rs.getString("nrc_number");
					String address = rs.getString("address");
					String marital_status = rs.getString("marital_status");
					String gender = rs.getString("gender");
					String position = rs.getString("position");
					String upload_resume = rs.getString("upload_resume");
					String education_background = rs.getString("education_background");
					String status = rs.getString("status");
					int user_id = rs.getInt("user_id");
					String office_branch = rs.getString("office_branch");
					String admin_comment = rs.getString("admin_comment");
					String email = rs.getString("email");
					// Add a new User object to the list
					users.add(new User(fullname, phone_number, nrc_number, address, marital_status, gender, position,
							upload_resume, education_background, status, user_id, office_branch, admin_comment, email));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception details
		}
		return users;
	}

	public List<User> searchUsersByNameOrEmail(String searchQuery) throws SQLException {
		List<User> users = new ArrayList<>();
		String searchPattern = "%" + searchQuery + "%";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(SEARCH_USERS)) {
			ps.setString(1, searchPattern);
			ps.setString(2, searchPattern);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					users.add(new User(rs.getString("full_name"), rs.getString("phone_number"),
							rs.getString("nrc_number"), rs.getString("address"), rs.getString("marital_status"),
							rs.getString("gender"), rs.getString("position"), rs.getString("upload_resume"),
							rs.getString("education_background"), rs.getString("status"), rs.getInt("user_id"),
							rs.getString("office_branch"), rs.getString("admin_comment"), rs.getString("email")

					));
				}
			}
		}
		return users;
	}

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
