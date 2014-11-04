package controllers;

import models.Part;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

public class Parts extends Controller {

    public static Result index() {
        List<Part> parts = Part.find.all();
        return ok(Json.toJson(parts));
    }

    public static Result show(Integer id) {
        Part db_part = Part.find.byId(id);
        if (db_part == null) {
            return notFound();
        } else {
            return ok(Json.toJson(db_part));
        }
    }

    public static Result create() {
        Part part = Form.form(Part.class).bindFromRequest().get();
        part.save();
        part = Part.find.byId(part.id);
        return ok(Json.toJson(part));
    }

    public static Result update(Integer id) {
        Part part = Form.form(Part.class).bindFromRequest().get();
        Part db_part = Part.find.byId(id);
        if (db_part == null) {
            return notFound();
        } else {
            db_part.updateDetails(part);
            db_part.save();
            db_part = Part.find.byId(db_part.id);
            return ok(Json.toJson(db_part));
        }
    }

    public static Result delete(Integer id) {
        Part.find.ref(id).delete();
        return ok();
    }

}
