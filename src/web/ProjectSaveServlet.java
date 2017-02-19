package web;

import db.ProjectStorage;
import db.StorageCreator;
import db.UserStorage;
import domain.Project;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Денис on 16.02.2017.
 */
public class ProjectSaveServlet extends HttpServlet{

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Project project = buildProject(req);
        if(project != null) {
            StorageCreator storageCreator = null;
            try {
                storageCreator = new StorageCreator();
                ProjectStorage s = storageCreator.newProjectStorage();
                s.save(project);
            } catch(SQLException e) {
                throw new ServletException(e);
            } finally {
                if(storageCreator != null) {
                    storageCreator.close();
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/Projects/projectTable.html");
    }

    private Project buildProject(HttpServletRequest req) {
        try {
            Project project = new Project();
            try {
                project.setId(Integer.parseInt(req.getParameter("id")));
            } catch(NumberFormatException e) {}
            project.setName(req.getParameter("name"));
            project.setDate_begin((Date) DATE_FORMAT.parse(req.getParameter("begin_date")));
            project.setDate_planned_finish((Date) DATE_FORMAT.parse(req.getParameter("date_planned_finish")));
            project.setDate_finish((Date) DATE_FORMAT.parse(req.getParameter("date_finish")));
            project.setProject_seccess(req.getParameter("project_seccess"));
            project.setCustomer_id(Integer.parseInt(req.getParameter("customer_id")));
            return project;
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException | ParseException e) {
            return null;
        }
    }
}
