package at.nniklxs.teleporter;

import org.bukkit.*;
import org.bukkit.inventory.*;

public class InventoryManager {

    public static Inventory inv_9x5(final InventoryHolder player, final String name) {
        final Inventory inv = Bukkit.createInventory(player, 45, name);
        final ItemStack grau = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7).setDisplayName(" ").create();
        final ItemStack dunkelgrau = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1, (short)7).setDisplayName(" ").create();
        inv.setItem(0, grau);
        inv.setItem(1, dunkelgrau);
        inv.setItem(2, grau);
        inv.setItem(3, grau);
        inv.setItem(4, grau);
        inv.setItem(5, grau);
        inv.setItem(6, grau);
        inv.setItem(7, dunkelgrau);
        inv.setItem(8, grau);
        inv.setItem(9, dunkelgrau);
        inv.setItem(10, grau);
        inv.setItem(11, grau);
        inv.setItem(12, grau);
        inv.setItem(13, grau);
        inv.setItem(14, grau);
        inv.setItem(15, grau);
        inv.setItem(16, grau);
        inv.setItem(17, dunkelgrau);
        inv.setItem(18, grau);
        inv.setItem(20, grau);
        inv.setItem(22, grau);
        inv.setItem(24, grau);
        inv.setItem(26, grau);
        inv.setItem(27, dunkelgrau);
        inv.setItem(28, grau);
        inv.setItem(29, grau);
        inv.setItem(30, grau);
        inv.setItem(31, grau);
        inv.setItem(32, grau);
        inv.setItem(33, grau);
        inv.setItem(34, grau);
        inv.setItem(35, dunkelgrau);
        inv.setItem(36, grau);
        inv.setItem(37, dunkelgrau);
        inv.setItem(38, grau);
        inv.setItem(39, grau);
        inv.setItem(40, grau);
        inv.setItem(41, grau);
        inv.setItem(42, grau);
        inv.setItem(43, dunkelgrau);
        inv.setItem(44, grau);
        return inv;
    }
}


