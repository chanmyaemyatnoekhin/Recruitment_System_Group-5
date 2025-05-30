<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Review Recruitment Form</title>
    <style>
        /* Simple styling */
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #7b5eda, #5e4bb6);
            padding: 20px;
        }
       .container {
            background: white;
            width: 400px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 10px;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input[type="text"], textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        textarea {
            resize: vertical;
        }
        button {
            margin-top: 20px;
           /* margin-left: 30px;*/
            width: 100%;
            padding: 10px;
            background-color: #5e4bb6;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Admin Review Recruitment Form</h2>

    <form action="AdminReviewServlet" method="post" enctype="multipart/form-data">
        <!-- Hidden userId -->
        <input type="hidden" name="userId" value="<%= request.getAttribute("userId") != null ? request.getAttribute("userId") : "" %>">

        <label for="fullName">Full Name *</label>
        <input type="text" id="fullName" name="fullName" 
               value="<%= request.getAttribute("fullName") != null ? request.getAttribute("fullName") : "" %>" required>

<label for="email">Email *</label> 
<input type="email" id="email" name="email" 
value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
 placeholder="example@domain.com" 
       value="<%= session.getAttribute("loggedInEmail") != null ? session.getAttribute("loggedInEmail") : "" %>" 
       readonly required>
<span id="emailError" style="color:red;"></span>

        <label for="phone">Phone Number *</label>
        <input type="text" id="phone" name="phone" 
               value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>" required>

        <label for="nrc">NRC Number *</label>
        <input type="text" id="nrc" name="nrc" 
               value="<%= request.getAttribute("nrc") != null ? request.getAttribute("nrc") : "" %>" required>

        <label for="address">Address</label>
        <textarea id="address" name="address" rows="3"><%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %></textarea>

        <label for="maritalStatus">Marital Status *</label>
        <select id="maritalStatus" name="maritalStatus" required>
            <option value="">Select</option>
            <option value="SINGLE" <%= "SINGLE".equals(request.getAttribute("maritalStatus")) ? "selected" : "" %>>Single</option>
            <option value="MARRIED" <%= "MARRIED".equals(request.getAttribute("maritalStatus")) ? "selected" : "" %>>Married</option>
        </select>

        <label for="gender">Gender *</label>
        <select id="gender" name="gender" required>
            <option value="">Select</option>
            <option value="MALE" <%= "MALE".equals(request.getAttribute("gender")) ? "selected" : "" %>>Male</option>
            <option value="FEMALE" <%= "FEMALE".equals(request.getAttribute("gender")) ? "selected" : "" %>>Female</option>
        </select>

        <label for="position">Position Applied For *</label>
        <input type="text" id="position" name="position" 
               value="<%= request.getAttribute("position") != null ? request.getAttribute("position") : "" %>" required>

        <label for="education">Education Background</label>
        <input type="text" id="education" name="education" 
               value="<%= request.getAttribute("education") != null ? request.getAttribute("education") : "" %>">

        <!-- Admin comment textarea, pre-filled -->
        <label for="adminComment">Admin Comment (Please explain issues)</label>
        <textarea name="adminComment" id="adminComment" rows="4" placeholder="Enter comment for the applicant if corrections are needed."><%= request.getAttribute("adminComment") != null ? request.getAttribute("adminComment") : "" %></textarea>

		<button type="submit" name="action" value="accept">Accept</button>
<button type="submit" name="action" value="return">Return</button>
    </form>
    
     <a href="javascript:void(0);" onclick="comfirmLogout()">Log Out</a>
</div>
</body>
</html>
