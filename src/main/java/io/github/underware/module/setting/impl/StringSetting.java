package io.github.underware.module.setting.impl;

import io.github.underware.module.setting.SettingBase;

public class StringSetting extends SettingBase<String> {

    public StringSetting(String name, String description, String value) {
        super(name, description, value);
    }

    @Override
    public String toString() {
        return "StringSetting{" +
                super.toString() +
                "}";
    }

}
