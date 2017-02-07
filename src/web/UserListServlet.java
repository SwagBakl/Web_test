package web;

/**
 * Created by Денис on 25.01.2017.
 */
import javax.servlet.http.HttpServlet;



        import java.io.IOException;
        import java.io.PrintWriter;
        import java.sql.Connection;
        import java.sql.SQLException;
        import java.util.List;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        import db.Connector;
        import db.UserStorage;
        import domain.User;

public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
            UserStorage s = new UserStorage();
            s.setConnection(c);
            List<User> users = s.readAll();
            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                    .forward(req, resp);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        } finally {
            try { c.close(); } catch (NullPointerException | SQLException e) {}
        }
    }
}
