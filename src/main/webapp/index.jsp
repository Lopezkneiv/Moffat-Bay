<%-- Created By: Keith Olsen --%>
<%-- Created On: 04/07/2024 --%>
<%-- Description: JSP Landing page for Moffat-Bay Lodge site. --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<html>
<head>
  <title>Moffat-Bay Lodge</title>
   <style>
        /* Style for the navigation menu */
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }
        nav ul li {
            float: left;
        }
        nav ul li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        nav ul li a:hover {
            background-color: #555;
        }
        
        
        /* Style for the small boxes */
        .description-box {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
        }
        /* Style for the link to show/hide description */
        .description-heading {
            cursor: pointer;
            font-weight: bold;
            text-decoration: underline;
        }
        
        /* Style for the map iframe */
        #map {
            width: 100%;
            height: 400px; 
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-top: 20px;
        }
        /* Style for the map container */
        .map-container {
            float: right;
            width: 30%;
        }
    </style>
    
    <script>
        // JavaScript function to toggle the visibility of the descriptions
        function toggleDescription(id) {
            var description = document.getElementById(id);
            if (description.style.display === "none") {
                description.style.display = "block";
            } else {
                description.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <header>
        <h1>Welcome to Moffat Bay Lodge</h1>
    </header>
    
    <nav>
        <ul>
            <li><a href="landing.jsp">Home</a></li>
            <li><a href="reservations.jsp">Reservations</a></li>
            <li><a href="activities.jsp">Activities</a></li>
            <li><a href="contact.jsp">Contact Us</a></li>
        </ul>
    </nav>

    <main>
        <p>Welcome to Moffat Bay Lodge, your ultimate vacation destination! Explore our beautiful rooms, exciting activities, and plan your dream vacation today.</p>
    
    	<div class="description-container">
            <div class="description-box">
                <span class="description-heading" onclick="toggleDescription('relaxation')">Relaxation</span>
                <p id="relaxation" style="display: none;">Unwind and rejuvenate in our serene spa, enjoy a peaceful stroll along the beach, or simply lounge by the pool and soak up the sun.</p>
            </div>
            <div class="description-box">
                <span class="description-heading" onclick="toggleDescription('family-friendly')">Family Friendly</span>
                <p id="family-friendly" style="display: none;">Create unforgettable family memories with our range of kid-friendly activities, spacious accommodations, and warm hospitality.</p>
            </div>
            <div class="description-box">
                <span class="description-heading" onclick="toggleDescription('adventure')">Adventure</span>
                <p id="adventure" style="display: none;">Embark on thrilling adventures such as hiking through lush trails, kayaking in crystal-clear waters, and exploring the wonders of nature.</p>
            </div>
        </div>
        
        
        <div class="map-container">
            <iframe id="map" src="https://www.google.com/maps/d/embed?mid=15GNSJbNvfh7sRB6hfM5LrfpnJ-I&hl=en_US&ehbc=2E312F" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
        </div>
        
    </main>

    <footer>
        <p>&copy; 2024 Moffat Bay Lodge. All rights reserved.</p>
    </footer>
</body>
</html>
</html>