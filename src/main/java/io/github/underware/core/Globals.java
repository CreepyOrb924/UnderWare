package io.github.underware.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Globals {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static char prefix = '.';

    public static boolean debug = false;

}
