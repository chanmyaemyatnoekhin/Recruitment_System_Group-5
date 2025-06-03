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

		System.out.println("user id " + request.getParameter("user_id"));
		String recruitmentIdParam = request.getParameter("recruitment_id");
		System.out.println("recruitment id in String : " + recruitmentIdParam);
		if (recruitmentIdParam == null || recruitmentIdParam.trim().isEmpty()) {
			request.setAttribute("error", "recruitment ID not provided.");
			request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp").forward(request, response);
			return;
		}

		int recruitmentId = 0;
		try {
			recruitmentId = Integer.parseInt(recruitmentIdParam);
			System.out.println("recruitment id in integer : " + recruitmentId);
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid recruitment ID.");
			request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp").forward(request, response);
			return;
		}

		try (Connection conn = dbConnection.getConnection()) {
			String sql = "SELECT  r.recruitment_id, r.full_name, r.phone_number, r.nrc_number, r.address, "
					+ "r.marital_status, r.gender, r.position, r.upload_resume, r.education_background, r.status, "
					+ "r.user_id,r.office_branch, r.admin_comment, u.email " + "FROM recruitment r "
					+ "INNER JOIN user u ON r.user_id = u.user_id " + "where recruitment_id = ? "
					+ "ORDER BY r.recruitment_id ASC";
//			String sql = "select * from recruitment where recruitment_id = ?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, recruitmentId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						// Set attributes for JSP
//						request.setAttribute("userId", userId);
						String fullName = rs.getString("full_name");
						request.setAttribute("recruitment_id", Integer.toString(rs.getInt("recruitment_id")));
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
						request.setAttribute("office_branch", rs.getString("office_branch"));
//						System.out.println("user Id :" + userId);
						System.out.println("full Name :" + fullName);

//						request.getSession().setAttribute("userId", userIdParam);
						request.getRequestDispatcher("NewReviewForm.jsp").forward(request, response);
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
		System.out.println("in doPost() of AdminReviewServlet");

//		String action = request.getParameter("action"); // check for the accept button first
//		if (action == null) {
//			System.out.println("action is null");
//			// check for the return button if accept is null
//		}
//		System.out.println("parameter action : " + action);

		System.out.println("full name : " + request.getParameter("fullName"));
		System.out.println("admin comment : " + request.getParameter("adminComment"));
		String userId = (String) request.getSession().getAttribute("userId");
		System.out.println("login user ID : " + userId);
		String adminComment = request.getParameter("adminComment");

		if (userId == null || userId.isEmpty()) {
			response.getWriter().println("User ID is missing.");
			return;
		}

		String status = null;
		String rcID = request.getParameter("recruitment_id"); // must be take out like that whether came from

		System.out.println("recruitment id : " + rcID);
		// hidden value or from parameter.
		if (rcID == null || rcID.isEmpty()) {
			response.getWriter().println("Recruitment ID is missing.");
			return;
		}
		// need to check whether rcID is null or not.s

//		if (action.equalsIgnoreCase("accept")) {
//			status = "APPROVE";
//			System.out.println("action is " + status);
//		} else if (action.equalsIgnoreCase("return")) {
//			status = "RETURN";
//			System.out.println("action is " + status);
//		} else {
//			response.getWriter().println("Invalid action.");
//			return;
//		}

		try (Connection conn = dbConnection.getConnection()) {
			String sql = "UPDATE recruitment SET status = ?, admin_comment = ? WHERE recruitment_id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, status);
				stmt.setString(2, adminComment); // For accept, it can be null
				stmt.setInt(3, Integer.parseInt(rcID));
				int rowsUpdated = stmt.executeUpdate();

				if (rowsUpdated > 0) {
					System.out.println("Admin Comment Update successfully.");
					response.sendRedirect("/UserList.jsp"); // redirect after success
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
