package io.github.underware.config.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.setting.SettingBase;
import io.github.underware.module.setting.SettingValueParser;

public final class ModuleDeserializer {

    private final ModuleBase module;

    public ModuleDeserializer(ModuleBase module) {
        this.module = module;
    }

    public void readJsonObject(JsonObject object) {
        if (object.has("key_bind")) {
            module.setKeyBind(object.get("key_bind").getAsInt());
        } else if (object.has("enabled")) {
            module.setEnabled(object.get("enabled").getAsBoolean());
        } else if (object.has("settings")) {
            readSettingArray(object.get("settings").getAsJsonObject());
        }
    }

    private void readSettingArray(JsonObject jsonObject) {
        if (!jsonObject.has("name")) {
            return;
        }

        String name = jsonObject.get("name").getAsString();
        module.getSettings().stream()
                .filter(setting -> setting.getName().equals(name))
                .findAny()
                .ifPresent(setting -> readSettingType(jsonObject, setting));
    }

    // TODO: 4/4/22 Test these for different types.
    private void readSettingType(JsonObject jsonObject, SettingBase<?> setting) {
        if (!jsonObject.has("value")) {
            return;
        }

        JsonElement jsonElement = jsonObject.get("value");
        new SettingValueParser(setting).parseJsonElementValue(jsonElement);
    }

}
