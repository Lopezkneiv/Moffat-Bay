<%-- Created By: Nathan Le --%>
<%-- Created On: 05/03/2024 --%>
<%-- Description: JSP Parasailing Attraction for Moffat-Bay Lodge. --%>
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moffat Bay Lodge </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #e0f7fa;
        }
        img {
            width: 100%; /* Adjust width as necessary */
            max-width: 300px; /* Set maximum width of images */
            height: auto; /* Maintain aspect ratio */
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        p {
            line-height: 1.6;
        }
        .slideshow-container {
            position: relative;
            max-width: 100%;
            margin: auto;
        }
        .mySlides {
            display: none;
            width: 100%;
            animation: fade 1.5s;
        }
        @keyframes fade {
            from {opacity: .4}
            to {opacity: 1}
        }
        .prev, .next {
            cursor: pointer;
            position: absolute;
            top: 50%;
            width: auto;
            padding: 16px;
            margin-top: -22px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
            user-select: none;
        }
        .next {
            right: 0;
            border-radius: 3px 0 0 3px;
        }
        .prev:hover, .next:hover {
            background-color: rgba(0, 0, 0, 0.8);
        }
    </style>
</head>
<body>
     <h1>Parasailing Attraction</h1>
    <div class="container">
        <p>Experience the thrill of soaring above the beautiful coastline with our parasailing adventure!</p>
        <h2>Details:</h2>
        <ul>
            <li><strong>Location:</strong> Coastal Beach, San Diego, United States of America</li>
            <li><strong>Duration:</strong> 1 hour (approximately)</li>
            <li><strong>Price:</strong> $50 per person</li>
            <li><strong>Requirements:</strong> Participants must be at least 12 years old and weigh between 70-250 pounds.</li>
            <li><strong>What to Bring:</strong> Swimsuit, towel, sunscreen, and a sense of adventure!</li>
        </ul>
        <h2>Gallery:</h2>
        <div class="slideshow-container">
            <div class="mySlides fade">
                <img src="pictures/Parasailing_1.jpg" style="width:100%">
            </div>
            <div class="mySlides fade">
                <img src="pictures/Parasailing_2.jpg" style="width:100%">
            </div>
            <div class="mySlides fade">
                <img src="pictures/Parasailing_3.jpg" style="width:100%">
            </div> 
            <div class="mySlides fade">
                <img src="pictures/Parasailing_4.jpg" style="width:100%">
            </div> 
            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
            <a class="next" onclick="plusSlides(1)">&#10095;</a>
        </div>
        <br>
        <p>Don't miss out on the opportunity to experience the excitement of parasailing and create unforgettable memories!</p>
    </div>

    <script>
        var slideIndex = 1;
        showSlides(slideIndex);

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            if (n > slides.length) {slideIndex = 1}
            if (n < 1) {slideIndex = slides.length}
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slides[slideIndex-1].style.display = "block";
        }
    </script>
</body>
</html>