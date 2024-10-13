package org.technicfox.emojify.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.technicfox.emojify.Emojify;
import org.technicfox.emojify.menusystem.menu.EmojiHomeMenu;




public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Тільки гравці можуть використовувати цю команду!").color(NamedTextColor.RED));
            return false;
        }

        new EmojiHomeMenu(Emojify.getPlayerMenuUtility((Player) sender)).open();
        return true;
    }


}

