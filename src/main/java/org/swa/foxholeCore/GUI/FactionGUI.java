package org.swa.foxholeCore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FactionGUI {
    public static final String TITLE = "Выбор фракции";
    public void open(Player player) {
        Inventory gui = Bukkit.createInventory(null,27, TITLE);

        ItemStack wardens = new ItemStack(Material.BLUE_BANNER);
        ItemMeta wardensMeta = wardens.getItemMeta();
        wardensMeta.setDisplayName("§9Вардены");
        wardens.setItemMeta(wardensMeta);

        ItemStack colonials = new ItemStack(Material.GREEN_BANNER);
        ItemMeta colonialsMeta = colonials.getItemMeta();
        colonialsMeta.setDisplayName("§aКолонисты");
        colonials.setItemMeta(colonialsMeta);

        gui.setItem(11, wardens);
        gui.setItem(15, colonials);

        player.openInventory(gui);
    }
}
