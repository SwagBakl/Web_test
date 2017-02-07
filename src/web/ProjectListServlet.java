package web;

import db.Connector;
import db.ProjectStorage;
import domain.Project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Денис on 02.02.2017.
 */

public class ProjectListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        Connection connection = null;
        try {
            Connector.init(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/bank_roll"
                            + "?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    "root"
            );
            connection = Connector.getConnection();
            ProjectStorage s = new ProjectStorage();
            s.setConnection(connection);
            List<Project> projects = s.readAll();
            req.setAttribute("projects",projects);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/Projects/project_index.jsp").forward(req,resp);
        }catch (ClassNotFoundException | SQLException e){
            throw new ServletException();
        }finally {
            try { connection.close(); } catch (NullPointerException | SQLException e) {}
        }

    }

}
