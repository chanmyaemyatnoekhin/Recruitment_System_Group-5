<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Form</title>
<%@ page session="true" %>
	<%
	    String userId = (String) session.getAttribute("userId");  // Step 1: Get userId from session
	    if (userId == null) {
	        // Not logged in, redirect to login
	        response.sendRedirect("design.jsp");
	    }
	%>
	<script>
    function validateNameInput(input) {
        const regex = /\d/;
        if (regex.test(input.value)) {
            input.value = input.value.replace(/\d/g, '');
            document.getElementById("nameError").innerText = "Numbers are not allowed in the name field!";
        } else {
            document.getElementById("nameError").innerText = "";
        }
    }
    </script>
    
    <script type="text/javascript">
        // Function to update the form action based on selected radio button and entered comment
        function updateFormAction() {
            var commentValue = document.getElementById('adminComment').value; // Get the comment entered
 
            // Update the form action dynamically
            var recruitmentId = document.getElementById('recruitment_id').value;
            var fullName = document.getElementById('fullName').value;

            var formAction = "AdminReviewServlet?recruitment_id=" + recruitmentId + "&fullName=" +fullName;
 
            
            if (commentValue) {
                formAction += "&adminComment=" + encodeURIComponent(commentValue);
            }
 
            // Set the updated form action
            document.getElementById('reviewForm').action = formAction;
        }
</script>
</head>
<body>
<div class="container">
<h1>Review Form</h1>
<form id="reviewForm" action="" method="post" enctype="multipart/form-data" onsubmit="updateFormAction()">
<input type="hidden" name="userId" value="<%= userId %>">
<label for="fullName">Full Name *</label>
<input type="text" id="fullName" name="fullName" value="<%= request.getAttribute("fullName") %>" oninput="validateNameInput(this)" placeholder="your full name" required>
        <span id="nameError" style="color:red;"></span><br><br>
        <label for="email">Email *</label> 
<input type="email" id="email" name="email" placeholder="example@domain.com" 
       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" 
       readonly required>
<span id="emailError" style="color:red;"></span>
<label>Admin Comment</label>
            <textarea name="adminComment"></textarea>
<button type="submit" name="action" value="accept">Accept</button>
        
</form>
</div>
</body>
</html>