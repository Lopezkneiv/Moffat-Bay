<!-- Robert Villarreal Silver Team -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moffat Bay Lodge Spa</title>
        <style>
        /* Apply background color to the entire page */
        body {
            background-color: #e0f7fa;
        }
                /* Centering the main heading */
        h1 {
            text-align: center;
        }
        
        
        /* Resize images to be smaller */
        img {
            width: 100%; /* Adjust width as necessary */
            max-width: 300px; /* Set maximum width of images */
            height: auto; /* Maintain aspect ratio */
        }

        /* Style for the div containing images and descriptions */
        div {
            margin: 10px;
            padding: 10px;
            border: 2px solid #cccccc;
            background-color: #ffffff;
            box-shadow: 2px 2px 5px #aaaaaa;
        }

        /* Footer styling */
        footer {
            margin-top: 20px;
            padding: 10px;
            text-align: center;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
    <h1>Spa Treatments</h1>

    <div>
        <img src="pictures/facial.jpg" alt="Facials">
        <p>Photo by Anna Shvets<br>
        Facials typically involve cleansing, exfoliating, and moisturizing the skin. There are various types of facials available, tailored to different skin types and concerns.</p>
    </div>
    <div>
        <img src="pictures/body.jpg" alt="Body treatment">
        <p>Image by <a href="https://www.freepik.com/free-photo/spa-concept-with-woman_2258432.htm#query=spa%20body%20wrap&position=0&from_view=keyword&track=ais&uuid=deaa9452-4a27-49df-a6f0-e76b72598771">Freepik</a><br>
        This include body scrubs, body wraps, and mud baths. They are designed to exfoliate, moisturize, and detoxify the body.</p>
    </div>
    <div>
        <img src="pictures/sauna.jpg" alt="Sauna and steam room">
        <p> Photo by Pixabay<br>
        Our spa offer access to saunas or steam rooms, which help to relax the muscles and cleanse the skin through sweating.</p>
    </div>
        <div>
        <img src="pictures/hydrotherapy.jpg" alt="Hydrotherapy">
        <p>Photo by Jonathan Borba <br>
        This includes treatments like hot tubs, whirlpools, and sometimes even sensory deprivation tanks. Hydrotherapy uses water to relax and heal the body.</p>
    </div>
    <div>
        <img src="pictures/beauty.jpg" alt="Beauty Treatments">
        <p> Photo by cottonbro studio<br>
        We also offer a range of beauty services such as manicures, pedicures, waxing, and hair treatments.</p>
    </div>
        <div>
        <img src="pictures/meditation.jpg" alt="Mindfulness and Meditation">
        <p> Photo by cottonbro studio<br> 
        Our spa focus on the holistic aspect of wellness, offering classes or spaces for meditation, yoga, and other mindfulness practices.</p>
    </div>
    <div>
        <img src="pictures/fitness.jpg" alt="Fitness">
        <p> Photo by Karl Solano<br>
        Some wellness spas might include fitness activities such as guided workouts, yoga classes, or access to a gym.</p>
    </div>
        <div>
        <img src="pictures/massage.jpg" alt="Massages">
        <p> Photo by Sergey Torbik<br>
        This is perhaps the most well-known spa treatment. Massages can range from gentle Swedish massages to deeper tissue massages, hot stone massages, and aromatherapy massages.</p>
    </div>
    
    <footer>
        <h2>Enjoy our Spa</h2>
        <p>Thank you for choosing our Spa. We hope you enjoy these special moments together and leave feeling better than ever!</p>
    </footer>
</body>
</html>