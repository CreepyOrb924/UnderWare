package io.github.underware.module;

import io.github.underware.UnderWare;
import io.github.underware.core.interfaces.ManagerHandler;
import io.github.underware.module.setting.SettingBase;
import io.github.underware.util.reflection.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum ModuleManager implements ManagerHandler<ModuleBase> {

    INSTANCE;

    public final List<ModuleBase> modules = new ArrayList<>();

    public void init() {
        loadObjects();
    }

    @Override
    public void loadObjects() {
        try {
            ReflectionUtil.getAddClassesFromPackageToList("io.github.underware.module.impl", modules, ModuleBase.class);
            modules.forEach(module -> {
                new ModuleSettingAccessor(module).loadModuleSettings();
                UnderWare.LOGGER.info("Loaded Module: {}.", module);
            });
        } catch (ClassNotFoundException e) {
            UnderWare.LOGGER.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public List<ModuleBase> getObjects() {
        return modules;
    }

    @Override
    public List<ModuleBase> getObjectsAlphabetically() {
        return modules.stream()
                .sorted((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public ModuleBase get(String name) {
        return modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unable to find " + name + "."));
    }

    public SettingBase<?> getSetting(ModuleBase module, String settingName) {
        return module.getSettings().stream()
                .filter(setting -> setting.getName().equalsIgnoreCase(settingName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unable to find Setting: " + settingName + '.'));
    }

    public void onKeyPress(int key) {
        modules.stream()
                .filter(module -> module.getKeyBind() == key)
                .forEach(ModuleBase::toggle);
    }

}
