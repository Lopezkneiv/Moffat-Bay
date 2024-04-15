/*Created By: Keith Olsen
 * Created On: 04/15/2024
 * Description: Servlet to handle emailing the contact us information.
*/
package Connector;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;

import java.io.IOException;
import java.util.Properties;

@WebServlet("/ContactUsServlet")
public class ContactUsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Send email
        String to = "YOUREMAIL@YOURMAIL.com"; //Replace with your email for testing.
        String subject = "Contact Us Form Submission";
        String body = "Name: " + name + "\n";
        body += "Phone Number: " + phone + "\n";
        body += "Email: " + email + "\n";
        body += "Message: " + message + "\n";

        sendEmail(to, subject, body);

        // Add Redirect to a thank you page or display a confirmation message
     
        response.sendRedirect("thankyou.jsp");
    }

    private void sendEmail(String to, String subject, String body) {
        // Configure email properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Create session
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("YOUREMAIL@YOURMAIL.com", "YOURPASSWORD"); //RESET to "YOUREMAIL@YOURMAIL.com", "YOURPASSWORD" before commiting in git.
                /*
                 * For gmail generate an app-specific password for this Java application. This is more secure than using your regular Google account password.
				 * Go to your Google account settings: https://myaccount.google.com/
				 * Navigate to the "Security" tab.
				 * Scroll down to the "Signing in to Google" section and click on "App passwords".
                 * Generate a new app password for this Java application. This password is specific to this application and can be used instead of your regular Google account password.
                 * REPLACE YOUREMAIL@YOURMAIL.com
                 */
            }
        });

        try {
            // Create message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("YOUREMAIL@YOURMAIL.com")); //Replace with your email for testing.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully");
        } catch (MessagingException mex) {
        	 System.err.println("Failed to send email. Error: " + mex.getMessage());
        }
    }
}
