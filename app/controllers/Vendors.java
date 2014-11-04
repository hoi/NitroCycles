package controllers;

import models.Vendor;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

public class Vendors extends Controller {

    public static Result index() {
        List<Vendor> vendors = new Model.Finder(Integer.class, Vendor.class).all();
        return ok(Json.toJson(vendors));
    }

    public static Result show(Integer id) {
        Vendor db_vendor = Vendor.find.byId(id);
        if (db_vendor == null) {
            return notFound();
        } else {
            return ok(Json.toJson(db_vendor));
        }
    }

    public static Result create() {
        Vendor vendor = Form.form(Vendor.class).bindFromRequest().get();
        vendor.save();
        vendor = Vendor.find.byId(vendor.id);
        return ok(Json.toJson(vendor));
    }

    public static Result update(Integer id) {
        Vendor vendor = Form.form(Vendor.class).bindFromRequest().get();
        Vendor db_vendor = vendor.find.byId(id);
        if (db_vendor == null) {
            return notFound();
        } else {
            db_vendor.updateDetails(vendor);
            db_vendor.save();
            db_vendor = Vendor.find.byId(db_vendor.id);
            return ok(Json.toJson(db_vendor));
        }
    }

    public static Result delete(Integer id) {
        Vendor.find.ref(id).delete();
        return ok();
    }

}
