package web;

import db.CustomerStorage;
import db.StorageCreator;
import domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Денис on 05.02.2017.
 */
public class CustomerEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Integer id = null;
        try{
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){

        }
        if(id != null){
            StorageCreator storageCreator = null;
            try {
                storageCreator = new StorageCreator();
                CustomerStorage s = storageCreator.newCustomerStorage();
                Customer customer = s.findById(id);
                req.setAttribute("customer", customer);

            }catch (SQLException e){
                throw new ServletException(e);
            }
            finally {
                if(storageCreator != null){
                    storageCreator.close();
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/customers/editCustomer.jsp").forward(req, resp);
    }
}
