package web;

import db.ProjectStorage;
import db.StorageCreator;
import domain.Project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Денис on 19.02.2017.
 */
public class ProjectAddServlet extends HttpServlet {

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

            Integer id = null;
            try {
                id = Integer.parseInt(req.getParameter("id"));

            }catch (NumberFormatException e){}
            if(id!=null){
                StorageCreator storageCreator = null;
                try {
                    storageCreator = new StorageCreator();
                    ProjectStorage ps = storageCreator.newProjectStorage();
                    Project project = ps.findById(id);
                    req.setAttribute("project", project);
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
