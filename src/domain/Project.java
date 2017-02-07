package domain;

import java.sql.Date;

/**
 * Created by Денис on 02.02.2017.
 */
public class Project {

    private int id;
    private String name;
    private Date date_begin;
    private Date date_planned_finish;
    private Date date_finish;
    private String project_seccess;
    private int customer_id;


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

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_planned_finish() {
        return date_planned_finish;
    }

    public void setDate_planned_finish(Date date_planned_finish) {
        this.date_planned_finish = date_planned_finish;
    }

    public Date getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(Date date_finish) {
        this.date_finish = date_finish;
    }

    public String getProject_seccess() {
        return project_seccess;
    }

    public void setProject_seccess(String project_seccess) {
        this.project_seccess = project_seccess;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
