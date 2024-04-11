<%-- Created By: Keith Olsen --%>
<%-- Created On: 04/07/2024 --%>
<%-- Description: JSP Landing page for Moffat-Bay Lodge site. --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<html>
<head>
<title>Moffat-Bay Lodge</title>
    <style>
    	/* TODO - Move to Style Sheet */
        /* Style for the description button */
        .description-button {
            margin-bottom: 5px;
            padding: 5px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: block;
        }
        /* Style for the description container */
        .description-container {
            width: 50%;
            padding-right: 20px;
            display: inline-block;
        }
        /* Style for the description box */
        .description-box {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            display: none; /* Initially hide all descriptions */
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
        // JavaScript function to display the selected description and hide others
        function showDescription(id) {
            var descriptions = document.querySelectorAll('.description-box');
            for (var i = 0; i < descriptions.length; i++) {
                descriptions[i].style.display = 'none'; // Hide all descriptions
            }
            var selectedDescription = document.getElementById(id);
            selectedDescription.style.display = 'block'; // Display selected description
        }
    </script>
</head>
<body>
    <header>
        <h1>Welcome to Moffat Bay Lodge</h1>
        <p>Your ultimate vacation destination!</p>
    </header>


    <main>
        <div class="description-container">
            <button class="description-button" onclick="showDescription('relaxation')">Relaxation</button>
            
            <button class="description-button" onclick="showDescription('family-friendly')">Family Friendly</button>
            
            <button class="description-button" onclick="showDescription('adventure')">Adventure</button>
            
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
            <iframe id="map" src="https://www.google.com/maps/d/embed?mid=15GNSJbNvfh7sRB6hfM5LrfpnJ-I&hl=en_US&ehbc=2E312F" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
        </div>
    </main>

    <footer>
        <p>&copy; 2024 Moffat Bay Lodge. All rights reserved.</p>
    </footer>
</body>
</html>