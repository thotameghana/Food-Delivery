<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login here</title>
<link rel="stylesheet" href="./styles/login.css">
</head>
<body>
    <div class="container">
        <div class="form">
            <h1>Login</h1>
            <form action="Login" method="post">
                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="button-group">
                    <input type="submit" value="Login">
                    <a href="register.jsp">
                        <button type="button">Register</button>
                    </a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>