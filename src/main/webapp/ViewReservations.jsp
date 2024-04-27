<!-- Robert Villarreal Silver Team -->
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reservation Lookup</title>
    <link rel="stylesheet" type="text/css" href="viewreservations.css"> <!-- Link to the CSS file -->
</head>
<body>
    <form method="POST" action="ReservationLookupServlet">
        Enter your Email or Reservation ID: <input type="text" name="searchInput" required>
        <input type="submit" value="Lookup">
    </form>
    <c:if test="${not empty error}">
        <p class="error">Error: ${error}</p> <!-- Display the error message if it exists -->
    </c:if>
</body>
</html>
