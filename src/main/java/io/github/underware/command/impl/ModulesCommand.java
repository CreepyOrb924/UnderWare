package io.github.underware.command.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.underware.command.CommandBase;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.ModuleManager;
import io.github.underware.module.setting.SettingBase;
import io.github.underware.util.chat.ChatUtil;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ModulesCommand extends CommandBase {

    public ModulesCommand() {
        super("Modules", "List the client's Modules.", new String[]{"Mods"}, "");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        StringBuilder stringBuilder = new StringBuilder("\n" +
                ChatFormatting.RED + "Module" +
                ChatFormatting.RESET + " \u23d0 " +
                ChatFormatting.GREEN + "Description" +
                ChatFormatting.RESET + " \u23d0 " +
                ChatFormatting.BLUE + "Keybind" +
                ChatFormatting.RESET + " \u2d30 " +
                ChatFormatting.YELLOW + "Settings\n");

        List<ModuleBase> modulesAlphabetically = ModuleManager.INSTANCE.getObjectsAlphabetically();
        for (int i = 0; i < modulesAlphabetically.size(); i++) {
            stringBuilder.append(getDetailsFormatted(modulesAlphabetically.get(i)));
            if (i != modulesAlphabetically.size() - 1) {
                stringBuilder.append('\n');
            }
        }
        ChatUtil.sendClientMessage(stringBuilder.toString());
    }

    private String getDetailsFormatted(ModuleBase module) {
        return ChatFormatting.RED + module.getName() +
                ChatFormatting.RESET + " \u23d0 " +
                ChatFormatting.GREEN + module.getDescription() +
                ChatFormatting.RESET + " \u23d0 " +
                ChatFormatting.BLUE + Keyboard.getKeyName(module.getKeyBind()) +
                ChatFormatting.RESET + " \u23d0 " +
                ChatFormatting.YELLOW + getSettingsFormatted(module);
    }

    private String getSettingsFormatted(ModuleBase module) {
        if (module.getSettings().isEmpty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int i = 0; i < module.getSettings().size(); i++) {
            SettingBase<?> setting = module.getSettings().get(i);
            stringBuilder.append(ChatFormatting.YELLOW)
                    .append(setting.getName())
                    .append(" - ")
                    .append(setting.getValue());
            if (i != module.getSettings().size() - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

}
