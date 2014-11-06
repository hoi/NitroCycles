package models;

import play.db.ebean.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Iterator;
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

    public void updatePrice() {
        Float new_price = 0.0f;
        List<ProjectPart> project_parts = new Model.Finder(Integer.class, ProjectPart.class).where().eq("project", this).findList();
        for (Iterator<ProjectPart> iter = project_parts.iterator(); iter.hasNext(); ) {
            ProjectPart project_part = iter.next();
            new_price += project_part.part.price;
        }
        this.price = new_price;
    }

    public void updateCompletion() {
        Integer numerator = 0;
        Integer denominator = 0;
        List<ProjectPart> project_parts = new Model.Finder(Integer.class, ProjectPart.class).where().eq("project", this).findList();
        for (Iterator<ProjectPart> iter = project_parts.iterator(); iter.hasNext(); ) {
            ProjectPart project_part = iter.next();
            if (project_part.state.equals("installed")) {
                numerator += 1;
            }
            denominator += 1;
        }
        if (denominator > 0) {
            this.completed = (float) numerator / denominator;
        } else {
            this.completed = 0.0f;
        }
    }
}
