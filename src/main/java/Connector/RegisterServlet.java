// Robert Villarreal Silver Team
package Connector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if (!password.equals(repassword)) {
            // Set an error message and forward back to the registration page
            request.setAttribute("registrationError", "Passwords do not match");
            request.getRequestDispatcher("RegistrationForm.jsp").forward(request, response);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";  
        String dbPassword = "silver";  

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO Users (Username, Password, Email_address) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);  
            stmt.setString(3, email);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("Login.jsp");
            } else {
                
                response.getWriter().println("A problem occurred during registration.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            
            response.getWriter().println("Database connection problem.");
        }
    }
}
