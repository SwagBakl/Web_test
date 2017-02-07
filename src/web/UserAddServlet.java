package web;

import db.CustomerStorage;
import db.StorageCreator;
import db.UserStorage;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Денис on 07.02.2017.
 */
public class UserAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));

        }catch (NumberFormatException e){}
        if(id!=null){
            StorageCreator storageCreator = null;
            try {
                storageCreator = new StorageCreator();
                UserStorage us = storageCreator.newUserStorage();
                User user = us.findById(id);
                req.setAttribute("user", user);
            }catch (SQLException e){
                throw new ServletException(e);
            }
            finally{
                if(storageCreator != null){
                    storageCreator.close();
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(req, resp);
    }

}
