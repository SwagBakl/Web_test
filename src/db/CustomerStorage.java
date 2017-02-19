package db;

import domain.Customer;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Денис on 27.01.2017.
 */
public class CustomerStorage extends BasicStorage {

    public List<Customer> readAll() throws SQLException {
        String sql = "SELECT `id`, `name`, `adress`, `number_of_projects`, `finished_projects` FROM `customers`";
        Connection c = getConnection();
        Statement s = null;
        ResultSet r = null;
        try {
            s = c.createStatement();
            r = s.executeQuery(sql);
            List<Customer> customers = new ArrayList<Customer>();
            while (r.next()) {
                Customer customer = new Customer();
                customer.setId(r.getInt("id"));
                customer.setName(r.getString("name"));
                customer.setAdress(r.getString("adress"));
                customer.setNumber_of_projects(r.getInt("number_of_projects"));
                customer.setFinished_projects(r.getInt("finished_projects"));
                customers.add(customer);
            }
            c.commit();
            return customers;
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
            }
            throw e;
        } finally {
            try {
                r.close();
            } catch (NullPointerException | SQLException e) {
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException e) {
            }
        }
    }

    public Customer readById(Integer id) throws SQLException {
        String sql = "SELECT `name`, `adress`, `number_of_projects`, `finished_projects` FROM customers WHERE id = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            rs = s.executeQuery();
            Customer customer = null;
            while (rs.next()) {
                customer = new Customer();
                customer.setName(rs.getString("name"));
                customer.setAdress(rs.getString("adress"));
                customer.setNumber_of_projects(rs.getInt("number_of_projects"));
                customer.setFinished_projects(rs.getInt("finished_projects"));
            }
            c.commit();
            return customer;
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
            }
            throw e;
        } finally {
            try {
                rs.close();
            } catch (NullPointerException | SQLException e) {
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException e) {
            }
        }
    }

    public Customer findById(Integer id) throws SQLException {
        Customer customer = readById(id);
        return customer;
    }

    public Integer create(Customer customer) throws SQLException {
        String sql = "INSERT INTO `customers`(`name`, `adress`, `number_of_projects`, `finished_projects`) VALUES (?, ?, ?, ?)";
        Connection c = getConnection();
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            s.setString(1, customer.getName());
            s.setString(2, customer.getAdress());
            s.setInt(3, customer.getNumber_of_projects());
            s.setInt(4, customer.getFinished_projects());
            s.executeUpdate();
            r = s.getGeneratedKeys();
            Integer id = null;
            if(r.next()) {
                id = r.getInt(1);
            }
            c.commit();
            return id;
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE `customers` SET `name` = ?, `adress` = ?, `number_of_projects`=?, `finished_projects` = ? WHERE `id` = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        try {
            s = c.prepareStatement(sql);
            s.setString(1, customer.getName());
            s.setString(2, customer.getAdress());
            s.setInt(3, customer.getNumber_of_projects());
            s.setInt(4, customer.getFinished_projects());
            s.executeUpdate();
            c.commit();
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public void save(Customer customer) throws SQLException{
        if(customer.getId() != 0) {
            update(customer);
        } else {
            Integer id = create(customer);
            customer.setId(id);
        }
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM `customers` WHERE `id` = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        try {
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
            c.commit();
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }




}
