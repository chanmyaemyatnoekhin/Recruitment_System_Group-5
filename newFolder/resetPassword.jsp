<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Reset Password</title>
  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      font-family: Arial, sans-serif;
      background-color: #1e68d4;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .box {
      background-color: rgba(255, 255, 255, 0.05);
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
      width: 300px;
      color: white;
    }

    .box h2 {
      margin-bottom: 30px;
      font-size: 20px;
      text-align: center;
    }

    .form-group {
      margin-bottom: 20px;
      text-align: left;
    }

    .form-group label {
      display: block;
      margin-bottom: 6px;
      font-size: 14px;
      color: #fff;
    }

    .form-group input {
      width: 100%;
      padding: 12px;
      border: none;
      border-radius: 8px;
      background-color: rgba(255, 255, 255, 0.1);
      color: white;
      font-size: 14px;
    }

    .form-group input::placeholder {
      color: #ccc;
    }

    .box button {
      width: 100%;
      padding: 12px;
      background-color: #3b82f6;
      border: none;
      border-radius: 8px;
      color: white;
      font-weight: bold;
      font-size: 15px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .box button:hover {
      background-color: #2563eb;
    }

    .back-link {
      margin-top: 15px;
      font-size: 14px;
      text-align: center;
    }

    .back-link a {
      color: #ccc;
      text-decoration: none;
    }

    .back-link a:hover {
      text-decoration: underline;
    }

    .error {
      color: red;
      font-size: 12px;
      margin-top: 4px;
    }

    .success {
      color: #4caf50;
      font-size: 12px;
      margin-top: 4px;
      text-align: center;
    }
  </style>
</head>
<body>
  <div class="box">
    <h2>Create New Password</h2>
    <form action="ResetPasswordServlet" method="post">
      <div class="form-group">
        <label for="password">New Password</label>
        <input type="password" id="password" name="password" placeholder="Enter new password" required>
      </div>
      <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm new password" required>
      </div>

      <div class="<%= request.getAttribute("status") != null && request.getAttribute("status").equals("success") ? "success" : "error" %>">
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
      </div>

      <button type="submit">Reset Password</button>
    </form>

    <div class="back-link">
      <a href="design.jsp">Back to Login</a>
    </div>
  </div>
</body>
</html>
