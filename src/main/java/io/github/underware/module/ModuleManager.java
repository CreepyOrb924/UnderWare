package io.github.underware.module;

import io.github.underware.UnderWare;
import io.github.underware.util.reflection.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ModuleManager {

    INSTANCE;

    public final List<ModuleBase> modules = new ArrayList<>();

    public void init() {
        loadModules();
    }

    private void loadModules() {
        try {
            ArrayList<Class<?>> modClasses = ReflectionUtil.getClassesForPackage("io.github.underware.module.impl");
            modClasses.spliterator().forEachRemaining(aClass -> {
                if (!ModuleBase.class.isAssignableFrom(aClass)) {
                    return;
                }
                try {
                    ModuleBase module = (ModuleBase) aClass.getConstructor().newInstance();
                    new ModuleSettingAccessor(module).loadModuleSettings();
                    modules.add(module);
                    UnderWare.LOGGER.info("Loaded Module: {}.", module);
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                    UnderWare.LOGGER.error("Unable to create module class. [{}]", e.toString());
                    e.printStackTrace();
                }
            });
        } catch (ClassNotFoundException e) {
            UnderWare.LOGGER.error(e);
            e.printStackTrace();
        }
    }

    public void addModules(ModuleBase... modules) {
        this.modules.addAll(Arrays.asList(modules));
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

    public void onKeyPress(int key) {
        modules.stream()
                .filter(module -> module.getKeyBind() == key)
                .forEach(ModuleBase::toggle);
    }

}
