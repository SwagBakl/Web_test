package db;

import domain.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
