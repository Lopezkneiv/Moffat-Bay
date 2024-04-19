// Robert Villarreal Silver Team
package Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("checkInDate");
        String endDate = request.getParameter("checkOutDate");
        String bookingType = request.getParameter("bookingType");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");  

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";
        
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            DatabaseMetaData dbm = (DatabaseMetaData) connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "reservations", null);
            if (!tables.next()) {
                try (Statement statement = connection.createStatement()) {
                    String sqlCreate = "CREATE TABLE reservations (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT," +
                            "start_date DATE NOT NULL," +
                            "end_date DATE NOT NULL," +
                            "booking_type VARCHAR(10) NOT NULL)";
                    statement.executeUpdate(sqlCreate);
                }
            }

            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            long daysBetween = ChronoUnit.DAYS.between(start, end);
            boolean isValidRange = true;  

            switch (bookingType) {
                case "daily":
                    // For daily bookings, any duration of at least one day is valid.
                    if (daysBetween < 1) {
                        isValidRange = false;
                    }
                    break;
                case "weekly":
                    // For weekly bookings, the duration must be at least 7 days.
                    if (daysBetween < 7) {
                        isValidRange = false;
                    }
                    break;
                case "monthly":
                    // For monthly bookings, the duration must be at least 30 days.
                    if (daysBetween < 30) {
                        isValidRange = false;
                    }
                    break;
                default:
                    // Any unsupported booking type is considered invalid.
                    isValidRange = false;
                    break;
            }

            if (!isValidRange) {
                // If the date range is not valid, set an error message in the request and forward back to the form page.
                request.setAttribute("errorMessage", "Invalid date range for the selected booking option.");
                request.getRequestDispatcher("Reservation.jsp").forward(request, response);
                return;
            }

            String sqlInsert = "INSERT INTO reservations (start_date, end_date, booking_type) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
                preparedStatement.setDate(1, Date.valueOf(startDate));
                preparedStatement.setDate(2, Date.valueOf(endDate));
                preparedStatement.setString(3, bookingType);
                preparedStatement.executeUpdate();
            }

            session.setAttribute("checkInDate", startDate);
            session.setAttribute("checkOutDate", endDate);
            session.setAttribute("user", username);  

            response.sendRedirect("booking.jsp");
        } catch (SQLException e) {
            throw new ServletException("Database connection problem", e);
        }
    }
}

