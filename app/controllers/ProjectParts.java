package controllers;

import models.Project;
import models.ProjectPart;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

public class ProjectParts extends Controller {

    public static Result index() {
        List<ProjectPart> project_parts = new Model.Finder(Integer.class, ProjectPart.class).all();
        return ok(Json.toJson(project_parts));
    }

    public static Result indexByProjectId(Integer id) {
        Project project = Project.find.byId(id);
        List<ProjectPart> project_parts = new Model.Finder(Integer.class, ProjectPart.class).where().eq("project", project).findList();
        return ok(Json.toJson(project_parts));
    }

    public static Result show(Integer id) {
        ProjectPart db_project_part = ProjectPart.find.byId(id);
        if (db_project_part == null) {
            return notFound();
        } else {
            return ok(Json.toJson(db_project_part));
        }
    }

    public static Result create() {
        ProjectPart project_part = Form.form(ProjectPart.class).bindFromRequest().get();
        project_part.save();
        project_part = ProjectPart.find.byId(project_part.id);
        return ok(Json.toJson(project_part));
    }

    public static Result update(Integer id) {
        ProjectPart project_part = Form.form(ProjectPart.class).bindFromRequest().get();
        ProjectPart db_project_part = ProjectPart.find.byId(id);
        if (db_project_part == null) {
            return notFound();
        } else {
            db_project_part.updateDetails(project_part);
            db_project_part.save();
            db_project_part = ProjectPart.find.byId(db_project_part.id);
            return ok(Json.toJson(db_project_part));
        }
    }

    public static Result delete(Integer id) {
        ProjectPart.find.ref(id).delete();
        return ok();
    }

}
