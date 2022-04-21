package io.github.underware.module.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.setting.SettingBase;
import io.github.underware.module.setting.SettingValueParser;

public class ModuleDeserializer {

    private final ModuleBase module;

    public ModuleDeserializer(ModuleBase module) {
        this.module = module;
    }

    public void readJsonObject(JsonObject object) {
        if (object.has("key_bind")) {
            module.setKeyBind(object.get("key_bind").getAsInt());
        }
        if (object.has("enabled")) {
            module.setEnabled(object.get("enabled").getAsBoolean());
        }
        if (object.has("settings")) {
            readSettingArray(object.get("settings").getAsJsonArray());
        }
    }

    private void readSettingArray(JsonArray jsonArray) {
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (!jsonObject.has("name")) {
                return;
            }

            String name = jsonObject.get("name").getAsString();
            module.getSettings().stream()
                    .filter(setting -> setting.getName().equals(name))
                    .findAny()
                    .ifPresent(setting -> readSettingType(jsonObject, setting));
        }
    }

    private void readSettingType(JsonObject jsonObject, SettingBase<?> setting) {
        if (!jsonObject.has("value")) {
            return;
        }

        JsonElement jsonElement = jsonObject.get("value");
        new SettingValueParser(setting).parseStringValue(jsonElement.getAsString());
    }

}
