<!-- Robert Villarreal Silver Team -->
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
    <form action="index.jsp" method="POST"> 
        <input type="text" name="facebookUser" placeholder="Facebook Username" required><br>
        <input type="password" name="facebookPassword" placeholder="Facebook Password" required><br>
        <input type="submit" value="Log in with Facebook">
    </form>
    <a href="Login.jsp">Return to Login</a> 
</div>

</body>
</html>
