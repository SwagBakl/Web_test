package web;

import db.CustomerStorage;
import db.StorageCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Денис on 07.02.2017.
 */
public class CustomerDeleteServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.setCharacterEncoding("UTF-8");
        Integer id = null;
        try{
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){}
        if(id !=null){
            StorageCreator storageCreator = null;
            try{
                storageCreator = new StorageCreator();
                CustomerStorage s = storageCreator.newCustomerStorage();
                s.delete(id);

            }catch (SQLException e){
                throw new ServletException(e);
            }
            finally {
                if(storageCreator != null){
                    storageCreator.close();
                }
            }
        }
        resp.sendRedirect(req.getContextPath()+ "/customers/customerTable.html");
    }
}
