package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class SignUpServlet
 */

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    // Update these constants with your DB details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/recruitment_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

   
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = "USER"; // Set role default here

            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                // Load JDBC driver and connect (adjust if needed)
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitment_system", "root", "");

                String sql = "INSERT INTO user (name, email, password, role) VALUES (?, ?, ?, 'USER')";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);


                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    response.sendRedirect("login.jsp");
                } else {
                    response.getWriter().println("Sign up failed. Try again.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            } finally {
                try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
                try { if (conn != null) conn.close(); } catch (Exception ignored) {}
            }
        }
    }




