<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recruitment Form</title> 
    
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
    
    <script>
function validatePhoneInput(input) {
    // Allow only digits and optional leading plus sign
    const regex = /^[\d+]*$/;
    if (!regex.test(input.value)) {
        // Remove invalid characters
        input.value = input.value.replace(/[^0-9+]/g, '');
        document.getElementById("phoneError").innerText = "Only digits and + sign are allowed!";
    } else {
        document.getElementById("phoneError").innerText = "";
    }
}
</script>

<script>
function validateEmail(input) {
    // Simple email regex pattern
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(input.value)) {
        document.getElementById("emailError").innerText = "Invalid email format!";
    } else {
        document.getElementById("emailError").innerText = "";
    }
}
</script>

<script>
function validateEmail(input) {
    // Simple email regex pattern
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(input.value)) {
        document.getElementById("emailError").innerText = "Invalid email format!";
    } else {
        document.getElementById("emailError").innerText = "";
    }
}
</script>
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
         .nrc-container {
		      display: flex;
		      gap: 10px;
		      align-items: flex-end;
		      flex-wrap: wrap;
   		 }

	    .nrc-field {
		      display: flex;
		      flex-direction: column;
	    }
         .nrc-preview {
      font-weight: bold;
      padding: 5px 10px;
      min-width: 200px;
      align-self: flex-end;
    }
        
        h2 {
            text-align: center;
            color: #5e4bb6;
        }
        label {
            display: block;
            margin-top: 15px;
        }
        input[type="text"],input[type="number"], input[type="email"], input[type="tel"], input[type="file"],
        textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
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
         select, input[type="text"] {
      padding: 5px;
      min-width: 120px;
    }
       .change-password-link {
				  color: #007BFF; 
				  margin-right: 190px;
				 
}

.change-password-link:last-child {
  margin-right:0; 
}
       
        
        
    </style>
</head>
<body>
    <div class="container">
        <h2>Recruitment Form</h2>
        <form action="SubmitApplicationServlet" method="post" enctype="multipart/form-data">
        <!-- other fields -->
            <label for="fullName">Full Name *</label>
           <!-- other fields -->
         <input type="hidden" name="userId" value="<%= userId %>">   
        <input type="text" id="fullName" name="fullName" value="<%= request.getAttribute("fullName") %>" oninput="validateNameInput(this)" placeholder="your full name" required>
        <span id="nameError" style="color:red;"></span><br><br>


         <label for="email">Email *</label> 
<input type="email" id="email" name="email" placeholder="example@domain.com" 
       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" 
       readonly required>
<span id="emailError" style="color:red;"></span>



            <label for="phone">Phone Number *</label>
             <!-- other fields --> 
        <input type="tel" id="phone" name="phone" value="<%= request.getAttribute("phone")%>" oninput="validatePhoneInput(this)" required>
        <span id="phoneError" style="color:red;"></span><br><br>
        
        
        
<!-- NRC START -->
<div class="nrc-container">
  <div class="nrc-field">
    <label for="region">NRC Number *</label>
    <select id="region" required onchange="updateTownships()">
      <option value="">Select</option>
      <option value="1/">1/</option>
      <option value="2/">2/</option>
      <option value="3/">3/</option>
      <option value="4/">4/</option>
      <option value="5/">5/</option>
      <option value="6/">6/</option>
      <option value="7/">7/</option>
      <option value="8/">8/</option>
      <option value="9/">9/</option>
      <option value="10/">10/</option>
      <option value="11/">11/</option>
      <option value="12/">12/</option>
      <option value="13/">13/</option>
      <option value="14/">14/</option>
    </select>  
  </div>

  <div class="nrc-field">       
    <select id="township" required onchange="updatePreview()">
      <option value="">Select</option>
    </select>
  </div>

  <div class="nrc-field">
    <select id="citizenship" required onchange="updatePreview()">
      <option value="">Select</option>
      <option value="(N)">(N)</option>
      <option value="(A)">(A)</option>
    </select>
  </div>

  <div class="nrc-field">
    <input type="text" id="nrc-number" placeholder="e.g., 112334" oninput="validateNRC(this)" maxlength="6" required>
  </div>

  <script>
    // Modify your validateNRC function to ensure it only keeps digits
    function validateNRC(input) {
      // Allow only digits
      input.value = input.value.replace(/\D/g, '');

      // Enforce max length of 6 digits
      if (input.value.length > 6) {
        input.value = input.value.slice(0, 6); // Truncate to 6 digits
      }

      // If exactly 6 digits, remove the invalidity message
      if (input.value.length === 6) {
        input.setCustomValidity('');
      } else {
        input.setCustomValidity('Please enter exactly 6 digits');
      }
    }

    const townshipData = {
      '1/': ['MaKaNa', 'WaMaNa', 'KaPhaNa', 'SaLaNa', 'TaNaNa', 'MaKaNa', 'KaMaNa'],
      '2/': ['LaKaNa', 'DaMaSa', 'PhaYaSa', 'YaTaNa', 'BaLaKha', 'MaSaNa', 'PaSaNa'],
      '3/': ['BaAaNa', 'LaBaNa', 'ThaTaNa', 'KaKaYa', 'KaSaKa', 'MaWaTa', 'PhaPaNa'],
      '4/': ['HaKhaNa', 'HtaTaLa', 'MaTaPa', 'KaPaLa', 'PhaLaNa', 'TaTaNa', 'TaZaNa'],
      '5/': ['MaYaNa', 'AaYaTa', 'KaOoTa', 'BaTaLa', 'SaKaNa', 'MaMaNa', 'MaMaTa', 'KaLaHta'],
      '6/': ['KaMaYa', 'TaMaNa', 'KaPaNa', 'PaLaNa', 'TaKaNa', 'KaTaNa'],
      '7/': ['KaLaNa', 'KaLaTa', 'KaPaTa', 'KaLaTa', 'TaKaNa', 'KaKaNa'],
      '8/': ['MaLaNa', 'MaMaNa', 'SaPaWa', 'TaLaNa', 'PaTaNa', 'SaKaNa'],
      '9/': ['MaLaNa', 'MaNaMa', 'KaMaNa', 'MaMaNa', 'SaPaLa', 'MaNaNa'],
      '10/': ['PaThaNa', 'MaMaNa', 'LaLaNa', 'SaLaNa', 'TaKaNa', 'PaLaNa'],
      '11/': ['KaPaNa', 'LaKaNa', 'SaLaNa', 'TaLaNa', 'SaKaNa', 'KaLaNa'],
      '12/': ['KaMaNa', 'PaTaNa', 'KaPaNa', 'SaKaNa', 'PaMaNa', 'TaKaNa'],
      '13/': ['TaKaNa', 'KaPaNa', 'LaKaNa', 'SaKaNa', 'PaLaNa', 'MaKaNa'],
      '14/': ['KaLaNa', 'TaKaNa', 'KaPaNa', 'SaKaNa', 'KaLaNa', 'MaPaNa']
    };

    function updateTownships() {
      const region = document.getElementById('region').value;
      const townshipSelect = document.getElementById('township');
      
      townshipSelect.innerHTML = '<option value="">Select</option>';

      if (townshipData[region]) {
        townshipData[region].forEach(town => {
          const opt = document.createElement('option');
          opt.value = town;
          opt.textContent = town;
          townshipSelect.appendChild(opt);
        });
      }
      
      updatePreview();
    }

    function updatePreview() {
      const region = document.getElementById('region').value;
      const township = document.getElementById('township').value;
      const citizenship = document.getElementById('citizenship').value;
      const number = document.getElementById('nrc-number').value;

      let nrc = '';
      if (region && township && citizenship && number) {
        nrc = `${region}${township}(${citizenship})${number}`;
      }

      document.getElementById('nrc-preview').textContent = 'NRC: ' + nrc;
      document.getElementById('nrc-hidden').value = nrc;
    }
  </script>

  <!-- Hidden input for form submission -->
  <input type="hidden" name="nrc" id="nrc-hidden">
</div>
<!-- NRC END -->

		
				
				
            <label>Address</label>
            <textarea name="address"></textarea>

            <label>Marital Status</label>
            <select name="maritalStatus" required>
                <option value="">Select your marital status</option>
                <option>Single</option>
                <option>Married</option>
                <option>Divorced</option>
                <option>Widowed</option>
            </select>

            <label>Gender</label>
            <select name="gender" required>
                <option value="">Select your gender</option>
                <option>Male</option>
                <option>Female</option>
                <option>Other</option>
            </select>

            <label>Position Applied For *</label>
            <select name="position" required>
                <option value="">Select a position</option>
                <option>Software Developer</option>
                <option>Designer</option>
                <option>Project Manager</option>
                <option>Other</option>
            </select>

            <label>Upload Resume (PDF, DOC)</label>
			<input type="file" name="resume" id="resume" accept=".pdf,.doc,.docx">

			<script>
			  const fileInput = document.getElementById('resume');
			
			  fileInput.addEventListener('change', function() {
			    const file = this.files[0];
			    if (file) {
			      // Allowed file extensions
			      const allowedExtensions = /(\.txt|\.pdf|\.doc|\.docx)$/i;
			      
			      if (!allowedExtensions.exec(file.name)) {
			        alert("This file can't be chosen");
			        this.value = ''; // Clear the input
			      }
			    }
			  });
			</script>

            <label>Education Background</label>
            <select name="education">
                <option value="">Select your education background</option>
                <option>High School</option>
                <option>Bachelor's Degree</option>
                <option>Master's Degree</option>
                <option>PhD</option>
                <option>Other</option>
            </select>
            
            
            
            
            <!--OFFICE BRANCH START -->
              <label>Office Branch</label>
            <textarea name="branch"></textarea>
            <!-- OFFICE BRANCH END -->
            
            

            <button type="submit">Apply</button>
            <button type="button" onclick="cancelForm()">Cancel</button>
        </form><br>
         <a href="changePassword.jsp" class="change-password-link">Change Password</a>
         
          
          <a href="javascript:void(0);" onclick="comfirmLogout()">Log Out</a>

         
         
    </div>
</body>
</html>
