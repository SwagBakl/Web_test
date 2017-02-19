package db;

import domain.Project;

import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 02.02.2017.
 */
public class ProjectStorage extends BasicStorage {

        public List<Project> readAll() throws SQLException {
            String sql = "SELECT `id`, `name`, `date_begin`, `date_planned_finish`, `date_finish`, `project_seccess`, `customer_id` FROM projects";
            Connection connection = getConnection();
            Statement s = null;
            ResultSet rs = null;

            try {

                s = connection.createStatement();
                rs = s.executeQuery(sql);
                List<Project> projects = new ArrayList<Project>();
                while (rs.next()){
                    Project project = new Project();
                    project.setId(rs.getInt("id"));
                    project.setName(rs.getString("name"));
                    project.setDate_begin(rs.getDate("date_begin"));
                    project.setDate_planned_finish(rs.getDate("date_planned_finish"));
                    project.setDate_finish(rs.getDate("date_finish"));
                    project.setProject_seccess(rs.getString("project_seccess"));
                    project.setCustomer_id(rs.getInt("customer_id"));
                    projects.add(project);
                }return projects;
            } catch (SQLException e) {
                try {
                    connection.rollback();
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

    public Project readById(Integer id) throws SQLException {
        String sql = "SELECT `name`, `date_begin`, `date_planned_finish`, `date_finish`, `project_seccess`, `customer_id` FROM `projects` WHERE `id` = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            Project project = null;
            if(r.next()) {
                project = new Project();
                project.setId(id);
                project.setName(r.getString("name"));
                project.setDate_begin(new java.sql.Date(r.getDate("date_begin").getTime()));
                project.setDate_planned_finish(r.getDate("date_planned_finish"));
                project.setDate_finish(r.getDate("date_finish"));
                project.setProject_seccess(r.getString("project_seccess"));
                project.setCustomer_id(r.getInt("customer_id"));
            }
            c.commit();
            return project;
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public Project findById(Integer id) throws SQLException {

        return readById(id);
    }

    public Integer create(Project project) throws SQLException {
        String sql = "INSERT INTO `projects`(`name`, `date_begin`, `date_planned_finish`, `date_finish`, `project_seccess`, `customer_id`) VALUES (?, ?, ?, ?, ?, ?)";
        Connection c = getConnection();
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            s.setString(1, project.getName());
            s.setDate(2, project.getDate_begin());
            s.setDate(3, project.getDate_planned_finish());
            s.setDate(4, project.getDate_finish());
            s.setString(5, project.getProject_seccess());
            s.setInt(6, project.getCustomer_id());
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

    public void update(Project project) throws SQLException {
        String sql = "UPDATE `projects` SET `name` = ?, `date_begin` = ?, `date_planned_finish` = ?, `date_finish` = ?, `project_seccess` = ?, `customer_id` = ? WHERE `id` = ?";
        Connection c = getConnection();
        PreparedStatement s = null;
        try {
            s = c.prepareStatement(sql);
            s.setString(1, project.getName());
            s.setDate(2, project.getDate_begin());
            s.setDate(3, project.getDate_planned_finish());
            s.setDate(4, project.getDate_finish());
            s.setString(5, project.getProject_seccess());
            s.setInt(6, project.getCustomer_id());
            s.setInt(7, project.getId());
            s.executeUpdate();
            c.commit();
        } catch(SQLException e) {
            try { c.rollback(); } catch(SQLException e1) {}
            throw e;
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public void save(Project project) throws SQLException{
        if(project.getId() != 0) {
            update(project);
        } else {
            Integer id = create(project);
            project.setId(id);
        }
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM `projects` WHERE `id` = ?";
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

