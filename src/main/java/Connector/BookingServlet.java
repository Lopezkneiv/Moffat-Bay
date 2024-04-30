// Robert Villarreal Silver Team
// Group Members: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal
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
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                // Check and create tables if they don't exist
                checkAndCreateTables(conn);

                // Fetch the last reservation_id from reservations table
                int reservationId = fetchLastReservationId(conn);

                // Insert general booking details with reservation ID
                insertGeneralBookingDetails(request, conn, reservationId);

                // Insert activity details for each activity, passing the reservation ID
                insertActivityDetails(request, conn, reservationId);

                // Store reservation ID in session
                HttpSession session = request.getSession();
                session.setAttribute("reservation_Id", reservationId);
                session.setAttribute("number_of_rooms", request.getParameter("number_of_rooms"));
                session.setAttribute("number_of_beds", request.getParameter("number_of_beds"));
                session.setAttribute("number_of_adults", request.getParameter("number_of_adults"));
                session.setAttribute("number_of_children", request.getParameter("number_of_children"));

                // Redirect or forward to a confirmation page
                response.sendRedirect("Success.jsp"); // Adjust according to your needs
            }
        } catch (Exception e) {
            throw new ServletException("Error processing booking", e);
        }
    }

    private int fetchLastReservationId(Connection conn) throws SQLException {
        String sql = "SELECT reservation_id FROM reservations WHERE reservation_id IS NOT NULL ORDER BY reservation_id DESC LIMIT 1";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("reservation_id");
            } else {
                throw new SQLException("No valid reservation_id found in reservations table.");
            }
        }
    }
	private void checkAndCreateTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String sqlCreateGeneral = "CREATE TABLE IF NOT EXISTS general_booking_details (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "reservation_id INT NOT NULL," +
                "number_of_rooms INT NOT NULL," +
                "number_of_beds INT NOT NULL," +
                "number_of_adults INT NOT NULL," +
                "number_of_children INT NOT NULL," +
                "rooms_booked BOOLEAN NOT NULL)";
            stmt.execute(sqlCreateGeneral);

            String sqlCreateActivity = "CREATE TABLE IF NOT EXISTS activity_booking_details (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "reservation_id INT NOT NULL," +
                "activity_name VARCHAR(255) NOT NULL," +
                "activity_date DATE NOT NULL," +
                "activity_time TIME NOT NULL," +
                "number_of_adults INT NOT NULL," +
                "number_of_children INT NOT NULL," +
                "FOREIGN KEY (reservation_id) REFERENCES general_booking_details(reservation_id))";
            stmt.execute(sqlCreateActivity);
        }
    }

    private void insertGeneralBookingDetails(HttpServletRequest request, Connection conn, int reservationId) throws SQLException {
        String sqlInsertGeneral = "INSERT INTO general_booking_details (reservation_id, number_of_rooms, number_of_beds, number_of_adults, number_of_children, rooms_booked) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertGeneral, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, reservationId);
            pstmt.setInt(2, Integer.parseInt(request.getParameter("number_of_rooms")));
            pstmt.setInt(3, Integer.parseInt(request.getParameter("number_of_beds")));
            pstmt.setInt(4, Integer.parseInt(request.getParameter("number_of_adults")));
            pstmt.setInt(5, Integer.parseInt(request.getParameter("number_of_children")));
            pstmt.setBoolean(6, true);
            pstmt.executeUpdate();
        }
    }

    private List<String> insertActivityDetails(HttpServletRequest request, Connection conn, int reservationId) throws SQLException {
        String[] activities = {"Snorkeling", "Parasailing", "Aquarium", "Dinner and Show", "Spa", "Couples Massage"};
        String sqlInsertActivity = "INSERT INTO activity_booking_details (reservation_id, activity_name, activity_date, activity_time, number_of_adults, number_of_children) VALUES (?, ?, ?, ?, ?, ?)";
        List<String> bookedActivities = new ArrayList<>();

        try {
            // Disable auto-commit for the connection
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertActivity)) {
                for (String activity : activities) {
                    String skipActivity = request.getParameter(activity + "_skip");
                    if ("true".equals(skipActivity)) continue;

                    String activityDate = request.getParameter(activity + "_date");
                    String activityTime = request.getParameter(activity + "_time") + ":00";
                    int adults = Integer.parseInt(request.getParameter(activity + "_adults"));
                    int children = Integer.parseInt(request.getParameter(activity + "_children"));

                    pstmt.setInt(1, reservationId);
                    pstmt.setString(2, activity);
                    pstmt.setDate(3, Date.valueOf(activityDate));
                    pstmt.setTime(4, Time.valueOf(activityTime));
                    pstmt.setInt(5, adults);
                    pstmt.setInt(6, children);
                    pstmt.addBatch();

                    bookedActivities.add(activity + " on " + activityDate + " at " + activityTime + ", Adults: " + adults + ", Children: " + children);
                }
                // Execute batch
                pstmt.executeBatch();
                // Commit transaction
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert activity details", e);
        }

        // Store the list of booked activities in the session
        HttpSession session = request.getSession();
        session.setAttribute("bookedActivities", bookedActivities);
        return bookedActivities;
    }
}