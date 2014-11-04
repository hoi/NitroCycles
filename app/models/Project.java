package models;

import play.db.ebean.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project extends Model {
    @Id
    public Integer id;

    public String name;
    public Float completed;
    public Float price;

    public static Finder<Integer, Project> find = new Model.Finder(Integer.class, Project.class);

    public void updateDetails(Project project) {
        this.name = project.name;
        this.completed = project.completed;
        this.price = project.price;
    }
}
