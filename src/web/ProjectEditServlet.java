package web;

import db.CustomerStorage;
import db.ProjectStorage;
import db.StorageCreator;
import domain.Customer;
import domain.Project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Денис on 07.02.2017.
 */
public class ProjectEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Integer id = null;

        try{
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){}
        if(id != null){
            StorageCreator storageCreator = null;
            try {
                storageCreator = new StorageCreator();
                ProjectStorage s = storageCreator.newProjectStorage();
                Project project = s.findById(id);
                req.setAttribute("project", project);

            }catch (SQLException e){
                throw new ServletException(e);
            }
            finally {
                if(storageCreator != null){
                    storageCreator.close();
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/Projects/project_edit.jsp").forward(req, resp);
    }

}
