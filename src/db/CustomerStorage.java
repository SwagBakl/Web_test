package db;

import domain.Customer;

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

    public Integer insert(Customer customer) throws SQLException{
        String sql = "INSERT INTO `customers` (`name`, `adress`, `number_of_projects`, `finished_projects`) values(?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAdress());
            preparedStatement.setInt(3, customer.getNumber_of_projects());
            preparedStatement.setInt(4, customer.getFinished_projects());
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if(rs.next()){
                id = rs.getInt(1);
            }
            connection.commit();
            return id;
        }catch(SQLException e) {
            try { connection.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { rs.close(); } catch(NullPointerException | SQLException e) {}
            try { preparedStatement.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET `name`= ?, `adress`= ?, `number_of_projects`= ?, `finished_projects`= ? WHERE `id` = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAdress());
            preparedStatement.setInt(3, customer.getNumber_of_projects());
            preparedStatement.setInt(4, customer.getFinished_projects());
            preparedStatement.setInt(5, customer.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw e;
        } finally {
            try {
                preparedStatement.close();
            } catch (NullPointerException | SQLException e) {
            }
        }
    }

    public void  save(Customer customer) throws SQLException {
        if (customer.getId() != 0) {
            update(customer);
        }else {
            Integer id = insert(customer);
            customer.setId(id);
        }
    }

    public void delete(Integer id) throws SQLException{

        String sql = "DELETE FROM `customers` WHERE `id`=?";
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException e) {
            try { connection.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { ps.close(); } catch(NullPointerException | SQLException e) {}
        }

    }


}
