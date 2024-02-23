package at.nniklxs.teleporter;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic


        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        config = getConfig();


        TeleporterCommand teleporterCommand = new TeleporterCommand(config);

        this.getCommand("teleporter").setExecutor(teleporterCommand);
        Bukkit.getPluginManager().registerEvents(teleporterCommand, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
