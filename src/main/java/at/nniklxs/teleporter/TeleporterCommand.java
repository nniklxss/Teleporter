package at.nniklxs.teleporter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class TeleporterCommand implements CommandExecutor, Listener {

    private final FileConfiguration config;

    public TeleporterCommand(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;
        player.openInventory(getStartInventory(player));
        player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1.0F, 1.0F);

        return true;
    }

    public Inventory getStartInventory(final Player owner) {
        final Inventory inv = InventoryManager.inv_9x5(owner, "§8» §9Teleporter");

        Player player = owner;

        for (int i = 1; i <= 4; i++) {
            String itemName = config.getString("teleporter.items." + i + ".name");
            List<String> itemLore = config.getStringList("teleporter.items." + i + ".lore");
            String permission = config.getString("teleporter.items." + i + ".permission");
            int x = config.getInt("teleporter.items." + i + ".coordinates.x");
            int y = config.getInt("teleporter.items." + i + ".coordinates.y");
            int z = config.getInt("teleporter.items." + i + ".coordinates.z");

            ItemStack item = new ItemBuilder(Material.TERRACOTTA, 1)
                    .setDisplayName(itemName)
                    .setLore(itemLore)
                    .create();

            inv.setItem(getInventorySlot(i), item);

            if (permission != null && !permission.isEmpty() && !player.hasPermission(permission)) {
                player.addAttachment(Main.getPlugin(Main.class)).setPermission(permission, true);
            }
        }

        String questName = config.getString("teleporter.items.quest.name");
        List<String> questLore = config.getStringList("teleporter.items.quest.lore");

        ItemStack questItem = new ItemBuilder(Material.ENDER_EYE, 1)
                .setDisplayName(questName)
                .setLore(questLore)
                .create();

        inv.setItem(40, questItem);

        return inv;
    }


    @EventHandler
    public void onClick(final InventoryClickEvent e) throws IOException {
        if (e == null || e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null || e.getView() == null || e.getView().getTitle() == null || e.getRawSlot() < 0 || e.getSlot() < 0 || e.getClickedInventory() == null || e.getClickedInventory().getType() == null || e.getInventory() == null || e.getInventory().getType() == null || e.getClick() == null) {
            return;
        }

        final Player player = (Player) e.getWhoClicked();
        if (!e.getView().getTitle().equals("§8» §9Teleporter") || e.getClickedInventory().getType() == InventoryType.PLAYER) {
            return;
        }

        e.setCancelled(true);
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null) {
            return;
        }

        final ItemMeta clickedItemMeta = clickedItem.getItemMeta();
        if (clickedItemMeta == null) {
            return;
        }

        final String itemName = clickedItemMeta.getDisplayName();
        if (itemName == null) {
            return;
        }

        for (int i = 1; i <= 4; i++) {
            String teleporterName = config.getString("teleporter.items." + i + ".name");
            if (itemName.equals(teleporterName)) {
                teleportPlayer(player, "teleporter.items." + i);
                return;
            }
        }

        if (itemName.equals(config.getString("teleporter.items.quest.name"))) {
            player.performCommand("quests");
        }
    }

    private void teleportPlayer(Player player, String teleporterPath) {
        String permission = config.getString(teleporterPath + ".permission");

        if (!player.hasPermission(permission)) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
            player.sendMessage("Du hast nicht die erforderliche Berechtigung, um diesen Teleporter zu verwenden!");
            return;
        }

        int x = config.getInt(teleporterPath + ".coordinates.x");
        int y = config.getInt(teleporterPath + ".coordinates.y");
        int z = config.getInt(teleporterPath + ".coordinates.z");

        Location location = new Location(Bukkit.getWorld("world"), x, y, z, 0, 0);
        player.teleport(location);
    }


    private int getInventorySlot(int index) {
        switch (index) {
            case 1:
                return 19;
            case 2:
                return 21;
            case 3:
                return 23;
            case 4:
                return 25;
            default:
                return -1;
        }
    }
}
