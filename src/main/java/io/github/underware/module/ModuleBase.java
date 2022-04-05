package io.github.underware.module;

import com.google.gson.annotations.Expose;
import io.github.underware.module.setting.SettingBase;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ModuleBase {

    @Expose
    private final String name;
    @Expose
    private int keyBind = Keyboard.KEY_NONE;
    @Expose
    private boolean enabled = false;
    @Expose
    private final List<SettingBase<?>> settings = new ArrayList<>();

    private final String description;
    private final Category category;

    protected final Minecraft mc = Minecraft.getMinecraft();

    public ModuleBase(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public ModuleBase(String name, String description, Category category, int keyBind) {
        this(name, description, category);
        this.keyBind = keyBind;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public List<SettingBase<?>> getSettings() {
        return settings;
    }

    public void addSettings(SettingBase<?>... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {
        enabled = true;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        enabled = false;
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public void toggle() {
        if (enabled) {
            onDisable();
        } else {
            onEnable();
        }
    }

    protected boolean worldCheck() {
        return mc.player != null && mc.world != null;
    }

    @Override
    public String toString() {
        return "ModuleBase{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", keyBind=" + keyBind +
                ", enabled=" + enabled +
                ", settings=" + settings +
                '}';
    }

}
