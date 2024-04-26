// Robert Villarreal Silver Team
package Connector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

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
        String query = "SELECT * FROM Reservations WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservationId);
            processResults(pstmt, request);
        }
    }

    private void lookupByEmail(Connection conn, String email, HttpServletRequest request) throws SQLException {
        String query = "SELECT * FROM Reservations JOIN Users ON Reservations.Username = Users.Username WHERE Users.Email_address = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            processResults(pstmt, request);
        }
    }

    private void processResults(PreparedStatement pstmt, HttpServletRequest request) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                request.setAttribute("reservation_Id", rs.getInt("reservation_id"));
                request.setAttribute("checkInDate", rs.getDate("start_date").toString());
                request.setAttribute("checkOutDate", rs.getDate("end_date").toString());
                // additional attributes
            } else {
                request.setAttribute("error", "No reservation found with given details.");
            }
        }
    }
}


