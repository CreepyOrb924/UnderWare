package io.github.underware.config.jsonstream;

import io.github.underware.UnderWare;
import io.github.underware.util.reflection.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;

public class JsonStreamParserLoader {

    private final List<JsonStreamParser> jsonStreamParsers = new ArrayList<>();

    public List<JsonStreamParser> getJsonStreamParsers() {
        if (jsonStreamParsers.isEmpty()) {
            try {
                ReflectionUtil.getAddClassesFromPackageToList("io.github.underware", jsonStreamParsers, JsonStreamParser.class);

                jsonStreamParsers.forEach(jsonStreamParser -> UnderWare.LOGGER.info("Loaded JsonStreamParser {}.", jsonStreamParser));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return jsonStreamParsers;
    }

}
