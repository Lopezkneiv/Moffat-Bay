<!-- Robert Villarreal Silver Team -->
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Password Reset</title>
    <link rel="stylesheet" type="text/css" href="ResetStyle.css">
</head>
<body>

<div class="container">
    <h2>Password Reset</h2>
    <form action="PasswordResetServlet" method="POST">
        <input type="text" name="username" placeholder="Enter Username" required><br>
        <input type="email" name="email" placeholder="Enter Email" required><br>
        <input type="submit" value="Verify">
    </form>
    <a href="Login.jsp">Return to Login</a> 
</div>

</body>
</html>
