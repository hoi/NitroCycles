package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Part extends Model {
    @Id
    public Integer id;

    public String name;
    public String part_number;
    public Float price;

    @ManyToOne
    public Vendor vendor;
}
