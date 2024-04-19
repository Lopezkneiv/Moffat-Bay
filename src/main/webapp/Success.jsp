<!-- Robert Villarreal Silver Team -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Booking Successful</title>
    <link rel="stylesheet" href="Successstyles.css"> 
<body>

    <div class="container">
        <h1>Booking Confirmation</h1>
        <p>Thank you for your booking, <c:out value="${sessionScope.user}"/>!</p>
        <p>Your reservation ID is: <c:out value="${sessionScope.reservationId}"/></p>
        
        
        <p>Check-In Date: <c:out value="${sessionScope.checkInDate}"/></p>
        <p>Check-Out Date: <c:out value="${sessionScope.checkOutDate}"/></p>

       
        <a href="index.jsp">Return to Home</a>
    </div>
</body>
</html>



