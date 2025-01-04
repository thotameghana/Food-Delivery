<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmed</title>
    <style>
        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;

        }

        .container {
            width: 100%;
            max-width: 400px;
            padding: 20px;
        }

        .confirmation-box {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            text-align: center;
        }

        h1 {
            color: #4CAF50;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .animation-container {
            margin-bottom: 20px;
        }

        .delivery-animation {
            max-width: 100%;
            height: auto;
        }

        p {
            color: #333333;
            font-size: 16px;
            line-height: 1.5;
            margin-bottom: 20px;
        }

        .track-button {
            display: inline-block;
            background-color: #4CAF50;
            color: #ffffff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 4px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .track-button:hover {
            background-color: #45a049;
        }

        @media (max-width: 480px) {
            .container {
                padding: 10px;
            }

            .confirmation-box {
                padding: 20px;
            }

            h1 {
                font-size: 20px;
            }

            p {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="confirmation-box">
            <h1>Order Confirmed!</h1>
            <div class="animation-container">
                <img src="https://cdn.dribbble.com/users/1693785/screenshots/9161312/comp_2_2.gif" alt="Animated delivery person" class="delivery-animation">
            </div>
            <p>Thank you for your order! Our delivery person is on the way with your package.</p>
            <a href="GetAllRestaurants" class="track-button">Home</a>
        </div>
    </div>
</body>
</html>

