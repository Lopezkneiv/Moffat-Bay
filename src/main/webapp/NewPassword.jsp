<!-- Robert Villarreal Silver Team -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set New Password</title>
    <link rel="stylesheet" type="text/css" href="ResetStyle.css"> 
</head>
<body>

<div class="container">
    <h2>Set New Password</h2>
    <form action="UpdatePasswordServlet" method="POST">
        <input type="hidden" name="username" value="${sessionScope.username}">
        <input type="password" name="newPassword" placeholder="New Password" required><br>
        <input type="password" name="confirmPassword" placeholder="Confirm Password" required><br>
        <input type="submit" value="Update Password">
    </form>
</div>

</body>
</html>
