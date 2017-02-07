package domain;

/**
 * Created by Денис on 25.01.2017.
 */
public class User {
    private int id;
    private String name;
    private String second_name;
    private String middle_name;
    private String login;
    private String password;
    private int role;

    public User(){

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    User(int id, String name, String second_name, String login, String password, int role){
        this.id = id;
        this.name = name;
        this.second_name = second_name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id + ", name " + name + ", login " + login + ", password " + password + "role" + role +"middle_name"+ middle_name +"}";
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }
}
