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

@WebServlet("/LogIn_Servlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		boolean hasError = false;

		if (email == null || email.trim().isEmpty()) {
			request.setAttribute("emailError", "Email is required");
			hasError = true;
		}

		if (password == null || password.trim().isEmpty()) {
			request.setAttribute("passwordError", "Password is required");
			hasError = true;
		}

		if (hasError) {
			request.getRequestDispatcher("design.jsp").forward(request, response);
			return;
		}

		// Database authentication
		try (Connection conn = dbConnection.getConnection()) {
			String sql = "SELECT * FROM user WHERE email = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, email);
				try (ResultSet rs = stmt.executeQuery()) {
					if (!rs.next()) {
						// Email doesn't exist
						request.setAttribute("emailError", "Email not found");
						request.getRequestDispatcher("design.jsp").forward(request, response);
					} else {
						String dbPassword = rs.getString("password");
						if (!dbPassword.equals(password)) {
							// Password is incorrect but email exists
							request.setAttribute("passwordError", "Password is incorrect");
							request.setAttribute("txtEmail", email); // retain entered email in form
							request.getRequestDispatcher("design.jsp").forward(request, response);
						} else {
							// Success login
							String role = rs.getString("role");
							String userID = rs.getString("user_id");
//        	                    request.getSession().setAttribute("userEmail", email);
							request.getSession().setAttribute("userId", userID);
							if ("admin".equalsIgnoreCase(role)) {
								response.sendRedirect("UserList.jsp");
							} else {
								request.getSession().setAttribute("loggedInEmail", email);
								response.sendRedirect("RecruitmentForm.jsp");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("design.jsp");
	}
}
