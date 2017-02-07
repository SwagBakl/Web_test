package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.StorageCreator;
import db.UserStorage;
import domain.User;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login != null && password != null) {
            StorageCreator storageCreator = null;
            try {
                storageCreator = new StorageCreator();
                UserStorage s = storageCreator.newUserStorage();
                User user = s.readByLoginAndPassword(login, password);
                if(user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    if(user.getRole()==1){
                        resp.sendRedirect(req.getContextPath() + "/customers/customerTable.html");}

                    if(user.getRole()==2){
                    resp.sendRedirect(req.getContextPath() + "/manager_page.html");}

                    if(user.getRole()==3){
                        resp.sendRedirect(req.getContextPath() + "/index.html");}
                    return;
                } else {
                    req.setAttribute("error", true);
                }
            } catch(SQLException e) {
                throw new ServletException(e);
            } finally {
                if(storageCreator != null) {
                    storageCreator.close();
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }
}