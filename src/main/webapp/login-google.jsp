<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<head>
    <title>Google Login</title>
    <link rel="stylesheet" type="text/css" href="LoginStyle.css">
</head>
<body>

<div class="container">
    <h2>Google Login</h2>
    <hr>
    <form action="Success.jsp" method="POST"> 
        <input type="email" name="googleUser" placeholder="Google Email" required><br>
        <input type="password" name="googlePassword" placeholder="Google Password" required><br>
        <input type="submit" value="Log in with Google">
    </form>
    <a href="Login.jsp">Return to Login</a> 
</div>

</body>
</html>
