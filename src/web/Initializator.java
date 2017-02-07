package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import db.Connector;

public class Initializator implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            Connector.init(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/bank_roll?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    "root"
            );
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {}
}