package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserDAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() {
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		String contextPath = request.getContextPath();
		String servletPath = path.substring(contextPath.length());
		System.out.println("Servlet path: " + path);
		System.out.println("Context path: " + contextPath);
		System.out.println("Servlet path: " + servletPath);

		try {
//			if (servletPath.startsWith("/delete")) {
//				System.out.println("Calling deleteUser method");
//				deleteUser(request, response);
//			} else {
			System.out.println("Calling userList method");
			userList(request, response);
//			}
		} catch (Exception e) {
			System.out.println("User Servlet Error: " + e.getMessage());
		}
	}

//	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			String idStr = request.getParameter("recruitment_id");
//
//			if (idStr != null && !idStr.trim().isEmpty()) {
//				try {
//					int recruitmentId = Integer.parseInt(idStr);
//					boolean deleted = userDAO.deleteUser(recruitmentId);
//
//					if (deleted) {
//						System.out.println("User Deleted Successfully with recruitment_id: " + recruitmentId);
//					} else {
//						System.out.println("Failed to delete user with recruitment_id: " + recruitmentId);
//					}
//				} catch (NumberFormatException e) {
//
//					System.out.println("Invalid recruitment_id format: " + idStr);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		response.sendRedirect(request.getContextPath() + "/UserList");
//	}

	private void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String searchQuery = request.getParameter("searchQuery");
		List<User> users;
		if (searchQuery == null || searchQuery.trim().isEmpty()) {
			users = userDAO.selectAllUsers();
		} else {
			users = userDAO.searchUsersByNameOrEmail(searchQuery.trim());
		}

		if (users == null) {
			users = new ArrayList<>();
		}

		request.setAttribute("UserList", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UserList.jsp");
		dispatcher.forward(request, response);
	}
}
