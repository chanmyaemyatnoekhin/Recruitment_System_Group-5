<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Thank You</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #1e68d4; /* original blue background */
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      color: white; /* white text to match */
    }
    .thankyou-container {
      background: rgba(255, 255, 255, 0.1); /* translucent white container */
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
      text-align: center;
      max-width: 400px;
    }
    h1 {
      color: #ffffff;
      margin-bottom: 20px;
      font-weight: 700;
    }
    p {
      color: #e0e6f0; /* lighter white for paragraph */
      font-size: 16px;
      line-height: 1.5;
    }
    a {
    color: #ffffff; /* lighter white for paragraph */
      font-size: 16px;
      line-height: 1.5;
    }
  </style>
  <script>
            function comfirmLogout() {
              var userConfirmed = confirm("Are you sure you want to log out?");
              if (userConfirmed) {
                alert("Logging out...");
                window.location.href = "design.jsp"; // Adjust this to your actual URL
              } else {
                alert("Canceling logout.");
              }
            }
          </script>
</head>
<body>
  <div class="thankyou-container">
    <h1>Thank You for Your Submission!</h1>
    <p>We appreciate your interest and the time you took to apply.<br/>
    Our team is currently reviewing your application and will get back to you shortly.<br/>
    Please feel free to reach out if you have any questions in the meantime.</p>
  <a href="javascript:void(0);" onclick="comfirmLogout()">Log Out</a>
  </div>
 
</body>
</html>
