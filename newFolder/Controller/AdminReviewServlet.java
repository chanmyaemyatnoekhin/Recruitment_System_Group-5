package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.dbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminReviewServlet
 */
@WebServlet("/AdminReviewServlet")
public class AdminReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in get method.");

		String userIdParam = request.getParameter("user_id");
		System.out.println("user id in String : " + userIdParam);
		if (userIdParam == null || userIdParam.trim().isEmpty()) {
			request.setAttribute("error", "User ID not provided.");
			request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp").forward(request, response);
			return;
		}

		int userId = 0;
		try {
			userId = Integer.parseInt(userIdParam);
			System.out.println("user id in integer : " + userId);
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid User ID.");
			request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp").forward(request, response);
			return;
		}

		try (Connection conn = dbConnection.getConnection()) {
			String sql = "SELECT r.*, u.email FROM recruitment r INNER JOIN user u ON r.user_id = u.user_id WHERE r.user_id = ?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, userId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						// Set attributes for JSP
//						request.setAttribute("userId", userId);
						String fullName = rs.getString("full_name");
						request.setAttribute("fullName", fullName);
						request.setAttribute("email", rs.getString("email"));
						request.setAttribute("phone", rs.getString("phone_number"));
						request.setAttribute("nrc", rs.getString("nrc_number"));
						request.setAttribute("address", rs.getString("address"));
						request.setAttribute("maritalStatus", rs.getString("marital_status"));
						request.setAttribute("gender", rs.getString("gender"));
						request.setAttribute("education", rs.getString("education_background"));
						request.setAttribute("position", rs.getString("position"));
						request.setAttribute("recruitmentStatus", rs.getString("status"));
						request.setAttribute("adminComment", rs.getString("admin_comment"));
//						System.out.println("user Id :" + userId);
						System.out.println("full Name :" + fullName);

//						request.getSession().setAttribute("userId", userIdParam);
						request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp").forward(request, response);
					} else {
						request.setAttribute("error", "No recruitment data found for this user.");
						request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp").forward(request, response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error: " + e.getMessage());
			request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action"); // accept or return
//		String userId = request.getParameter("userId");
		String userId = (String) request.getSession().getAttribute("userId");
		String adminComment = request.getParameter("adminComment");

		if (userId == null || userId.isEmpty()) {
			response.getWriter().println("User ID is missing.");
			return;
		}

		String status = null;

		if ("accept".equalsIgnoreCase(action)) {
			status = "APPROVE";
		} else if ("return".equalsIgnoreCase(action)) {
			status = "RETURN";
		} else {
			response.getWriter().println("Invalid action.");
			return;
		}

		try (Connection conn = dbConnection.getConnection()) {
			String sql = "UPDATE recruitment SET status = ?, admin_comment = ? WHERE user_id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, status);
				stmt.setString(2, adminComment); // For accept, it can be null
				stmt.setString(3, userId);
				int rowsUpdated = stmt.executeUpdate();

				if (rowsUpdated > 0) {
					response.sendRedirect("UserList.jsp"); // redirect after success
				} else {
					response.getWriter().println("No record updated.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		}
	}

}
