<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <style>
        body {
            background: linear-gradient(135deg, #7b5eda, #5e4bb6);
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            background: white;
            width: 400px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 10px;
        }
        h2 {
            text-align: center;
            color: #5e4bb6;
        }
        label {
            display: block;
            margin-top: 15px;
        }
        .password-container {
            position: relative;
            margin-top: 5px;
        }
        .password-container input {
            width: 100%;
            padding: 10px 40px 10px 10px; /* Space for the icon */
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        .toggle-password {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            cursor: pointer;
            font-size: 18px;
            color: #5e4bb6;
            padding: 0;
        }
        button.submit-btn {
            margin-top: 20px;
            margin-left: 30px;
            width: 35%;
            padding: 10px;
            background-color: #5e4bb6;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
    <!-- Include FontAwesome for eye icons -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <script>
        function validatePasswordForm() {
            var current = document.getElementById("currentPassword").value;
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var errorMessage = document.getElementById("passwordError");

            if (current === "" || newPassword === "" || confirmPassword === "") {
                errorMessage.innerText = "All fields are required.";
                return false;
            }

            if (newPassword !== confirmPassword) {
                errorMessage.innerText = "New password and confirmation do not match.";
                return false;
            }

            errorMessage.innerText = "";
            return true;
        }

        function cancelForm() {
            window.location.href = "recruitmentForm.jsp"; // adjust as needed
        }

        function togglePassword(id) {
            const input = document.getElementById(id);
            const icon = document.querySelector(`[data-toggle="${id}"] i`);
            if (input.type === "password") {
                input.type = "text";
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            } else {
                input.type = "password";
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Change Password</h2>
        <form action="ChangePasswordServlet" method="post" onsubmit="return validatePasswordForm();">
            <label for="currentPassword">Current Password *</label>
            <div class="password-container">
                <input type="password" id="currentPassword" name="currentPassword" required>
                <button type="button" class="toggle-password" data-toggle="currentPassword" onclick="togglePassword('currentPassword')">
                    <i class="fas fa-eye"></i>
                </button>
            </div>

            <label for="newPassword">New Password *</label>
            <div class="password-container">
                <input type="password" id="newPassword" name="newPassword" required>
                <button type="button" class="toggle-password" data-toggle="newPassword" onclick="togglePassword('newPassword')">
                    <i class="fas fa-eye"></i>
                </button>
            </div>

            <label for="confirmPassword">Confirm New Password *</label>
            <div class="password-container">
                <input type="password" id="confirmPassword" name="confirmPassword" required>
                <button type="button" class="toggle-password" data-toggle="confirmPassword" onclick="togglePassword('confirmPassword')">
                    <i class="fas fa-eye"></i>
                </button>
            </div>

            <span id="passwordError" class="error-message"></span><br><br>

            <button type="submit" class="submit-btn">Change</button>
            <button type="button" class="submit-btn" onclick="cancelForm()">Cancel</button>
        </form>
    </div>
</body>
</html>
