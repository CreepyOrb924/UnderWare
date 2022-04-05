package io.github.underware.module.setting.impl;

import io.github.underware.module.setting.SettingBase;

import java.util.Arrays;

public final class EnumSetting extends SettingBase<Enum<?>> {

    public EnumSetting(String name, String description, Enum<?> value) {
        super(name, description, value);
    }

    public Enum<?> getNextEnumValue() {
        int index = getValue().ordinal();
        int nextIndex = index + 1;
        Enum<?>[] enums = getValue().getDeclaringClass().getEnumConstants();
        nextIndex %= enums.length;
        return enums[nextIndex];
    }

    public void setValue(String value) {
        Enum<?>[] enumConstants = getValue().getDeclaringClass().getEnumConstants();
        this.setValue(Arrays.stream(enumConstants)
                .filter(anEnum -> anEnum.name().equalsIgnoreCase(value))
                .findAny()
                .orElse(enumConstants[0]));
    }

    @Override
    public String toString() {
        return "EnumSetting{" +
                super.toString() +
                "}";
    }

}
