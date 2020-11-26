package ica.oose.controller;

import ica.oose.datasource.dao.LoginDAOMySQL;
import ica.oose.domain.User;
import ica.oose.service.LoginService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Kevin on 30-3-2017.
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    @Inject
    LoginService loginService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String owner = request.getParameter("username");
        User user = loginService.login(owner);
        if(user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/playlists");
        }
        else {
            request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/error").forward(request, response);
        }
    }
}
