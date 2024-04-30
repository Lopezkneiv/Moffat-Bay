// Robert Villarreal Silver Team
// Group Members: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal
package Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    private static final String reservation_id = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("checkInDate");
        String endDate = request.getParameter("checkOutDate");
        String bookingType = request.getParameter("bookingType");

        HttpSession session = request.getSession();
        String userEmail = null;

        // Retrieve user email from cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userEmail".equals(cookie.getName())) {
                    userEmail = cookie.getValue();
                    break;
                }
            }
        }

        if (userEmail == null) {
            request.setAttribute("errorMessage", "You must be logged in to make a reservation.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC driver not found.", e);
        }

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String username = fetchUsernameFromEmail(connection, userEmail);
            if (username == null) {
                request.setAttribute("errorMessage", "User not found. Please log in again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            long daysBetween = ChronoUnit.DAYS.between(start, end);
            boolean isValidRange = true;

            switch (bookingType) {
                case "daily":
                    if (daysBetween < 1) isValidRange = false;
                    break;
                case "weekly":
                    if (daysBetween < 7) isValidRange = false;
                    break;
                case "monthly":
                    if (daysBetween < 30) isValidRange = false;
                    break;
                default:
                    isValidRange = false;
                    break;
            }

            if (!isValidRange) {
                request.setAttribute("errorMessage", "Invalid date range for the selected booking option.");
                request.getRequestDispatcher("Reservation.jsp").forward(request, response);
                return;
            }
            // Store dates in session after validating the range but before making the database insert
            session.setAttribute("checkInDate", startDate);
            session.setAttribute("checkOutDate", endDate);

            String sqlInsert = "INSERT INTO reservations (reservation_id, start_date, end_date, booking_type, Username) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
                preparedStatement.setString(1, reservation_id);
                preparedStatement.setDate(2, Date.valueOf(startDate));
                preparedStatement.setDate(3, Date.valueOf(endDate));
                preparedStatement.setString(4, bookingType);
                preparedStatement.setString(5, username);
                
                preparedStatement.executeUpdate();
            }

            response.sendRedirect("booking.jsp");
        } catch (SQLException e) {
            throw new ServletException("Database connection problem", e);
        }
    }

    private String fetchUsernameFromEmail(Connection conn, String email) throws SQLException {
        String sql = "SELECT Username FROM Users WHERE Email_address = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Username");
                }
            }
        }
        return null;
    }
}


