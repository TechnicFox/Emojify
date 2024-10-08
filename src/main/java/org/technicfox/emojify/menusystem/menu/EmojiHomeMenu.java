package org.technicfox.emojify.menusystem.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.technicfox.emojify.Emojify;
import org.technicfox.emojify.menusystem.Menu;
import org.technicfox.emojify.menusystem.PlayerMenuUtility;

import java.util.Objects;

public class EmojiHomeMenu extends Menu {

    public EmojiHomeMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        try {
            String name = ChatColor.translateAlternateColorCodes('&', Emojify.getConfigUtil().getConfig().getString("MainMenuConfig.invName"));
            return name;
        }catch (Exception e){
            Bukkit.getLogger().severe("Error loading name of main menu: " + e.getMessage());
            e.printStackTrace();
        }
        return "Oops! Something went wrong! Please contact the developer.";
    }

    @Override
    public int getSlots() {
        try{
            return Emojify.getConfigUtil().getConfig().getInt("MainMenuConfig.slots");
        }catch (Exception e){
            Bukkit.getLogger().severe("Error loading number of slots in the main menu. Setting to 54: " + e.getMessage());
            e.printStackTrace();
        }
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        if (event.getSlot() == getSlots()-1){
            event.getWhoClicked().closeInventory();
            return;
        }
        if (!Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.ENCHANTED_BOOK)) return;
        try {
            String data = Emojify.getConfigUtil().getConfig().getString("inventories.slot"+event.getSlot()+".name");
            if (data == null) Bukkit.getLogger().warning("Error loading name of the item in slot " + event.getSlot());
            Emojify.getPlayerMenuUtility((Player) event.getWhoClicked()).setEmojiSlot("slot"+event.getSlot());
            new EmojiSelectorMenu(Emojify.getPlayerMenuUtility((Player) event.getWhoClicked())).open();

        }catch (Exception e){
            Bukkit.getLogger().severe("Error loading name of main menu: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void setMenuItems() {
        try {
            for (int i = 0; i < getSlots()-1; i++) {
                getEmoji(i, Emojify.getConfigUtil().getConfig().getInt("inventories.slot" + i + ".id"),
                        Emojify.getConfigUtil().getConfig().getString("inventories.slot" + i + ".name"), false);
            }
            ItemStack exit = new ItemStack(Material.MAP);
            ItemMeta meta = exit.getItemMeta();
            meta.setCustomModelData(1010);
            meta.setItemName("§c§lExit");
            exit.setItemMeta(meta);
            this.inventory.setItem(getSlots()-1, exit);
        }catch (Exception e){
            Bukkit.getLogger().severe("Error loading items in main menu: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
