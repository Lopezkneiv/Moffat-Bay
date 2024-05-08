<%-- Created By: Keith Olsen --%>
<%-- Created On: 04/07/2024 --%>
<%-- Description: JSP Landing page for Moffat-Bay Lodge site. --%>
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<html>
<head>
	<title>Moffat-Bay Lodge</title>
    <link rel="stylesheet" type="text/css" href="index.css">
    <script>
 	// JavaScript function to display the selected description and hide others
    function showDescription(id) {
        var descriptions = document.querySelectorAll('.description-box');
        for (var i = 0; i < descriptions.length; i++) {
            descriptions[i].style.display = 'none'; // Hide all descriptions
        }
        var selectedDescription = document.getElementById(id);
        selectedDescription.style.display = 'block'; // Display selected description
    }

    // JavaScript function to hide all descriptions except for the default one on page load
    window.onload = function() {
        var descriptions = document.querySelectorAll('.description-box');
        for (var i = 1; i < descriptions.length; i++) { // Start from index 1 to skip the first one (default)
            descriptions[i].style.display = 'none'; // Hide all descriptions except for the default one
        }
    };
    </script>
</head>
<body>
    <%
        Boolean loginSuccess = (Boolean) session.getAttribute("loginSuccess");
        if (loginSuccess != null && loginSuccess) {
            out.println("<p>Login successful! Welcome, " + session.getAttribute("user") + ".</p>");
            session.removeAttribute("loginSuccess");
        }
        Boolean registrationSuccess = (Boolean) session.getAttribute("registrationSuccess");
        if (registrationSuccess != null && registrationSuccess) {
            out.println("<p>You have successfully registered an account. You are logged in.</p>");
            session.removeAttribute("registrationSuccess");
        }
    %>
    <header>
        <h1>Welcome to Moffat Bay Lodge
            <div class="Logo">
                <img src="pictures/SalishSalmon.png" alt="Logo_Salmon">
            </div>
        </h1>
    </header>


	 <main>
	 
	    <div class="container">
	        <div class="lodge-description">
	            <h2>Moffat Bay Lodge</h2>
	            <p>Welcome to Moffat Bay Lodge, your ultimate vacation destination! Nestled in the heart of nature, our lodge offers a serene escape from the hustle and bustle of everyday life. Whether you're seeking relaxation, family fun, or adventure, we have something for everyone.</p>
	        </div>
	        <div class="pictures-container">
	            <div class="picture">
	                <img src='pictures/Lodge.jpg' alt="Lodge">
	            </div>
	            <div class="picture">
	                <img src="pictures/Pool_Lodge.jpg" alt="Pool_Lodge">
	            </div>
	        </div>
        </div>
	    <div class="container">
	        <div class="buttons-container">
	            <button class="description-button" onclick="showDescription('relaxation')">Relaxation</button>
	            <button class="description-button" onclick="showDescription('family-friendly')">Family Friendly</button>
	            <button class="description-button" onclick="showDescription('adventure')">Adventure</button>
	        </div>
	        <div class="description-container">
	            <div id="relaxation" class="description-box" style="display: block;">
	                <p>Unwind and rejuvenate in our serene spa, enjoy a peaceful stroll along the beach, or simply lounge by the pool and soak up the sun.</p>
	            </div>
	            <div id="family-friendly" class="description-box">
	                <p>Create unforgettable family memories with our range of kid-friendly activities, spacious accommodations, and warm hospitality.</p>
	            </div>
	            <div id="adventure" class="description-box">
	                <p>Embark on thrilling adventures such as hiking through lush trails, kayaking in crystal-clear waters, and exploring the wonders of nature.</p>
	            </div>
	        </div>
			<div class="map-container">
	    		<iframe id="map" src="https://www.google.com/maps/d/embed?mid=15GNSJbNvfh7sRB6hfM5LrfpnJ-I&hl=en_US&ehbc=2E312F" style="width: 100%; height: 350px; border: 1px solid #ccc;" aria-hidden="false" tabindex="0"></iframe>
			</div>
		</div>
    </main>


    <footer>
        <p>&copy; 2024 Moffat Bay Lodge. All rights reserved.</p>
    </footer>
</body>
</html>