package main.Utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.Lists.ShapeList;

public class JsonUtils {
    public static String getJson(ShapeList list) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(list.getList());
    }
}
