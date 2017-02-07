package web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserStorage;
import db.StorageCreator;
import domain.User;


public class UserSaveServlet extends HttpServlet {
    //private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = buildUser(req);
        if(user != null) {
            StorageCreator storageCreator = null;
            try {
                storageCreator = new StorageCreator();
                UserStorage s = storageCreator.newUserStorage();
                s.save(user);
            } catch(SQLException e) {
                throw new ServletException(e);
            } finally {
                if(storageCreator != null) {
                    storageCreator.close();
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    private User buildUser(HttpServletRequest req) {
        try {
            User user = new User();
            try {
                user.setId(Integer.parseInt(req.getParameter("id")));
            } catch(NumberFormatException e) {}
            user.setName(req.getParameter("name"));
            user.setSecond_name(req.getParameter("second_name"));
            user.setMiddle_name(req.getParameter("middle_name"));
            user.setLogin(req.getParameter("login"));
            if(user.getName() == null || user.getSecond_name() == null) {
                throw new NullPointerException();
            }
            user.setPassword(req.getParameter("password"));
            user.setRole(Integer.parseInt(req.getParameter("role")));
            return user;
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }
}
