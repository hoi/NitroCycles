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

    public static Finder<Integer, Part> find = new Model.Finder(Integer.class, Part.class);

    public void updateDetails(Part part) {
        this.name = part.name;
        this.part_number = part.part_number;
        this.price = part.price;
        this.vendor = part.vendor;
    }
}
