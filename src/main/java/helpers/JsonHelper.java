package helpers;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by snaphuman on 6/7/16.
 */
public class JsonHelper {

    public static String toJson(Object object) {

        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {

        return JsonHelper::toJson;
    }
}
