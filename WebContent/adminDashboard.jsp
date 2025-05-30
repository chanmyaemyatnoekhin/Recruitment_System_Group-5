<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Review Applications</title>
      <%@ page session="true" %>
	<%
	    String userId = (String) session.getAttribute("userId");  
	    if (userId == null) {
	        response.sendRedirect("design.jsp");
	    }
	%>
</head>
<body>
    <h2>Admin Dashboard</h2>

    <!-- Dummy user list -->
    <table border="1" cellpadding="10">
        <tr>
            <th>User ID</th>
            <th>Full Name</th>
            <th>Action</th>
        </tr>
        <tr>
            <td>3</td>
            <td>Anna</td>
            <td>
                <form action="AdminDashboardServlet" method="get">
                    <input type="hidden" name="userId" value="3">
                    <input type="submit" value="Review Form">
                </form>
            </td>
        </tr>
        <tr>
            <td>user456</td>
            <td>Jane Smith</td>
            <td>
                <form action="AdminReviewRedirectServlet" method="get">
                    <input type="hidden" name="userId" value="user456">
                    <input type="submit" value="Review Form">
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
