package Connector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
            return;
        }

        
        String hashedPassword = hashPassword(newPassword);

        
        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "UPDATE Users SET Password = ? WHERE Username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, hashedPassword);
                stmt.setString(2, username);
                
                int updated = stmt.executeUpdate();
                if (updated > 0) {
                    response.sendRedirect("Login.jsp"); // Redirect to login after success
                } else {
                    request.setAttribute("errorMessage", "Failed to update password");
                    request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection problem.");
        }
    }
    
    private String hashPassword(String password) {
       
        return password; 
    }
}
