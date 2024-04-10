<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="LoginStyle.css">
</head>
<body>

<div class="container">
    <h2>Login</h2>
    <hr>

    <form action="LoginServlet" method="POST">
        <!-- Error Message Display -->
        <div class="error-message">
            <% if (request.getAttribute("loginError") != null) { %>
                <p style="color:red;"><%= request.getAttribute("loginError") %></p>
            <% } %>
        </div>

        <input type="email" name="email" placeholder="Enter Email" required>
        <input type="password" name="password" placeholder="Enter Password" required>
        
        <div class="forgot-password">
            <a href="PasswordReset.jsp">Forgot Password?</a>
        </div>
        
        <div class="remember-me">
            <input type="checkbox" id="rememberMe" name="rememberMe">
            <label for="rememberMe">Remember me</label>
        </div>
        
        <input type="submit" value="Sign In">
        
        <div class="alternative-login">
            <button type="button" onclick="location.href='login-facebook.jsp'">Login with Facebook</button>
            <button type="button" onclick="location.href='login-google.jsp'">Login with Google</button>
        </div>
    </form>
    
    <div class="register-link">
        Don't have an account? <a href="RegistrationForm.jsp">Sign Up</a>
    </div>
</div>

<script>
    // Script to handle 'Remember me' checkbox behavior (placeholder code)
    window.onload = function() {
        var rememberMe = document.getElementById('rememberMe');
        var email = document.getElementsByName('email')[0];
        
        rememberMe.checked = localStorage.getItem('rememberMe') === 'true';
        email.value = rememberMe.checked ? localStorage.getItem('email') : '';
        
        rememberMe.addEventListener('change', function() {
            localStorage.setItem('rememberMe', rememberMe.checked);
            localStorage.setItem('email', rememberMe.checked ? email.value : '');
        });
    };
</script>

</body>
</html>
