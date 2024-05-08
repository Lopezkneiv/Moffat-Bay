// Robert Villarreal Silver Team
// Group Members: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal
package Connector;

import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/SocialLoginServlet")
public class SocialLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        @SuppressWarnings("unused")
		String source = request.getParameter("source"); // 'facebook' or 'google'

        if (email == null || password == null) {
            request.setAttribute("loginError", "Email or password cannot be empty");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/moffat_bay_lodge";
        String dbUsername = "team";
        String dbPassword = "silver";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                // Check if the email already exists
                PreparedStatement checkUser = conn.prepareStatement("SELECT Password FROM Users WHERE Email_address=?");
                checkUser.setString(1, email);
                ResultSet rs = checkUser.executeQuery();

                if (rs.next()) {
                    // User exists, verify the provided password
                    String storedPassword = rs.getString("Password");
                    if (BCrypt.checkpw(password, storedPassword)) {
                        // Password is correct, log in the user
                        HttpSession session = request.getSession();
                        session.setAttribute("user", email);
                        session.setAttribute("loginSuccess", true);
                        response.sendRedirect("index.jsp");
                    } else {
                        request.setAttribute("loginError", "Incorrect password. Please try again.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    // User does not exist, create a new account using email as username
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    String insertQuery = "INSERT INTO Users (Username, Password, Email_address) VALUES (?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(insertQuery);
                    stmt.setString(1, email); // Username is the same as email
                    stmt.setString(2, hashedPassword);
                    stmt.setString(3, email);

                    int rowsInserted = stmt.executeUpdate();
                    if (rowsInserted > 0) {
                        // Account created, log in the new user
                        HttpSession session = request.getSession();
                        session.setAttribute("user", email);
                        session.setAttribute("registrationSuccess", true);
                        response.sendRedirect("index.jsp");
                    } else {
                        request.setAttribute("loginError", "A problem occurred during account creation.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("loginError", "Database connection problem: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}