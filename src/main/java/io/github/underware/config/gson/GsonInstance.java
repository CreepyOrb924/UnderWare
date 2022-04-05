package io.github.underware.config.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonInstance {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

}
