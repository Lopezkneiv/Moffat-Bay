// Robert Villarreal Silver Team
// Group Members: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal
package Connector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/PasswordResetServlet")
public class PasswordResetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("updatePassword".equals(action)) {
            updatePassword(request, response);
        } else {
            verifyUser(request, response);
        }
    }

    private void verifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE Username = ? AND Email_address = ?")) {
            
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username); // Store username in session
                response.sendRedirect("NewPassword.jsp"); // Page to enter new password
            } else {
                request.setAttribute("errorMessage", "Invalid username or email");
                request.getRequestDispatcher("PasswordReset.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection problem.");
        }
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String newPassword = request.getParameter("newPassword");

        if (username == null || newPassword == null) {
            response.sendRedirect("PasswordReset.jsp"); // Redirect if session is lost or password is null
            return;
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET Password = ? WHERE Username = ?")) {
            
            stmt.setString(1, hashedPassword);
            stmt.setString(2, username);
            int updated = stmt.executeUpdate();
            
            if (updated > 0) {
                response.sendRedirect("login.jsp"); // Redirect to login page after successful password reset
            } else {
                request.setAttribute("errorMessage", "Failed to update password");
                request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection problem.");
        }
    }
}
