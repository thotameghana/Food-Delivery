<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: lightblue;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .message-container {
            background-color: #fff;
            padding: 20px 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .message-container h1 {
            color: #ff5733;
            font-size: 24px;
            margin-bottom: 10px;
        }
        .message-container p {
            font-size: 18px;
            color: #333;
        }
        .redirect {
            margin-top: 20px;
            font-size: 16px;
            color: #007bff;
        }
    </style>
    <script>
        setTimeout(() => {
            window.location.href = "GetAllRestaurants"; // Redirect to home.jsp after 5 seconds
        }, 5000);
    </script>
</head>
<body>
    <div class="message-container">
        <h1>Your Cart is Empty!</h1>
        <p>You will be redirected to the home page shortly.</p>
        <p class="redirect">If not, <a href="home.jsp">click here</a>.</p>
    </div>
</body>
</html>
