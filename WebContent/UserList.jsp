<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - User List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <style>
  /* Your CSS from before */
  body {
    font-family: Arial, sans-serif;
    background-color: #f3f4f6;
    margin: 0;
    padding: 0;
  }
  .header {
    background-color: #5a2d82;
    color: white;
    padding: 20px;
    font-size: 24px;
    font-weight: bold;
    text-align:center;
  }
  .container {
    margin: 30px auto;
    width: 80%;
    background-color: white;
    padding: 20px;
    border-radius: 10px;
  }
  h2 {
    color: #5a2d82;
  }
  input[type="text"] {
    padding: 10px;
    width: 70%;
    border-radius: 5px;
    border: 1px solid #ccc;
  }
  button {
    padding: 10px 20px;
    background-color: #5a2d82;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  table {
    width: 80%;
    border-collapse: collapse;
    margin-top: 20px;
  }
  th, td {
    padding: 12px;
    border-bottom: 1px solid #ddd;
    text-align: left;
  }
  th {
    background-color: #5a2d82;
    color:white;
  }
  .status-approve {
    color: green;
    font-weight: bold;
  }
  .status-reject {
    color: red;
    font-weight: bold;
  }
  .action-btn {
    color: red;
    cursor: pointer;
  }
</style>
</head>
<body>
    <div class="header">Admin Dashboard - User List</div>
    <div class="container">
        <h2>Users</h2>
		

        

			<form method="get" action="UserListServlet">
			    <input type="text" name="searchQuery" placeholder="Search users by full name or email" value="${param.searchQuery }">
			    <button type="submit">Search</button>
			</form>
		
			
			<p>List of all registered users with their approval status</p>
			
			
			        <table>
			        
			            <tr>
			                <th>Full Name</th>
			                <th>Email</th>
			                <th>Status</th>
			                <th>Action</th>
			            </tr>
							<%
					    List<User> list = (List<User>) request.getAttribute("UserList");
					
					    if (list != null && !list.isEmpty()) {
					        for (User user : list) {
					%>
					            <tr>
					                <td><%= user.getFullname() %></td>
					                <td><%= user.getEmail() %></td>
					                <td style="color: <%= "reject".equalsIgnoreCase(user.getStatus()) ? "red" : "green" %>;">
					                  
								    <a href="Admin.jsp?status=<%= user.getStatus() %>" style="color: inherit; text-decoration: none;">
								        <%= user.getStatus() != null ? user.getStatus() : "No Status" %>
								    </a>

					                </td>
					                
					            </tr>
					<%
					        }
					    } else {
					%>
					        <tr>
					            <td colspan="4">No users found.</td>
					        </tr>
					<%
					    }
					%>

			        </table>
			

    </div>
</body>
</html> 