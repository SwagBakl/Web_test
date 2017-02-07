package test;

/**
 * Created by Денис on 26.01.2017.
 */
import java.awt.BorderLayout;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Connector;
import db.UserStorage;
import domain.User;

public class Runner {
    public static void main(String[] args) {
        Connection c = null;
        try {
            Connector.init(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/bank_roll?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    "root"
            );
            c = Connector.getConnection();
            UserStorage s = new UserStorage();
            s.setConnection(c);
            List<User> users = s.readAll();
            UserTableModel m = new UserTableModel(users);
            JFrame window = new JFrame("Список пользователей");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBounds(100, 100, 640, 480);
            JTable table = new JTable(m);
            JScrollPane scrollPane = new JScrollPane(table);
            window.add(scrollPane, BorderLayout.CENTER);
            window.setVisible(true);
            window.validate();
        } catch(ClassNotFoundException | SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            JOptionPane.showMessageDialog(null, sw);
        } finally {
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
}
