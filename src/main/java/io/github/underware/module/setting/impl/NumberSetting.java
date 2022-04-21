package io.github.underware.module.setting.impl;

import io.github.underware.module.setting.SettingBase;

public class NumberSetting<T extends Number> extends SettingBase<T> {

    private final T min, max, inc;

    public NumberSetting(String name, String description, T value, T min, T max, T inc) {
        super(name, description, value);
        this.min = min;
        this.max = max;
        this.inc = inc;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    public T getInc() {
        return inc;
    }

    @Override
    public String toString() {
        return "NumberSetting{" +
                super.toString() +
                ", min=" + min +
                ", max=" + max +
                ", inc=" + inc +
                '}';
    }

}
