// Robert Villarreal Silver Team
// Group Members: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal
package Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement stmtUserCheck = conn.prepareStatement("SELECT Password FROM Users WHERE Email_address = ?");
            
            stmtUserCheck.setString(1, email);
            ResultSet rsUserCheck = stmtUserCheck.executeQuery();
            
            if (rsUserCheck.next()) {
                String storedPassword = rsUserCheck.getString("Password");  // This is the hashed password from the database
                
                if (BCrypt.checkpw(password, storedPassword)) {  // Use BCrypt to check the password
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
            rsUserCheck.close();
            stmtUserCheck.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("loginError", "Database connection problem.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
