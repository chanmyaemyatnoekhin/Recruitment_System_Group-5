<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Forgot Password</title>
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
      width: 320px;
      color: white;
    }

    .box h2 {
      margin-bottom: 30px;
      font-size: 20px;
      text-align: center;
    }

    .form-group {
      margin-bottom: 20px;
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

    .error, .success {
      font-size: 13px;
      margin-top: 8px;
      text-align: center;
    }

    .error {
      color: red;
    }

    .success {
      color: #4caf50;
    }
  </style>
</head>
<body>
  <div class="box">
    <h2>Forgot Password</h2>
    <form action="${pageContext.request.contextPath}/ForgetPasswordServlet" method="post">

      <div class="form-group">
        <label for="email">Enter your email</label>
        <input type="email" id="email" name="email" placeholder="Email"
               value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>
      </div>

      <% 
        String message = (String) request.getAttribute("message");
        String status = (String) request.getAttribute("status");
        if (message != null) {
      %>
        <div class="<%= "success".equals(status) ? "success" : "error" %>">
          <%= message %>
        </div>
      <% } %>

      <button type="submit">Send OTP</button>
    </form>

    <div class="back-link">
      <a href="design.jsp">Back to Login</a>
    </div>
  </div>
</body>
</html>
