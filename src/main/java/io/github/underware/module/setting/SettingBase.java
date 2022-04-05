package io.github.underware.module.setting;

import com.google.gson.annotations.Expose;

public abstract class SettingBase<T> {

    @Expose
    private final String name;
    @Expose
    private T value;

    private final String description;

    public SettingBase(String name, String description, T value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value + '\'';
    }

}
