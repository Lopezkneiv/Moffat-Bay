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
@WebServlet("/ReservationLookupServlet")
public class ReservationLookupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchInput = request.getParameter("searchInput"); // Get the input from the form

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                if (searchInput.matches("\\d+")) { // Check if input is purely numeric
                    lookupByReservationId(conn, Integer.parseInt(searchInput), request);
                } else if (searchInput.contains("@")) { // Simple check to see if it might be an email
                    lookupByEmail(conn, searchInput, request);
                } else {
                    request.setAttribute("error", "Invalid input format.");
                    request.getRequestDispatcher("/ViewReservations.jsp").forward(request, response);
                    return;
                }

                if (request.getAttribute("error") == null) {
                    request.getRequestDispatcher("/reservationDetails.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/ViewReservations.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Database connection problem: " + e.getMessage());
            request.getRequestDispatcher("/ViewReservations.jsp").forward(request, response);
        }
    }

    private void lookupByReservationId(Connection conn, int reservationId, HttpServletRequest request) throws SQLException {
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservationId);
            processResults(conn, pstmt, request);
        }
    }

    private void lookupByEmail(Connection conn, String email, HttpServletRequest request) throws SQLException {
        String query = "SELECT * FROM reservations JOIN users ON reservations.Username = users.Username WHERE users.Email_address = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            processResults(conn, pstmt, request);
        }
    }

    private void processResults(Connection conn, PreparedStatement pstmt, HttpServletRequest request) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                request.setAttribute("reservation_Id", reservationId);
                request.setAttribute("checkInDate", rs.getDate("start_date").toString());
                request.setAttribute("checkOutDate", rs.getDate("end_date").toString());

                // Fetch general booking details associated with this reservation
                try (PreparedStatement pstmtGeneral = conn.prepareStatement(
                        "SELECT * FROM general_booking_details WHERE reservation_id = ?")) {
                    pstmtGeneral.setInt(1, reservationId);
                    try (ResultSet rsGeneral = pstmtGeneral.executeQuery()) {
                        if (rsGeneral.next()) {
                            request.setAttribute("number_of_rooms", rsGeneral.getInt("number_of_rooms"));
                            request.setAttribute("number_of_beds", rsGeneral.getInt("number_of_beds"));
                            request.setAttribute("number_of_adults", rsGeneral.getInt("number_of_adults"));
                            request.setAttribute("number_of_children", rsGeneral.getInt("number_of_children"));
                        }
                    }
                }

                // Fetch activity booking details associated with this reservation
                try (PreparedStatement pstmtActivity = conn.prepareStatement(
                        "SELECT * FROM activity_booking_details WHERE reservation_id = ?")) {
                    pstmtActivity.setInt(1, reservationId);
                    try (ResultSet rsActivity = pstmtActivity.executeQuery()) {
                        List<String> activities = new ArrayList<>();
                        while (rsActivity.next()) {
                            String activityDetails = "Activity: " + rsActivity.getString("activity_name") +
                                                     ", Date: " + rsActivity.getDate("activity_date").toString() +
                                                     ", Time: " + rsActivity.getTime("activity_time").toString();
                            activities.add(activityDetails);
                        }
                        request.setAttribute("activities", activities);
                    }
                }
            } else {
                request.setAttribute("error", "No reservation found with given details.");
            }
        }
    }
}
    