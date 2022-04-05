package io.github.underware.module.setting.impl;

import io.github.underware.module.setting.SettingBase;

public final class BooleanSetting extends SettingBase<Boolean> {

    public BooleanSetting(String name, String description, Boolean value) {
        super(name, description, value);
    }

    @Override
    public String toString() {
        return "BooleanSetting{" +
                super.toString() +
                '}';
    }

}
