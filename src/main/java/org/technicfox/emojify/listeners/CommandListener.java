package org.technicfox.emojify.listeners;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.technicfox.emojify.Emojify;
import org.technicfox.emojify.menusystem.menu.EmojiHomeMenu;




public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Тільки гравці можуть використовувати цю команду!");
            return false;
        }

        new EmojiHomeMenu(Emojify.getPlayerMenuUtility((Player) sender)).open();
        return true;
    }


}

