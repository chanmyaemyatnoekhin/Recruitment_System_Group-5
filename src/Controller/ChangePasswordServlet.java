package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Config.dbConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Get session and user ID
        HttpSession session = request.getSession(false); // false to avoid creating a new session
        String userId = (session != null) ? (String) session.getAttribute("userId") : null;

        if (userId == null) {
            // User is not logged in; redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve parameters from the form
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        boolean hasError = false;
        String errorMessage = "";

        // Basic validation
        if (currentPassword == null || currentPassword.trim().isEmpty() ||
            newPassword == null || newPassword.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            hasError = true;
            errorMessage = "All fields are required.";
        } else if (!newPassword.equals(confirmPassword)) {
            hasError = true;
            errorMessage = "New password and confirmation do not match.";
        }

        if (hasError) {
            request.setAttribute("passwordError", errorMessage);
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            return;
        }

        // Database check and update
        try (Connection conn = dbConnection.getConnection()) {
            // Step 1: Verify current password
            String checkSql = "SELECT password FROM user WHERE user_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, userId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        String dbPassword = rs.getString("password");
                        if (!dbPassword.equals(currentPassword)) {
                            request.setAttribute("passwordError", "Current password is incorrect.");
                            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
                            return;
                        }
                    } else {
                        request.setAttribute("passwordError", "User not found.");
                        request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
                        return;
                    }
                }
            }

            // Step 2: Update new password
            String updateSql = "UPDATE user SET password = ? WHERE user_id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, userId);
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    // Success - redirect or message
                    response.sendRedirect("recruitmentForm.jsp?message=Password changed successfully.");
                } else {
                    request.setAttribute("passwordError", "Failed to update password.");
                    request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("passwordError", "An error occurred while changing the password.");
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
        }
    }
}
