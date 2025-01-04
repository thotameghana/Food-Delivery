<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="./styles/register.css">
<script src="app.js"></script>
</head>
<body>
    <div class="container">
        <div class="form">
            <h1>Register</h1>
            <form method="GET" onsubmit="return validate();" action="Register">
                <div class="input-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" placeholder="Enter your name">
                    <span id="userNameMessage" class="error-message"></span>
                </div>
                
                <div class="input-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" placeholder="Enter password">
                    <span id="passwordMessage" class="error-message"></span>
                </div>
                
                <div class="input-group">
                    <label for="cpassword">Confirm Password:</label>
                    <input type="password" id="cpassword" name="cpassword" placeholder="Confirm password">
                    <span id="confirmPasswordMessage" class="error-message"></span>
                </div>
                
                <div class="input-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email">
                    <span id="emailMessage" class="error-message"></span>
                </div>
                
                <div class="input-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" placeholder="Enter your address">
                    <span id="addressMessage" class="error-message"></span>
                </div>
                
                <div class="button-group">
                    <input type="submit" value="Register">
                    <a href="login.jsp">
                        <button type="button">Login</button>
                    </a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
