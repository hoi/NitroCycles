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

    public static Finder<Integer, Vendor> find = new Model.Finder(Integer.class, Vendor.class);

    public void updateDetails(Vendor vendor) {
        this.name = vendor.name;
        this.url = vendor.url;
    }
}
