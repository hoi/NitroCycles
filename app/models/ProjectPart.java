package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectPart extends Model {
    @Id
    public Integer id;

    @ManyToOne
    public Project project;
    @ManyToOne
    public Part part;

    public String state;
}
