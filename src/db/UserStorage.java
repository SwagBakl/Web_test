package db;

/**
 * Created by Денис on 25.01.2017.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import domain.Project;
import domain.User;

public class UserStorage extends BasicStorage {


    public List<User> readAll() throws SQLException {
        String sql = "SELECT `id`, `name`, `second_name`, `middle_name`, `login`, `password`, `role` FROM `users`";
        Connection c = getConnection();
        Statement s = null;
        ResultSet r = null;
        try {
            s = c.createStatement();
            r = s.executeQuery(sql);
            List<User> users = new ArrayList<User>();
            while (r.next()) {
                User user = new User();
                user.setId(r.getInt("id"));
                user.setName(r.getString("name"));
                user.setSecond_name(r.getString("second_name"));
                user.setMiddle_name(r.getString("middle_name"));
                user.setLogin(r.getString("login"));
                user.setPassword(r.getString("password"));
                user.setRole(r.getInt("role"));
                users.add(user);
            }
            c.commit();
            return users;
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

    public User readByLoginAndPassword(String login, String password) throws SQLException {
        String sql = "SELECT `id`, `name`, `second_name`, `middle_name`, `role` FROM `users` WHERE `login` = ? AND `password` = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            s = c.prepareStatement(sql);
            s.setString(1, login);
            s.setString(2, password);
            r = s.executeQuery();
            User user = null;
            if(r.next()) {
                user = new User();
                user.setId(r.getInt("id"));
                user.setName(r.getString("name"));
                user.setSecond_name(r.getString("second_name"));
                user.setMiddle_name(r.getString("middle_name"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(r.getInt("role"));
            }
            c.commit();
            return user;
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public User readById(Integer id) throws SQLException {
        String sql = "SELECT `name`, `second_name`, `middle_name`, `login`, `password`, `role` FROM `users` WHERE `id` = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            User user = null;
            if(r.next()) {
                user = new User();
                user.setId(id);
                user.setName(r.getString("name"));
                user.setSecond_name(r.getString("second_name"));
                user.setMiddle_name(r.getString("middle_name"));
                user.setLogin(r.getString("login"));
                user.setPassword(r.getString("password"));
                user.setRole(r.getInt("role"));
            }
            c.commit();
            return user;
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public User findById(Integer id) throws SQLException {
        User user = readById(id);

        return user;
    }

    public Integer create(User user) throws SQLException {
        String sql = "INSERT INTO `users`(`name`, `second_name`, `middle_name`, `login`, `password`, `role`) VALUES (?, ?, ?, ?, ?, ?)";
        Connection c = getConnection();
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            s.setString(1, user.getName());
            s.setString(2, user.getSecond_name());
            s.setString(3, user.getMiddle_name());
            s.setString(4, user.getLogin());
            s.setString(5, user.getPassword());
            s.setInt(6, user.getRole());
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

    public void update(User user) throws SQLException {
        String sql = "UPDATE `users` SET `name` = ?, `second_name` = ?, `middle_name`=?, `login` = ?, `password` = ?, `role` = ? WHERE `id` = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        try {
            s = c.prepareStatement(sql);
            s.setString(1, user.getName());
            s.setString(2, user.getSecond_name());
            s.setString(3, user.getMiddle_name());
            s.setString(4, user.getLogin());
            s.setString(5, user.getPassword());
            s.setInt(6, user.getRole());
            s.setInt(7, user.getId());
            s.executeUpdate();
            c.commit();
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public void save(User user) throws SQLException {
        //update(user);
        if(user.getId() != 0) {
            update(user);
        } else {
            Integer id = create(user);
            user.setId(id);
        }
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
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