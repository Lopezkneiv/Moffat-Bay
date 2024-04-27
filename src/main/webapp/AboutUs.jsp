<%-- Created By: Keith Olsen --%>
<%-- Created On: 04/14/2024 --%>
<%-- Description: JSP About US page for Moffat-Bay. --%>
<%-- Modified: 04/15/2024 By Keith Olsen, Added Contact Us section--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="Header.jsp" %>
<html>
<head>
    <title>About Us - Moffat Bay Lodge</title>
    <link rel="stylesheet" type="text/css" href="About.css">
</head>
<body>
    <div class="about-section">
        <div class="about-icon">
            <!-- Icon taken from https://www.flaticon.com/free-icons/about-us -->
            <img src="pictures/aboutUsIcon.png" alt="About Us Icon">
        </div>
        <div class="about-description">
        	<h1>Moffat Bay Lodge</h1>
            <h2>About Us</h2>
            <p>Welcome to Moffat Bay Lodge, your premier vacation destination nestled in the picturesque landscapes of 
            Moffat Bay. At Moffat Bay Lodge, we believe in providing our guests with an unforgettable experience that combines 
            luxury, comfort, and adventure. Surrounded by breathtaking natural beauty, our lodge offers a sanctuary for 
            relaxation and rejuvenation.
            </p>
			<br>
			<p>
			Whether you're seeking a tranquil retreat to unwind amidst serene surroundings or an action-packed adventure 
			exploring the wonders of nature, Moffat Bay Lodge has something for everyone. Our dedicated team is committed 
			to ensuring that your stay with us is nothing short of extraordinary. From cozy accommodations and delectable 
			dining options to thrilling outdoor activities and personalized service, we strive to exceed your expectations 
			at every turn.
			</p>
			<br>
			<p>Come escape the ordinary and embark on a journey of discovery at Moffat Bay Lodge. Your dream vacation awaits!
			</p>
		</div>
    </div>

	<%-- Profile pictures are place holders from https://www.pexels.com/ --%>
    <div class="team-section">
        <h2>Professional Team</h2>
        <div class="team-members">
            <div class="team-member">
                <img src="pictures/Manager.jpg" alt="Profile Picture">
                <p><b>Bixby Bumblethorn</b></p>
                <p><b>Position:</b> Manager</p>
                <p>
                Meet Bixby Bumblethorn, our dedicated Manager who oversees the day-to-day operations of Moffat Bay Lodge. 
                With years of experience in the hospitality industry, Bixby Bumblethorn brings a wealth of knowledge and 
                expertise to ensure that every aspect of your stay is seamless and enjoyable. Passionate about 
                delivering exceptional service, Bixby Bumblethorn is committed to making your experience at Moffat Bay Lodge 
                truly memorable.
                </p>
                <div class="social-icons">
                    <!-- Include your social media icons here as links -->
                    <a href="#"><img src="pictures/facebook-icon.png" alt="Facebook Icon"></a>
                    <a href="#"><img src="pictures/google-icon.png" alt="Google Icon"></a>
                    <a href="#"><img src="pictures/x-social-icon.png" alt="Twitter Icon"></a>
                </div>
            </div>
            <div class="team-member">
                <img src="pictures/Chef.jpg" alt="Profile Picture">
                <p><b>Isadora Flambeaux</b></p>
                <p><b>Position:</b> Chef</p>
                <p>
                Introducing Isadora Flambeaux, our talented Chef who delights guests with his culinary creations at Moffat Bay Lodge. 
                With a passion for cooking and a flair for creativity, Isadora Flambeaux crafts mouthwatering dishes using the finest 
                locally sourced ingredients. Whether you're indulging in a gourmet meal at our restaurant or savoring a 
                picnic lunch amidst nature, Isadora Flambeaux's culinary expertise promises a gastronomic journey like no other.
                </p>
                <div class="social-icons">
                    <!-- Include your social media icons here as links -->
                    <a href="#"><img src="pictures/facebook-icon.png" alt="Facebook Icon"></a>
                    <a href="#"><img src="pictures/google-icon.png" alt="Google Icon"></a>
                    <a href="#"><img src="pictures/x-social-icon.png" alt="Twitter Icon"></a>
                </div>
            </div>
            <div class="team-member">
                <img src="pictures/ActivityCord.jpg" alt="Profile Picture">
                <p><b>Penelope Starling</b></p>
                <p><b>Position:</b> Activities Coordinator</p>
                <p>
                Say hello to Penelope Starling, our energetic Activities Coordinator who curates exciting adventures for guests at 
                Moffat Bay Lodge. With a love for the outdoors and a knack for organizing thrilling experiences, Penelope Starling 
                ensures that your stay is filled with unforgettable moments. From guided hikes and kayaking excursions 
                to wildlife encounters and stargazing nights, Penelope Starling's passion for adventure will ignite your sense of 
                exploration.
                </p>
                <div class="social-icons">
                    <!-- Include your social media icons here as links -->
                    <a href="#"><img src="pictures/facebook-icon.png" alt="Facebook Icon"></a>
                    <a href="#"><img src="pictures/google-icon.png" alt="Google Icon"></a>
                    <a href="#"><img src="pictures/x-social-icon.png" alt="Twitter Icon"></a>
                </div>
            </div>
            <div class="team-member">
                <img src="pictures/FrontDesk.jpg" alt="Profile Picture">
                <p><b>Ziggy Zephyrblitz</b></p>
                <p><b>Position:</b> Front Desk Supervisor</p>
                <p>
                Meet Ziggy Zephyrblitz, our friendly Front Desk Supervisor who warmly welcomes guests to Moffat Bay Lodge. With a 
                welcoming smile and impeccable customer service skills, Ziggy Zephyrblitz ensures that your check-in process is 
                smooth and hassle-free. Whether you have questions about local attractions or need assistance during 
                your stay, Ziggy Zephyrblitz is always ready to assist with a genuine commitment to making your visit unforgettable.
                </p>
                <div class="social-icons">
                    <!-- Include your social media icons here as links -->
                    <a href="#"><img src="pictures/facebook-icon.png" alt="Facebook Icon"></a>
                    <a href="#"><img src="pictures/google-icon.png" alt="Google Icon"></a>
                    <a href="#"><img src="pictures/x-social-icon.png" alt="Twitter Icon"></a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="contact-section">
	    <h2>Contact Us</h2>
	    <button id="contact-button">Contact Us</button>
	    <form id="contact-form" action="ContactUsServlet" method="post">
	        <div class="form-group">
	            <label for="name">Name:</label>
	            <input type="text" id="name" name="name" required>
	        </div>
	        <div class="form-group">
	            <label for="phone">Phone Number:</label>
	            <input type="text" id="phone" name="phone">
	        </div>
	        <div class="form-group">
	            <label for="email">Email:</label>
	            <input type="email" id="email" name="email" required>
	        </div>
	        <div class="form-group">
	            <label for="message">What would you like to discuss?</label>
	            <textarea id="message" name="message" rows="4" required></textarea>
	        </div>
	        <div class="form-group">
	            <button type="submit">Submit</button>
	        </div>
	    </form>
	</div>
	<script>
		// JavaScript to toggle the visibility of the contact form and scroll to it
	    document.getElementById('contact-button').addEventListener('click', function() {
	        var form = document.getElementById('contact-form');
	        if (form.style.display === 'none') {
	            form.style.display = 'block';
	            // Scroll to the top of the form
	            form.scrollIntoView({ behavior: 'smooth', block: 'start' });
	        } else {
	            form.style.display = 'none';
        	}
    	});
    </script>	
</body>
</html>
