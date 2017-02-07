package web;

import db.CustomerStorage;
import db.StorageCreator;
import db.UserStorage;
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
public class CustomerSaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        Customer customer = buildCustomer(req);
        if(customer != null){
            StorageCreator storageCreator = null;
            try {
                storageCreator = new StorageCreator();
                CustomerStorage s = storageCreator.newCustomerStorage();
                s.save(customer);
            }catch (SQLException e){
                throw new ServletException();
            }
            finally {
                if(storageCreator != null){
                    storageCreator.close();
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/customers/customerTable.html");
    }

    private Customer buildCustomer(HttpServletRequest req) {
        try {
            Customer customer = new Customer();
            try {
                customer.setId(Integer.parseInt(req.getParameter("id")));
            } catch (NumberFormatException e) {
            }
            customer.setName(req.getParameter("name"));
            if (customer.getName() == null) {
                throw new NullPointerException();
            }
            customer.setAdress(req.getParameter("adress"));
            customer.setNumber_of_projects(Integer.parseInt(req.getParameter("number_of_projects")));
            customer.setFinished_projects(Integer.parseInt(req.getParameter("finished_project")));
            return customer;
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e){
            return null;
        }
    }
}
