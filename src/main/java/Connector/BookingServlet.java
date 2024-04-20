// Robert Villarreal Silver Team
package Connector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";  
        String dbPassword = "silver";  

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            // Check and create tables if they don't exist
            checkAndCreateTables(conn);

            // Insert general booking details and get reservation ID
            int reservationId = insertGeneralBookingDetails(request, conn);

            // Insert activity details for each activity
            insertActivityDetails(request, conn);

            // Store reservation ID in session
            request.getSession().setAttribute("reservationId", reservationId);
            
         // Store reservation ID and booking details in session
            HttpSession session = request.getSession();
            session.setAttribute("reservationId", reservationId);
            session.setAttribute("number_of_rooms", request.getParameter("number_of_rooms"));
            session.setAttribute("number_of_beds", request.getParameter("number_of_beds"));
            session.setAttribute("number_of_adults", request.getParameter("number_of_adults"));
            session.setAttribute("number_of_children", request.getParameter("number_of_children"));


            // Redirect or forward to a confirmation page
            response.sendRedirect("Success.jsp"); // Adjust according to your needs
        } catch (Exception e) {
            throw new ServletException("Error processing booking", e);
        }
    }

    private void checkAndCreateTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String sqlCreateGeneral = "CREATE TABLE IF NOT EXISTS general_booking_details (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "number_of_rooms INT NOT NULL," +
                "number_of_beds INT NOT NULL," +
                "number_of_adults INT NOT NULL," +
                "number_of_children INT NOT NULL," +
                "rooms_booked BOOLEAN NOT NULL)";
            stmt.execute(sqlCreateGeneral);

            String sqlCreateActivity = "CREATE TABLE IF NOT EXISTS activity_booking_details (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "activity_name VARCHAR(255) NOT NULL," +
                "activity_date DATE NOT NULL," +
                "activity_time TIME NOT NULL," +
                "number_of_adults INT NOT NULL," +
                "number_of_children INT NOT NULL)";
            stmt.execute(sqlCreateActivity);
        }
    }

    private int insertGeneralBookingDetails(HttpServletRequest request, Connection conn) throws SQLException {
        String sqlInsertGeneral = "INSERT INTO general_booking_details (number_of_rooms, number_of_beds, number_of_adults, number_of_children, rooms_booked) VALUES (?, ?, ?, ?, ?)";
        ResultSet rs = null;
        int reservationId = 0;
        
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertGeneral, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, Integer.parseInt(request.getParameter("number_of_rooms")));
            pstmt.setInt(2, Integer.parseInt(request.getParameter("number_of_beds")));
            pstmt.setInt(3, Integer.parseInt(request.getParameter("number_of_adults")));
            pstmt.setInt(4, Integer.parseInt(request.getParameter("number_of_children")));
            pstmt.setBoolean(5, true);
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                reservationId = rs.getInt(1); // Retrieve the first column as ID
            }
        } finally {
            if (rs != null) rs.close();
        }
        return reservationId;
    }

    private void insertActivityDetails(HttpServletRequest request, Connection conn) throws SQLException {
        String[] activities = {"Snorkeling", "Parasailing", "Aquarium", "Dinner and Show", "Spa", "Couples Massage"};
        String sqlInsertActivity = "INSERT INTO activity_booking_details (activity_name, activity_date, activity_time, number_of_adults, number_of_children) VALUES (?, ?, ?, ?, ?)";
        List<String> bookedActivities = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertActivity)) {
            for (String activity : activities) {
                String skipActivity = request.getParameter(activity + "_skip");
                if ("true".equals(skipActivity)) {
                    continue; // Skip this activity if the "Do Not Book" checkbox is checked
                }
                String activityDate = request.getParameter(activity + "_date");
                String activityTime = request.getParameter(activity + "_time") + ":00";
                int adults = Integer.parseInt(request.getParameter(activity + "_adults"));
                int children = Integer.parseInt(request.getParameter(activity + "_children"));

                pstmt.setString(1, activity);
                pstmt.setDate(2, Date.valueOf(activityDate));
                pstmt.setTime(3, Time.valueOf(activityTime));
                pstmt.setInt(4, adults);
                pstmt.setInt(5, children);
                pstmt.executeUpdate();

                // Save for session
                bookedActivities.add(activity + " on " + activityDate + " at " + activityTime + ", Adults: " + adults + ", Children: " + children);
            }
        }

        // Store the list of booked activities in the session
        HttpSession session = request.getSession();
        session.setAttribute("bookedActivities", bookedActivities);
    }
    }