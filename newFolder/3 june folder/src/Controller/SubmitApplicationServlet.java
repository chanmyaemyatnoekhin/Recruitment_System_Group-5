package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Config.dbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class SubmitApplicationServlet
 */
@WebServlet("/SubmitApplicationServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class SubmitApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitApplicationServlet() {
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

		String uploadPath = "C:\\Group-5-Recruitment-System\\NewRecruitmentSystem\\uploadedFiles";
//		String loginUserID = request.getParameter("userId");

		String loginUserID = (String) request.getSession().getAttribute("userId");
		if (loginUserID == null) {
			System.out.println("login user id is missing");
		}

		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String nrc = request.getParameter("nrc");
		String address = request.getParameter("address");
		String maritalStatus = request.getParameter("maritalStatus");
		String gender = request.getParameter("gender");
		String position = request.getParameter("position");

		Part resumePart = request.getPart("resume");
		// change start
		String savedFilePath = "No Uploaded File";
		if (resumePart != null && resumePart.getSize() > 0) {
			String resumeFileName = resumePart.getSubmittedFileName();
			File uploadFile = new File(uploadPath);
			if (!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			savedFilePath = uploadPath + File.separator + fullName + "_" + System.currentTimeMillis() + "_"
					+ resumeFileName;
			resumePart.write(savedFilePath);
		}
		// change end

		String education = request.getParameter("education");

		String adminComment = request.getParameter("adminComment");
		if (adminComment != null && !adminComment.trim().isEmpty()) {
			// do something
		}

		PrintWriter pw = response.getWriter();
		pw.println("full name :" + fullName);

		try (Connection con = dbConnection.getConnection()) {
			if (con == null) {
				response.getWriter().println("Database connection failed.");
				return;
			}
			String query = "INSERT INTO recruitment (full_name, phone_number, nrc_number, address, marital_status, gender, position, upload_resume, education_background, status, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstmt = con.prepareStatement(query)) {
				pw.println("full name in try :" + fullName);
				pstmt.setString(1, fullName);
				pstmt.setString(2, phone);
				pstmt.setString(3, nrc);
				pstmt.setString(4, address);
				pstmt.setString(5, maritalStatus);
				pstmt.setString(6, gender);
				pstmt.setString(7, position);
				pstmt.setString(8, savedFilePath);
				pstmt.setString(9, education);
				pstmt.setString(10, "PENDING");
				pstmt.setInt(11, Integer.parseInt(loginUserID));

				int rowsInserted = pstmt.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("insert successful.");
				} else {
					System.out.println("insert fail.");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("thankyou.jsp");
	}

}
