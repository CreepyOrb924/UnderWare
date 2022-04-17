package io.github.underware.module;

import io.github.underware.UnderWare;
import io.github.underware.module.setting.SettingBase;
import io.github.underware.util.reflection.ReflectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ModuleManager {

    INSTANCE;

    public final List<ModuleBase> modules = new ArrayList<>();

    public void init() {
        loadModules();
    }

    private void loadModules() {
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

    public void addModules(ModuleBase... modules) {
        this.modules.addAll(Arrays.asList(modules));
    }

    public List<ModuleBase> getModulesAlphabetically() {
        return modules.stream()
                .sorted((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()))
                .collect(Collectors.toList());
    }

    public ModuleBase getModule(Class<? extends ModuleBase> aClass) {
        return modules.stream()
                .filter(module -> module.getClass() == aClass)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find Module: " + aClass + "."));
    }

    public ModuleBase getModule(String moduleName) {
        return modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(moduleName))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find Module: " + moduleName + "."));
    }

    public SettingBase<?> getSettingByName(ModuleBase module, String settingName) {
        return module.getSettings().stream()
                .filter(setting -> setting.getName().equalsIgnoreCase(settingName))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find setting: " + settingName + '.'));
    }

    public void onKeyPress(int key) {
        modules.stream()
                .filter(module -> module.getKeyBind() == key)
                .forEach(ModuleBase::toggle);
    }

}
