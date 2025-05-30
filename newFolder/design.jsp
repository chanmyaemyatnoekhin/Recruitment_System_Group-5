<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <script>
    function validateForm() {
        let email = document.getElementById("txtEmail");
        let password = document.getElementById("txtPassword");
        let valid = true;

        // Reset error styles and messages
        email.classList.remove("input-error");
        password.classList.remove("input-error");
        
        document.getElementById("emailError").innerText = "";
        document.getElementById("passwordError").innerText = "";

        if (email.value.trim() === "") {
            document.getElementById("emailError").innerText = "Email is required";
            email.classList.add("input-error");
            valid = false;
        }

        if (password.value.trim() === "") {
            document.getElementById("passwordError").innerText = "Password is required";
            password.classList.add("input-error");
            valid = false;
        }

        return valid;
    }
  </script>
  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      font-family: Arial, sans-serif;
      background-color: #5588EE;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .login-box {
      background-color: #1565C0;
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
      width: 300px;
      color: white;
    }

    .login-box h2 {
      margin-bottom: 30px;
      font-size: 20px;
      text-align: center;
    }

    .form-group {
      margin-bottom: 20px;
      text-align: left;
      position: relative;
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

    .login-box button {
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

    .login-box button:hover {
      background-color: #2563eb;
    }

    .bottom-links {
      margin-top: 15px;
      font-size: 14px;
      display: flex;
      justify-content: space-between;
      color: #ccc;
    }

    .bottom-links a {
      color: #ccc;
      text-decoration: none;
    }

    .bottom-links a:hover {
      text-decoration: underline;
    }

    .error {
      color: red;
      font-size: 12px;
      margin-top: 4px;
    }

    .input-error {
      border: 2px solid #FF4D4D !important;
    }
  </style>
</head>
<body>
  <div class="login-box">
    <h2>Login to Your Account</h2>
    <form action="LogIn_Servlet" method="post" onsubmit="return validateForm()">
      <div class="form-group">
        <label for="txtEmail">Email</label>
       <input type="text" name="txtEmail" 
       value="<%= request.getAttribute("txtEmail") != null ? request.getAttribute("txtEmail") : "" %>">
        <div class="error">
                <%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %>
            </div>
      </div>

      <div class="form-group">
        <label for="txtPassword">Password</label>
        <input type="password" id="txtPassword" name="txtPassword" placeholder="Password">
        <!-- <div id="passwordError" class="error"></div> -->
        <div class="error">
                <%= request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : "" %>
            </div>
      </div>

      <div class="error" style="text-align:center; margin-bottom: 10px;">
        <%= request.getAttribute("loginError") != null ? request.getAttribute("loginError") : "" %>
      </div>

      <button type="submit">Sign In</button>
    </form>

    <div class="bottom-links">
      <a href="forgetPassword.jsp">Forgot password?</a>
      <a href="signUp.jsp">Sign up</a>
    </div>
  </div>
</body>
</html>
