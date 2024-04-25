<!-- Robert Villarreal Silver Team -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="checkoutstyles.css">
</head>
<body>

<div class="checkout-container">
    <h1>Checkout</h1>
    <form action="CheckoutServlet" method="post">
        
        <label for="cardPayment">Card Number:</label>
        <input type="text" id="cardPayment" name="cardPayment" required>
        
        <label for="cardName">Card Holder Name:</label>
        <input type="text" id="cardName" name="cardName" required>
        
        <label for="expireDate">Expiration Date:</label>
        <input type="text" id="expireDate" name="expireDate" required placeholder="MM/YY">
        
        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" name="cvv" required>
        
        <label for="homeAddress">Home Address:</label>
        <input type="text" id="homeAddress" name="homeAddress" required>
        
        <label for="checkIn">Check-In Date:</label>
        <input type="date" id="checkIn" name="checkIn" required>
        
        <label for="checkOut">Check-Out Date:</label>
        <input type="date" id="checkOut" name="checkOut" required>
        
        <input type="submit" value="Confirm Payment">
    </form>
</div>

</body>
</html>

