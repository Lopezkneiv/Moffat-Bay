// Robert Villarreal Silver Team
package Connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";  
        String dbPassword = "silver";  

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("Email");

        if (email == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is not present in session. Please log in again.");
            return;
        }

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            conn.setAutoCommit(false); // Start transaction

            // Fetch user ID using email
            Integer userId = null;
            String fetchUserIdSql = "SELECT User_Id FROM Users WHERE Email_address = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(fetchUserIdSql)) {
                pstmt.setString(1, email);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getInt("User_Id");
                    } else {
                        throw new SQLException("No user found with the given email.");
                    }
                }
            }

            // Rest of the code to insert payment and reservation details using userId
            String sqlPayment = "INSERT INTO Payment (User_Id, Card_payment, Card_name, Expire_date, Cvv, Home_address) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlPayment)) {
                pstmt.setInt(1, userId);
                pstmt.setString(2, request.getParameter("cardPayment"));
                pstmt.setString(3, request.getParameter("cardName"));
                pstmt.setString(4, request.getParameter("expireDate"));
                pstmt.setString(5, request.getParameter("cvv"));
                pstmt.setString(6, request.getParameter("homeAddress"));
                pstmt.executeUpdate();
            }

            String sqlReservation = "INSERT INTO Reservation (User_Id, Check_In, Check_Out) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlReservation)) {
                pstmt.setInt(1, userId);
                pstmt.setString(2, request.getParameter("checkIn"));
                pstmt.setString(3, request.getParameter("checkOut"));
                pstmt.executeUpdate();
            }

            conn.commit(); // Commit all changes
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Ensure connection is closed
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (session.getAttribute("Email") == null) {
                response.sendRedirect("login.jsp"); // Assuming 'login.jsp' is your login page
                return;
            }

        }
    }
}


