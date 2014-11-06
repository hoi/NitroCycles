import com.avaje.ebean.Ebean;
import models.Vendor;
import play.*;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        InitialData.insert(app);
    }

    static class InitialData {
        public static void insert(Application app) {
            if(Ebean.find(Vendor.class).findRowCount() == 0) {
                Map<String, List<Object>> all = (Map<String, List<Object>>)Yaml.load("initial-data.yml");
                Ebean.save(all.get("vendors"));
                Ebean.save(all.get("parts"));
<<<<<<< HEAD
=======
                Ebean.save(all.get("projects"));
                Ebean.save(all.get("project_parts"));
>>>>>>> Completed Angular App
            }
        }
    }

}
