package org.progressoft.dynamicparserspting.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.progressoft.dynamicparserspting.connection.DatabaseConnection;
import org.progressoft.dynamicparserspting.connection.Encryption;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class Login {
    @RequestMapping(value="/login", method = RequestMethod.GET)
    protected String getMap() {
        return "index";
    }

    @GetMapping
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btn").equals("create")) {
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username + " " + Encryption.encrypt(password) + " " + password);
            try {
                Connection connection = new DatabaseConnection("mydb").setConnection();
                String sql = "SELECT username, password FROM login_table WHERE username LIKE ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                ResultSet set = statement.executeQuery();
                if (set.next() && validateUserName(username, set.getString(1)) &&
                        Encryption.encrypt(password) == (set.getInt(2))) {
                    request.getSession().setAttribute("username", username);
                    request.getSession().setAttribute("password", password);
                    request.getRequestDispatcher("uploadPage.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "message");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean validateUserName(String username, String getName) {
        return username.equals(getName);
    }
    @PostMapping
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username1 = req.getParameter("username1");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        if (username1 == null || username1.equals("")) {
            req.setAttribute("message", "message");
            req.getRequestDispatcher("createUser.jsp").forward(req, resp);
            return;
        }
        if (!password1.equals(password2)) {
            req.setAttribute("message", "message");
            req.getRequestDispatcher("createUser.jsp").forward(req, resp);
            return;
        }
        try {
            Connection connection = new DatabaseConnection("mydb").setConnection();
            String sql = "insert into login_table values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username1);
            statement.setInt(2, Encryption.encrypt(password1));
            statement.executeUpdate();
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
