// Robert Villarreal Silver Team
// Group Members: Nathan Le, Ivan Lopez-Kne, Trevor Michaels, Keith Olsen, Robert Villarreal
package Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
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
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                // Check if the username or email already exists
                PreparedStatement checkUser = conn.prepareStatement("SELECT * FROM Users WHERE Username=? OR Email_address=?");
                checkUser.setString(1, username);
                checkUser.setString(2, email);
                ResultSet rs = checkUser.executeQuery();

                if (rs.next()) {
                    request.setAttribute("registrationError", "Username or Email already exists");
                    request.getRequestDispatcher("RegistrationForm.jsp").forward(request, response);
                    return;
                }

                // Insert the new user into the database
                String query = "INSERT INTO Users (Username, Password, Email_address) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);  
                stmt.setString(3, email);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    response.sendRedirect("Login.jsp");
                } else {
                    request.setAttribute("registrationError", "A problem occurred during registration.");
                    request.getRequestDispatcher("RegistrationForm.jsp").forward(request, response);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("registrationError", "Database connection problem: " + e.getMessage());
            request.getRequestDispatcher("RegistrationForm.jsp").forward(request, response);
        }
    }
}
