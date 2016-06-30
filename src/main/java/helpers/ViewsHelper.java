package helpers;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;

/**
 * Created by snaphuman on 6/6/16.
 */
public class ViewsHelper {

    public static String render(String templatePath, HashMap<String, Object> model) {

        return new FreeMarkerEngine().render(new ModelAndView(model, templatePath));
    }
}
