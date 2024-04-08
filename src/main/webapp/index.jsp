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
    </style>
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
    </main>

    <footer>
        <p>&copy; 2024 Moffat Bay Lodge. All rights reserved.</p>
    </footer>
</body>
</html>
</html>