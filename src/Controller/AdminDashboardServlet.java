package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.dbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminDashboardServlet
 */
@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDashboardServlet() {
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
//		doGet(request, response);
//		request.setAttribute("fullName", fullName);
//		request.setAttribute("email", email);
//		request.setAttribute("phone", phone);
//		request.setAttribute("nrc", nrc);
//		request.setAttribute("gender", gender);
//		request.setAttribute("position", position);

		String loginUserID = (String) request.getSession().getAttribute("userId");
		if (loginUserID == null) {
			System.out.println("login user id is missing");
		}
		boolean foundUser = false;
		try (Connection con = dbConnection.getConnection()) {
			if (con == null) {
				response.getWriter().println("Database connection failed.");
				return;
			}
			String query = "select * from recruitment where user_id = ?";

			try (PreparedStatement pstmt = con.prepareStatement(query)) {
				pstmt.setString(1, loginUserID);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					foundUser = true;
					request.setAttribute("fullName", rs.getString("full_name"));
					request.setAttribute("phone", rs.getString("phone_number"));
					request.setAttribute("nrc", rs.getString("nrc_number"));
					request.setAttribute("address", rs.getString("address"));
					request.setAttribute("maritalStatus", rs.getString("marital_status"));
					request.setAttribute("gender", rs.getString("gender"));
					request.setAttribute("position", rs.getString("position"));
					request.setAttribute("education", rs.getString("education_background"));

					String queryUser = "select * from user where user_id = ?";
					try (PreparedStatement pstmtUser = con.prepareStatement(queryUser)) {
						pstmtUser.setString(1, loginUserID);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//					RequestDispatcher dispatcher = request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp");
//					dispatcher.forward(request, response);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (foundUser) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminReviewRecruitmentForm.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().println("No recruitment record found for user ID: " + loginUserID);
		}

	}

}
