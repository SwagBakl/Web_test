package test;

/**
 * Created by Денис on 26.01.2017.
 */
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


import domain.User;

public class UserTableModel implements TableModel {
    private List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int index) {
        switch(index) {
            case 0: return "ID";
            case 1: return "FIO";
            case 2: return "Login";
            case 3: return "Password";
            case 4: return "Role";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int index) {
        switch(index) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        User user = users.get(rowIndex);
        switch(colIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getName();
            case 2:
                return user.getLogin();
            case 3:
                return user.getPassword();
            case 4:
                return user.getRole();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {}

    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {}

    @Override
    public void removeTableModelListener(TableModelListener listener) {}
}
