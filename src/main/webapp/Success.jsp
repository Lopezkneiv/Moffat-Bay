<!-- Robert Villarreal Silver Team -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Booking Successful</title>
    <link rel="stylesheet" href="Successstyles.css"> 
</head>
<body>

    <div class="container">
        <h1>Booking Confirmation</h1>
        <p>Thank you for your booking, <c:out value="${sessionScope.user}"/>!</p>
        <p>Your reservation ID is: <c:out value="${sessionScope.reservation_Id}"/></p>
        <p>Check-In Date: <c:out value="${sessionScope.checkInDate}"/></p>
        <p>Check-Out Date: <c:out value="${sessionScope.checkOutDate}"/></p>
        
        <!-- Display booking details -->
                <h2>General Booking:</h2>
        <p>Number of Rooms Booked: <c:out value="${sessionScope.number_of_rooms}"/></p>
        <p>Number of Beds: <c:out value="${sessionScope.number_of_beds}"/></p>
        <p>Number of Adults: <c:out value="${sessionScope.number_of_adults}"/></p>
        <p>Number of Children: <c:out value="${sessionScope.number_of_children}"/></p>

        <!-- Display activity bookings -->
        <h2>Booked Activities:</h2>
        <ul>
            <c:forEach var="activityDetail" items="${sessionScope.bookedActivities}">
                <li><c:out value="${activityDetail}"/></li>
            </c:forEach>
        </ul>

       
        <a href="index.jsp">Return to Home</a>
    </div>
</body>
</html>


