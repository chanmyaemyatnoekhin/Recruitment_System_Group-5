package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
					response.sendRedirect("adminDashboard.jsp"); // redirect after success
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
