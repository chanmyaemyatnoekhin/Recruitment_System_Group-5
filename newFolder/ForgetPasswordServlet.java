package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;

import Config.dbConnection; 

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        RequestDispatcher dispatcher = null;
        HttpSession mySession = request.getSession();

        if (email != null && !email.isEmpty()) {
            boolean emailExists = false;

            try (Connection conn = dbConnection.getConnection()) {
                String query = "SELECT * FROM user WHERE email = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                emailExists = rs.next(); 
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Database error: " + e.getMessage());
                request.setAttribute("status", "error");
                dispatcher = request.getRequestDispatcher("forgetPassword.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (!emailExists) {
                request.setAttribute("message", "Email not found in our records.");
                request.setAttribute("status", "error");
                dispatcher = request.getRequestDispatcher("forgetPassword.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Email exists - send OTP
            int otpvalue = new Random().nextInt(1255650);

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("kyiminy2k@gmail.com", "fwchvwwjmbbvpenk");
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("kyiminy2k@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                message.setSubject("OTP for Password Reset");


                String htmlContent = "<html><body>"

                        + "<h2>OTP Verification</h2>"

                        + "<p>Hello,</p>"

                        + "<p>Your OTP is: <strong>" + otpvalue + "</strong></p>"

                        + "<p>If you did not request this OTP, please ignore this message.</p>"

                        + "</body></html>";

                message.setContent(htmlContent, "text/html");

                 
                Transport.send(message);
                System.out.println("Email sent successfully");
                
                request.setAttribute("message", "OTP is sent to your email");
                request.setAttribute("status", "success");
                mySession.setAttribute("otp", otpvalue);
                mySession.setAttribute("email", email);
                dispatcher = request.getRequestDispatcher("verifyOTP.jsp");
                dispatcher.forward(request, response);

            } catch (MessagingException e) {
                e.printStackTrace();
                request.setAttribute("message", "Failed to send email: " + e.getMessage());
                request.setAttribute("status", "error");
                dispatcher = request.getRequestDispatcher("forgetPassword.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            request.setAttribute("message", "Please enter your email.");
            request.setAttribute("status", "error");
            dispatcher = request.getRequestDispatcher("forgetPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}
