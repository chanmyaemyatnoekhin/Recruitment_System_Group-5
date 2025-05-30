<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Verify OTP</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #1e68d4;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }

    .box {
      background-color: rgba(255, 255, 255, 0.05);
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
      width: 320px;
      color: white;
    }

    h2 {
      text-align: center;
      margin-bottom: 25px;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      margin-bottom: 6px;
    }

    .form-group input {
      width: 100%;
      padding: 12px;
      border: none;
      border-radius: 8px;
      background-color: rgba(255, 255, 255, 0.1);
      color: white;
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
    }

    .box button:hover {
      background-color: #2563eb;
    }

    .message {
      margin-top: 10px;
      text-align: center;
      font-size: 13px;
    }

    .error {
      color: red;
    }

    .success {
      color: #4caf50;
    }

    #timer {
      margin-bottom: 15px;
      font-weight: bold;
      text-align: center;
      font-size: 14px;
    }

    #resendOTP {
      margin-top: 12px;
      background: none;
      border: none;
      color: #ffffffcc;
      font-size: 14px;
      text-align: center;
      width: 100%;
      cursor: pointer;
      text-decoration: underline;
    }

    #resendOTP:hover {
      color: #ffffff;
    }
  </style>
</head>
<body>
  <div class="box">
    <h2>Enter OTP</h2>

    <form action="VerifyOTPServlet" method="post" id="otpForm">
      <div class="form-group">
        <label for="otp">OTP</label>
        <input type="text" id="otp" name="otp" placeholder="Enter the OTP" required>
      </div>

      <% 
        String message = (String) request.getAttribute("message");
        String status = (String) request.getAttribute("status");
        if (message != null) {
      %>
        <div class="message <%= "success".equals(status) ? "success" : "error" %>">
          <%= message %>
        </div>
      <% } %>

      <!-- Countdown Timer -->
      <div id="timer">Expired time: 60s</div>

      <button type="submit" id="submitBtn">Verify OTP</button>
    </form>

    <button id="resendOTP">Didn’t receive code? Resend OTP</button>
  </div>

  <script>
    let timeLeft = 60;
    let countdown;

    const timerDiv = document.getElementById("timer");
    const otpInput = document.getElementById("otp");
    const submitBtn = document.getElementById("submitBtn");
    const resendBtn = document.getElementById("resendOTP");

    function updateTimer() {
      if (timeLeft >= 0) {
        timerDiv.textContent = "Expired time: " + timeLeft + "s";
        timeLeft--;
      } else {
        timerDiv.textContent = "Time expired!";
        otpInput.disabled = true;
        submitBtn.disabled = true;
        clearInterval(countdown);
      }
    }

    function startCountdown() {
      clearInterval(countdown);
      timeLeft = 60;
      otpInput.disabled = false;
      submitBtn.disabled = false;
      updateTimer(); // immediate update
      countdown = setInterval(updateTimer, 1000);
    }

    resendBtn.addEventListener("click", function (e) {
    	  e.preventDefault();

    	  fetch("ForgetPasswordServlet", {
    	    method: "POST",
    	    headers: {
    	      "Content-Type": "application/x-www-form-urlencoded"
    	    },
    	    body: new URLSearchParams({
    	      email: "<%= session.getAttribute("email") %>"
    	    })
    	  })
    	  .then(response => response.text())
    	  .then(() => {
    	    // restart timer
    	    startCountdown();

    	    // Redirect to forgetPassword.jsp or verifyOTP.jsp depending on server response
    	    window.location.href = "verifyOTP.jsp";
    	  })
    	  .catch(error => {
    	    console.error("Error during resend:", error);
    	    alert("Failed to resend OTP. Please try again.");
    	  });
    	});


    startCountdown(); // initial call
  </script>
</body>
</html>
