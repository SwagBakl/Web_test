package domain;

/**
 * Created by Денис on 27.01.2017.
 */
public class Customer {

    private int id;
    private String name;
    private String adress;
    private int number_of_projects;
    private int finished_projects;




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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getNumber_of_projects() {
        return number_of_projects;
    }

    public void setNumber_of_projects(int number_of_projects) {
        this.number_of_projects = number_of_projects;
    }

    public int getFinished_projects() {
        return finished_projects;
    }

    public void setFinished_projects(int finished_projects) {
        this.finished_projects = finished_projects;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id + ", name " + name + ", adress " + adress + ", number_of_projects " + number_of_projects + "finished_projects" + finished_projects +"}";
    }
}
