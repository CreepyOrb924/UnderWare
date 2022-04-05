package io.github.underware.module;

import io.github.underware.UnderWare;
import io.github.underware.module.setting.SettingBase;

import java.lang.reflect.Field;

public final class ModuleSettingAccessor {

    private final ModuleBase module;

    public ModuleSettingAccessor(ModuleBase module) {
        this.module = module;
    }

    public void loadModuleSettings() {
        for (Field field : module.getClass().getDeclaredFields()) {
            if (!SettingBase.class.isAssignableFrom(field.getType())) {
                return;
            }

            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                module.addSettings((SettingBase<?>) field.get(module));
            } catch (IllegalAccessException e) {
                UnderWare.LOGGER.error("Error getting setting fields for module: {}.", module);
                e.printStackTrace();
            }
        }
    }

}
