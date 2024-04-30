<!-- Robert Villarreal Silver Team -->
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>


<html>
<head>
    <title>Sign Up</title>
        <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap" rel="stylesheet">
     <link rel="stylesheet" type="text/css" href="RegistrationStyle.css">

</head>
<body>
<div class="container">
    <h2>Sign Up</h2>
        <%-- Check for registration error and display message if present --%>
    <% if (request.getAttribute("registrationError") != null) { %>
        <p style="color:red;"><%= request.getAttribute("registrationError").toString() %></p>
    <% } %>
    <form action="RegisterServlet" method="POST">
        <input type="text" name="username" placeholder="Username" required>
        <input type="email" name="email" placeholder="Email Address" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="password" name="repassword" placeholder="Re-enter Password" required>
        <input type="submit" value="Sign Up">
    </form>
    <a href="Login.jsp">Already have an account? Login</a> 
</div>

</body>
</html>
