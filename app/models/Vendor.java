package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vendor extends Model {
    @Id
    public Integer id;

    public String name;
    public String url;

    @OneToMany
    public Part parts;
}
