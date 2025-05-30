package Controller;

import Config.dbConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResetPasswordServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confirmPassword");
        String email = (String) session.getAttribute("email");
        RequestDispatcher dispatcher = request.getRequestDispatcher("design.jsp");

        if (email == null) {
            request.setAttribute("status", "noSessionEmail");
            dispatcher.forward(request, response);
            return;
        }

        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            try (Connection con = dbConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement("UPDATE user SET password = ? WHERE email = ?")) {

                pst.setString(1, newPassword);
                pst.setString(2, email);

                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    request.setAttribute("status", "resetSuccess");
                } else {
                    request.setAttribute("status", "resetFailed");
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("status", "error");
            }

        } else {
            request.setAttribute("status", "mismatch");
        }

        dispatcher.forward(request, response);
    }
}
