package org.progressoft.dynamicparserspting.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.progressoft.dynamicparserspting.connection.Encryption;
import org.progressoft.dynamicparserspting.connection.LoginRepo;
import org.progressoft.dynamicparserspting.connection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class Login {
    @Autowired
    private LoginRepo login;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected String getMap() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("create".equals(request.getParameter("btn"))) {
            request.getRequestDispatcher("/WEB-INF/views/createUser.jsp").forward(request, response);
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username + " " + Encryption.encrypt(password) + " " + password);
            if (login.existsById(username) && login.getById(username).getPassword() == Encryption.encrypt(password)) {
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("password", password);
                request.getRequestDispatcher("/WEB-INF/views/uploadPage.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Error");
                request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
            }
        }
    }

    @PostMapping
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username1 = req.getParameter("username1");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        if (username1 == null || username1.equals("")) {
            req.setAttribute("message", "message");
            req.getRequestDispatcher("/WEB-INF/views/createUser.jsp").forward(req, resp);
            return;
        }
        if (!password1.equals(password2)) {
            req.setAttribute("message", "message");
            req.getRequestDispatcher("/WEB-INF/views/createUser.jsp").forward(req, resp);
            return;
        }
        User user = new User(username1, Encryption.encrypt(password1));
        login.save(user);
    }
}
