package db;

import java.sql.Connection;
import java.sql.SQLException;

public class StorageCreator {
    private Connection connection;
    /*private TypeStorage typeStorage;
    private ContactStorage contactStorage;*/
    private UserStorage userStorage;
    private CustomerStorage customerStorage;
    private ProjectStorage projectStorage;

    public StorageCreator() throws SQLException {
        connection = Connector.getConnection();
    }

    public UserStorage newUserStorage() {
        if(userStorage == null) {
            userStorage = new UserStorage();
            userStorage.setConnection(connection);
        }
        return userStorage;
    }

   public CustomerStorage newCustomerStorage() {
       if(customerStorage == null){
           customerStorage = new CustomerStorage();
           customerStorage.setConnection(connection);
       }
       return customerStorage;
   }

    public ProjectStorage newProjectStorage() {
        if(projectStorage == null){
            projectStorage = new ProjectStorage();
            projectStorage.setConnection(connection);
        }
        return projectStorage;
    }



    public void close() {
        try { connection.close(); } catch(SQLException e) {}
    }
}