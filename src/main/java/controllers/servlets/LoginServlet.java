package controllers.servlets;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginservlet", urlPatterns = {"", "/login"})
public class LoginServlet extends HttpServlet {

    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        PrintWriter writer = resp.getWriter();
        writer.println("My login is "+ login + " and my password is "+ password);
        req.getRequestDispatcher("/login.jsp").include(req, resp);
    }


}
