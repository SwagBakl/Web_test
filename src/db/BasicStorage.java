package db;

/**
 * Created by Денис on 25.01.2017.
 */import java.sql.Connection;

abstract public class BasicStorage {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
