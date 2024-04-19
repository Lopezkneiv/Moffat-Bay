<!-- Robert Villarreal Silver Team -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="Header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Moffat Bay Lodge Reservations</title>
    <link rel="stylesheet" href="reservationstyle.css">
</head>
<body>
    <div class="main-content">

        <div>
            <h2>Moffat Bay Lodge Reservations</h2>
            
                <c:if test="${not empty errorMessage}">
        <div class="error-message">
            <p>${errorMessage}</p>
        </div>
    </c:if>
            <form id="reservationForm" action="ReservationServlet" method="post">
                <label>Check-in Date: <input type="date" name="checkInDate"></label>
                <label>Check-out Date: <input type="date" name="checkOutDate"></label>
                <div class="booking-options">
                    <label><input type="radio" name="bookingType" value="daily" checked> Daily</label>
                    <label><input type="radio" name="bookingType" value="weekly"> Weekly</label>
                    <label><input type="radio" name="bookingType" value="monthly"> Monthly</label>
                </div>
                <button type="submit">Book Reservation</button>
                            <div class="view-reservations-link" style="margin-top: 10px;">
                <a href="ViewReservations.jsp">View Your Reservations</a>
            </div>
            </form>
        </div>
    </div>
</body>
</html>


