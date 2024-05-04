<%-- Created By: Ivan Lopez-Kne --%>
<%-- Created On: 05/04/2024 --%>
<%-- Description: JSP Dinner Theater Promotion for Moffat Bay Lodge. --%>
<%-- Silver Team: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moffat Bay Dinner Theater</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #e0f7fa;
        }
        img {
            width: 100%;
            max-width: 500px;
            height: auto;
            display: block;
            margin: 0 auto; /* Center image */
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
        ul {
            list-style-type: none; /* Remove bullet points */
            padding: 0;
            text-align: center; /* Center the text of the list */
        }
        li {
            padding: 5px; /* Add some padding around each list item */
        }
    </style>
</head>
<body>
    <h1>Moffat Bay Dinner Theater</h1>
    <div class="container">
        <h2>About the Theater</h2>
        <p>Welcome to our exclusive dinner theater experience at Moffat Bay. Enjoy a captivating evening of drama, music, and fine dining set against the backdrop of our elegant venue. Perfect for a romantic night out or a special family gathering.</p>
        <img src="pictures/Theater_Interior.jpg" alt="Interior of Moffat Bay Dinner Theater">
        <h2>Upcoming Acts</h2>
        <ul>
            <li><strong>"Murder at the Masquerade" - </strong>Interactive mystery dinner show. Dates: May 20th - June 15th, 2024.</li>
            <li><strong>"Jazz Nights" - </strong>An evening of classic jazz performances. Dates: June 20th - July 5th, 2024.</li>
            <li><strong>"Comedy Capers" - </strong>Laugh out loud with our comedy lineup. Dates: July 10th - August 1st, 2024.</li>
        </ul>
        <p>Each show is paired with a specially designed menu by our renowned chef to complement the theme of the evening. Don't miss out on an unforgettable night of entertainment and cuisine!</p>
    </div>
</body>
</html>
