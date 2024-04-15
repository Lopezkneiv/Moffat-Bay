package Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password"); 
        boolean rememberMe = "on".equals(request.getParameter("rememberMe"));

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement stmtUserCheck = conn.prepareStatement("SELECT * FROM Users WHERE Email_address = ?")) {
            
            stmtUserCheck.setString(1, email);
            ResultSet rsUserCheck = stmtUserCheck.executeQuery();
            
            if (rsUserCheck.next()) {
                // Email exists, now check password
                String storedPassword = rsUserCheck.getString("Password"); // Assuming 'Password' is the name of your column
                
                if (storedPassword.equals(password)) {
                    // Login success: set session or cookie as needed
                    HttpSession session = request.getSession();
                    session.setAttribute("user", email);
                    
                    if (rememberMe) {
                        Cookie c = new Cookie("userEmail", email);
                        c.setMaxAge(60 * 60 * 24 * 14); // 14 days
                        response.addCookie(c);
                    }

                    // Redirect to user's home page or dashboard
                    response.sendRedirect("index.jsp");
                } else {
                    // Correct email but wrong password
                    request.setAttribute("loginError", "The email entered does not exist or the password is incorrect");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            } else {
                // Email does not exist
                request.setAttribute("loginError", "The email entered does not exist or the password is incorrect");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection problem.");
        }
    }
}
