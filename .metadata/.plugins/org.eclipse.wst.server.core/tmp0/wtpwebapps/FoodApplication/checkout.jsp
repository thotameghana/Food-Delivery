<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout Page</title>
    <link rel="stylesheet" href="./styles/checkout.css">
</head>
<body>
    <div class="container">
        <h1>Checkout</h1>
        <form action="Checkout" method="post">
		    <label for="address">Address:</label>
		    <input type="text" id="address" name="address" required>
		
		    <label for="city">City:</label>
		    <input type="text" id="city" name="city" required>
		
		    <label for="country">Country:</label>
		    <select id="country" name="country" required>
		        <option value="">Select a country</option>
		        <option value="usa">United States</option>
		        <option value="canada">Canada</option>
		        <option value="india">India</option>
		        <option value="australia">Australia</option>
		        <option value="germany">Germany</option>
		        <option value="france">France</option>
		        <option value="japan">Japan</option>
		    </select>
		
		    <label for="zip">PIN Code:</label>
		    <input type="text" id="zip" name="zip" required>
		
		    <label>Payment Mode:</label>
		    <div class="payment-options">
		        <div class="payment-option">
		            <input type="radio" id="credit-card" name="payment" value="credit-card" required>
		            <label for="credit-card">Credit Card</label>
		        </div>
		        <div class="payment-option">
		            <input type="radio" id="paypal" name="payment" value="UPI">
		            <label for="paypal">UPI</label>
		        </div>
		        <div class="payment-option">
		            <input type="radio" id="bank-transfer" name="payment" value="bank-transfer">
		            <label for="bank-transfer">Bank Transfer</label>
		        </div>
		    </div>
		
		    <button type="submit">Place Order</button>
		</form>
        
    </div>
</body>
</html>
