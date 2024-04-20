// Robert Villarreal Silver Team
package Connector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/PasswordResetServlet")
public class PasswordResetServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try {
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            // Prepare the SQL statement to check the username and email
            String query = "SELECT * FROM Users WHERE Username = ? AND Email_address = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Username and email matched, redirect to page to enter new password
                HttpSession session = request.getSession();
                session.setAttribute("username", username); // Store username in session to use it later for password update
                response.sendRedirect("NewPassword.jsp"); // Page to enter new password
            } else {
                // Username and email didn't match, display an error
                request.setAttribute("errorMessage", "Invalid username or email");
                request.getRequestDispatcher("PasswordReset.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection problem.");
        }
    }
}
