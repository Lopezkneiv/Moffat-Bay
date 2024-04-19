<!-- Robert Villarreal Silver Team -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Moffat Bay Lodge Pricing</title>
    <link rel="stylesheet" href="pricingstyles.css"> 
</head>
<body>

<div class="pricing-wrapper">

    <div class="pricing-header">
        <h1>Moffat Bay Lodge Pricing</h1>
    </div>

    <div class="pricing-container">
    
        <div class="pricing-card">
            <div class="card-header basic">
                <h2>$130 A Night</h2>
                <p class="plan-type">You May Book Daily</p>
            </div>
            <ul class="features-list">
                <li>2 Bed Rooms</li>
                <li>3 Beds</li>
                <li>2 Baths</li>
            </ul>
        <a href="Reservation.jsp" class="choose-button-link">
            <button class="choose-button" type="button">Choose</button>
        </a>
        </div>

        <div class="pricing-card">
            <div class="card-header standard">
                <h2>$180 A Night</h2>
                <p class="plan-type">You May Book Weekly</p>
            </div>
            <ul class="features-list">
                <li>3 Bed Rooms</li>
                <li>5 Beds</li>
                <li>3 Baths</li>
            </ul>
        <a href="Reservation.jsp" class="choose-button-link">
            <button class="choose-button" type="button">Choose</button>
        </a>
        </div>

        <div class="pricing-card">
            <div class="card-header premium">
                <h2>$220 A Night</h2>
                <p class="plan-type">You May Book Monthly</p>
            </div>
            <ul class="features-list">
                <li>4 Bed Rooms</li>
                <li>6 Beds</li>
                <li>4 Bath</li>
            </ul>
        <a href="Reservation.jsp" class="choose-button-link">
            <button class="choose-button" type="button">Choose</button>
        </a>
        </div>

    </div>

</div>

</body>
</html>



