package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectPart extends Model {
    @Id
    public Integer id;

    public String state;

    @ManyToOne
    public Project project;

    @ManyToOne
    public Part part;

    public static Finder<Integer, ProjectPart> find = new Model.Finder(Integer.class, ProjectPart.class);

    public void updateDetails(ProjectPart project_part) {
        this.state = project_part.state;
        this.project = project_part.project;
        this.part = project_part.part;
    }
}
