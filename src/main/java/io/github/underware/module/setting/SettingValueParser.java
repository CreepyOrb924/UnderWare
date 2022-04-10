package io.github.underware.module.setting;

import com.google.gson.JsonElement;
import io.github.underware.module.setting.impl.BooleanSetting;
import io.github.underware.module.setting.impl.EnumSetting;
import io.github.underware.module.setting.impl.NumberSetting;
import io.github.underware.module.setting.impl.StringSetting;

public class SettingValueParser {
    private final SettingBase<?> setting;

    public SettingValueParser(SettingBase<?> setting) {
        this.setting = setting;
    }

    public void parseStringValue(String stringValue) {
        if (setting instanceof NumberSetting) {
            NumberSetting numberSetting = (NumberSetting) setting;
            Number value = numberSetting.getValue();
            if (value instanceof Double) {
                numberSetting.setValue(Double.parseDouble(stringValue));
            } else if (value instanceof Float) {
                numberSetting.setValue(Float.parseFloat(stringValue));
            } else if (value instanceof Long) {
                numberSetting.setValue(Long.parseLong(stringValue));
            } else {
                numberSetting.setValue(Integer.parseInt(stringValue));
            }
        } else if (setting instanceof BooleanSetting) {
            ((BooleanSetting) setting).setValue(Boolean.valueOf(stringValue));
        } else if (setting instanceof EnumSetting) {
            ((EnumSetting) setting).setValue(stringValue);
        } else if (setting instanceof StringSetting) {
            ((StringSetting) setting).setValue(stringValue);
        }
    }

    public void parseJsonElementValue(JsonElement jsonElement) {
        if (setting instanceof NumberSetting) {
            NumberSetting numberSetting = (NumberSetting) setting;
            Number value = numberSetting.getValue();
            if (value instanceof Double) {
                numberSetting.setValue(jsonElement.getAsDouble());
            } else if (value instanceof Float) {
                numberSetting.setValue(jsonElement.getAsFloat());
            } else if (value instanceof Long) {
                numberSetting.setValue(jsonElement.getAsLong());
            } else {
                numberSetting.setValue(jsonElement.getAsInt());
            }
        } else if (setting instanceof BooleanSetting) {
            ((BooleanSetting) setting).setValue(jsonElement.getAsBoolean());
        } else if (setting instanceof EnumSetting) {
            ((EnumSetting) setting).setValue(jsonElement.getAsString());
        } else if (setting instanceof StringSetting) {
            ((StringSetting) setting).setValue(jsonElement.getAsString());
        }
    }

}