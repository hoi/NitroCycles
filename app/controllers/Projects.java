package controllers;

import models.Project;
import models.ProjectPart;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;

import java.util.Iterator;
import java.util.List;

public class Projects extends Controller {

    public static Result index() {
        List<Project> projects = new Model.Finder(Integer.class, Project.class).all();
        return ok(Json.toJson(projects));
    }

    public static Result show(Integer id) {
        Project db_project = Project.find.byId(id);
        if (db_project == null) {
            return notFound();
        } else {
            return ok(Json.toJson(db_project));
        }
    }

    public static Result create() {
        Project project = Form.form(Project.class).bindFromRequest().get();
        project.save();
        project = Project.find.byId(project.id);
        return ok(Json.toJson(project));
    }

    public static Result update(Integer id) {
        Project project = Form.form(Project.class).bindFromRequest().get();
        Project db_project = Project.find.byId(id);
        if (db_project == null) {
            return notFound();
        } else {
            db_project.updateDetails(project);
            db_project.save();
            db_project = Project.find.byId(db_project.id);
            return ok(Json.toJson(db_project));
        }
    }

    public static Result delete(Integer id) {
        Project project = Project.find.byId(id);
        // delete associated project_parts
        List<ProjectPart> project_parts = new Model.Finder(Integer.class, ProjectPart.class).where().eq("project", project).findList();
        for (Iterator<ProjectPart> iter = project_parts.iterator(); iter.hasNext(); ) {
            ProjectPart project_part = iter.next();
            project_part.delete();
        }
        project.delete();
        return ok();
    }

}
