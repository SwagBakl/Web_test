package web;

import db.Connector;
import db.UserStorage;
import domain.User;

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
public class ManagerPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Connection c = null;
        try {
            Connector.init(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/bank_roll"
                            + "?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    "root"
            );
            c = Connector.getConnection();

            getServletContext().getRequestDispatcher("/WEB-INF/jsp/manager_page.jsp")
                    .forward(req, resp);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        } finally {
            try { c.close(); } catch (NullPointerException | SQLException e) {}
        }
    }

}
