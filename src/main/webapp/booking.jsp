<!-- Robert Villarreal Silver Team -->
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Activity Booking</title>
    <link rel="stylesheet" href="bookingstyles.css"> 
</head>
<body>
<h1>Book Rooms and Activities</h1>
<p>Hello, <%= request.getSession().getAttribute("user") %></p>
<p>Check-In Date: <%= request.getSession().getAttribute("checkInDate") %></p>
<p>Check-Out Date: <%= request.getSession().getAttribute("checkOutDate") %></p>

<script type="text/javascript">
    window.onload = function() {
        var form = document.querySelector('form');
        var checkInDate = new Date('<%= request.getSession().getAttribute("checkInDate") %>');
        var checkOutDate = new Date('<%= request.getSession().getAttribute("checkOutDate") %>');

        form.onsubmit = function() {
            var isValid = true;
            var activityDates = document.querySelectorAll('input[type="date"]');
            activityDates.forEach(function(input) {
                var activityDate = new Date(input.value);
                if (activityDate < checkInDate || activityDate > checkOutDate) {
                    alert("Activity dates must be within your stay from " + checkInDate.toDateString() + " to " + checkOutDate.toDateString());
                    isValid = false;
                }
            });
            return isValid;
        };
    };
</script>

<form action="BookingServlet" method="post">
    <h2>Select Activities</h2>
    <% String[] activities = {"Snorkeling", "Parasailing", "Aquarium", "Dinner and Show", "Spa", "Couples Massage"};
       for (String activity : activities) { %>
        <fieldset>
            <legend><%= activity %></legend>
            <label>Date: <input type="date" name="<%= activity %>_date"></label>
            <label>Time:
                <select name="<%= activity %>_time">
                    <% for (int hour = 8; hour <= 23; hour++) {
                        String displayTime = hour > 12 ? (hour - 12) + " PM" : hour + " AM";
                        displayTime = displayTime.equals("0 AM") ? "12 AM" : displayTime;
                        displayTime = displayTime.equals("12 PM") ? "12 PM" : displayTime; %>
                        <option value="<%= hour + ":00" %>"><%= displayTime %></option>
                    <% } %>
                </select>
            </label>
            <label>Number of Adults:
                <select name="<%= activity %>_adults">
                    <% for (int i = 1; i <= 10; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                    <% } %>
                </select>
            </label>
            <label>Number of Children:
                <select name="<%= activity %>_children">
                    <% for (int i = 0; i <= 10; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                    <% } %>
                </select>
            </label>
            <label>Do Not Book This Activity: <input type="checkbox" name="<%= activity %>_skip" value="true"></label>
        </fieldset>
    <% } %>
    <fieldset>
        <legend>General Booking Details</legend>
        <label>Number of Rooms:
            <select name="number_of_rooms">
                <% for (int i = 1; i <= 4; i++) { %>
                    <option value="<%= i %>"><%= i %></option>
                <% } %>
            </select>
        </label>
        <label>Number of Beds:
            <select name="number_of_beds">
                <% for (int i = 1; i <= 6; i++) { %>
                    <option value="<%= i %>"><%= i %></option>
                <% } %>
            </select>
        </label>
        <label>Number of Adults:
            <select name="number_of_adults">
                <% for (int i = 1; i <= 4; i++) { %>
                    <option value="<%= i %>"><%= i %></option>
                <% } %>
            </select>
        </label>
        <label>Number of Children:
            <select name="number_of_children">
                <% for (int i = 0; i <= 6; i++) { %>
                    <option value="<%= i %>"><%= i %></option>
                <% } %>
            </select>
        </label>
    </fieldset>
    <div class="button-container">
        <input type="submit" value="Submit Booking">
    </div>
</form>
</body>
</html>
