package io.github.underware.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * This singleton class cannot follow the project standard of using enums for singleton classes
 * because it needs to be able to be serialized.
 */
public class Globals {

    public static Globals INSTANCE = new Globals();

    public final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Expose
    public char prefix = '.';

    @Expose
    public boolean debug = false;

}
