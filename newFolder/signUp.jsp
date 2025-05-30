<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #5588EE;
            font-family: Arial, sans-serif;
        }

        .signup-container {
            width: 350px;
            margin: 100px auto;
            background-color: #1565C0;
            padding: 20px;
            border-radius: 25px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            color: white;
        }

        .signup-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .signup-container label {
            display: block;
            margin-top: 15px;
            margin-left:30px;
        }

        .signup-container input[type="text"],
        .signup-container input[type="email"],
        .signup-container input[type="password"],
        .signup-container input[type="submit"] {
            width: 85%;
            padding: 15px;
            margin-left:30px;
            margin-top: 10px;
            border: none;
            border-radius: 10px;
            background-color: #5588EE;
            color: white;
            font-size: 16px;
            box-sizing: border-box;
        }

        .signup-container input[type="submit"] {
            margin-top: 20px;
            background-color: #007BFF;
            cursor: pointer;
        }

        .signup-container input[type="submit"]:hover {
            background-color: #002f66;
        }

        .login-link {
            margin-top: 15px;
            text-align: center;
        }

        .login-link a {
            color: #cce5ff;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
        
		.error {
	      color: red;
	      font-size: 12px;
	      margin-top: 4px;
	      margin-left:30px;
	    }
	    
        .input-error {
		    border: 2px solid #FF4D4D !important;
		}
        
    </style>
    <script>
    function validateForm() {
        let name = document.getElementById("txtName");
        let email = document.getElementById("txtEmail");
        let password = document.getElementById("txtPassword");
        let valid = true;

        // Reset error styles and messages
        name.classList.remove("input-error");
        email.classList.remove("input-error");
        password.classList.remove("input-error");

        document.getElementById("nameError").innerText = "";
        document.getElementById("emailError").innerText = "";
        document.getElementById("passwordError").innerText = "";

        if (name.value.trim() === "") {
            document.getElementById("nameError").innerText = "Name is required";
            name.classList.add("input-error");
            valid = false;
        }

        const emailValue = email.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (emailValue === "") {
            document.getElementById("emailError").innerText = "Email is required";
            email.classList.add("input-error");
            valid = false;
        } else if (!emailRegex.test(emailValue)) {
            document.getElementById("emailError").innerText = "Invalid email format";
            email.classList.add("input-error");
            valid = false;
        }

        const passwordValue = password.value.trim();
        const passwordRegex = /^(?=.*[A-Z])(?=.*[\W_]).{8,}$/;

        if (passwordValue === "") {
            document.getElementById("passwordError").innerText = "Password is required";
            password.classList.add("input-error");
            valid = false;
        } else if (!passwordRegex.test(passwordValue)) {
            document.getElementById("passwordError").innerText = "Password must be at least 8 characters, at least 1 uppercase letter, and 1 special character.";
            password.classList.add("input-error");
            valid = false;
        }

        return valid;
    }
    </script>
</head>
<body>
<div class="signup-container">
    <h2>Sign Up</h2>
    <form action="SignUpServlet" method="post" onsubmit="return validateForm()">
        <label for="name">Name</label>
        <input type="text" id="txtName" name="name" placeholder="John Doe" />
        <div id="nameError" class="error"></div>

        <label for="email">Email</label>
        <input type="email" id="txtEmail" name="email" placeholder="you@example.com" />
        <div id="emailError" class="error"></div>

        <label for="password">Password</label>
        <input type="password" id="txtPassword" name="password" placeholder="Password" />
        <div id="passwordError" class="error"></div>

        <input type="submit" id="btnSignUp" value="Sign Up" />
    </form>
    <div class="login-link">
        Already have an account? <a href="design.jsp">Login</a>
    </div>
</div>
</body>
</html>
