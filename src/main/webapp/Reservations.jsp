<%-- Created By: Trevor Michaels --%>
<%-- Created On: 04/14/2024 --%>
<%-- Description: JSP Reservation page for Moffat-Bay. --%>
<%-- Modified: 04/18/2024 By Trevor Michaels, Added Reservation Page--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<html>
<head>
    <title>Reservations - Moffat Bay Lodge</title>
    <link rel="stylesheet" type="text/css" href="Reservations.css">
</head>
<body>
    <div class="reservation-section">
        <h1>Choose Your Room</h1>
        <div class="room-options">
            <div class="room-option">
                <img src="pictures/StandardRoom.png" alt="Standard Room">
                <h2>Standard Room $150</h2>
                <p>Experience the perfect blend of comfort and elegance in our Standard Room,
                ideal for singles or couples seeking a serene retreat. This beautifully appointed room 
                features a plush queen-sized bed with premium bedding, ensuring a restful night's sleep. 
                The spacious layout includes a work desk and a cozy seating area, 
                perfect for both relaxation and productivity.</p>
                <p>The room is equipped with modern amenities, including high-speed Wi-Fi, 
                a flat-screen TV with international channels, and a minibar stocked with refreshments. 
                The climate control system allows you to adjust the room's temperature to your comfort level.
                Step into the luxurious master bathroom, outfitted with high-end toiletries, fluffy towels, 
                and a large walk-in shower, providing a spa-like experience right in your room.</p>
                <button type="button">Book Now</button>
            </div>
            <div class="room-option">
                <img src="pictures/DeluxeRoom.jpg" alt="Deluxe Room">
                <h2>Deluxe Room $250</h2>
                <p>Welcome to the spacious and versatile Deluxe Room, thoughtfully designed to cater to 
                families, friends, or those who simply crave extra space. 
                This expansive open-floor layout seamlessly integrates comfort with convenience, 
                providing the perfect setting for both relaxation and socializing.</p>
                <p>The room features two luxuriously comfortable beds, ensuring a restful sleep for all guests. 
                Each bed is outfitted with high-quality linens and a selection of pillows to suit your preferences. 
                A fully equipped kitchenette stands ready to handle your culinary needs, complete with a microwave, 
                refrigerator, coffee maker, and a dining area. 
                Whether you're whipping up a quick breakfast or enjoying a leisurely meal, 
                everything you need is at your fingertips. Adjacent to the kitchenette, 
                the living area offers a plush sofa and a flat-screen TV, 
                ideal for movie nights or unwinding after a day of exploring. 
                The entire space is adorned with stylish decor that enhances the room's modern yet homey feel.</p>
                <button type="button">Book Now</button>
            </div>
            <div class="room-option">
                <img src="pictures/SuiteRoom.jpg" alt="Suite Room">
                <h2>Suite Room $350</h2>
                <p>Indulge in the pinnacle of luxury and comfort in our Suite Room, meticulously designed for honeymooners 
                and couples celebrating special occasions. This elegant suite offers an exceptional blend of privacy, 
                space, and upscale amenities, creating an unforgettable experience.</p>
                <p>The master bedroom is a private sanctuary, featuring a king-sized bed with plush bedding 
                and a dedicated en-suite bathroom. This bathroom is equipped with a dual vanity, a spacious walk-in shower,
                 and a separate bathtub, perfect for relaxing soaks after a day of adventure or romance.</p>
                <p>Step into the expansive living area, where sophistication meets comfort. The living room 
                is furnished with a comfortable sofa and state-of-the-art entertainment system, 
                ideal for enjoying quiet evenings together. The separate dining room provides an intimate setting 
                for romantic dinners, complemented by the suite’s full kitchen equipped with modern appliances, 
                including a full-size refrigerator, stove, and all the necessary utensils to prepare gourmet meals. 
                An additional full bathroom ensures convenience and privacy for both guests, featuring premium toiletries and luxurious towels.</p>
                <button type="button">Book Now</button>
            </div>
        </div>
    </div>
</body>
</html>
