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
    @OneToMany
    List<ProjectPart> project_parts = new ArrayList<ProjectPart>();
}
