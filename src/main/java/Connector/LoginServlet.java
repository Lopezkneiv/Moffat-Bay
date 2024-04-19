// Robert Villarreal Silver Team
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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Ensure the driver is registered
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement stmtUserCheck = conn.prepareStatement("SELECT * FROM Users WHERE Email_address = ?");
            
            stmtUserCheck.setString(1, email);
            ResultSet rsUserCheck = stmtUserCheck.executeQuery();
            
            if (rsUserCheck.next()) {
                String storedPassword = rsUserCheck.getString("Password");  // Adjusted to handle possible hash
                
                if (storedPassword.equals(password)) {  // Consider password hashing check here
                    HttpSession session = request.getSession();
                    session.setAttribute("user", email);
                    
                    if (rememberMe) {
                        Cookie c = new Cookie("userEmail", email);
                        c.setMaxAge(60 * 60 * 24 * 14); // 14 days
                        c.setHttpOnly(true);  // Important: Mitigate XSS attack vector
                        c.setSecure(true);    // Ensure the cookie is only sent over HTTPS
                        response.addCookie(c);
                    }

                    response.sendRedirect("index.jsp");
                } else {
                    request.setAttribute("loginError", "Invalid password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("loginError", "Email address not found.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("loginError", "Database connection problem.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
