package io.github.underware.module.setting.impl;

import io.github.underware.module.setting.SettingBase;

public final class NumberSetting extends SettingBase<Number> {

    private final Number min, max, inc;

    public NumberSetting(String name, String description, Number value, Number min, Number max, Number inc) {
        super(name, description, value);
        this.min = min;
        this.max = max;
        this.inc = inc;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }

    public Number getInc() {
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
