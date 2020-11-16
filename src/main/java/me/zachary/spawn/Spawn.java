package me.zachary.spawn;

import me.zachary.spawn.commands.Reloadcommand;
import me.zachary.spawn.commands.Setspawncommand;
import me.zachary.spawn.commands.Spawncommand;
import me.zachary.spawn.storage.StorageFile;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import xyz.theprogramsrc.supercoreapi.global.Metrics;
import xyz.theprogramsrc.supercoreapi.spigot.SpigotPlugin;

public final class Spawn extends SpigotPlugin {

    public static Economy econ = null;

    @Override
    public void onPluginEnable() {
        int pluginId = 9178;
        Metrics metrics = new Metrics(this, pluginId);

        new Setspawncommand(this);
        new Spawncommand(this);
        new Reloadcommand(this);
        new StorageFile(this);

        saveDefaultConfig();

        if (!setupEconomy() ) {
            System.out.println(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public void onPluginLoad() {
        // On Plugin Load.
    }
    @Override
    public void onPluginDisable() {
        // On Plugin Disable.
    }

    @Override
    public boolean isPaid() {
        return false;
    }

    public StorageFile getStorageFile(){
        return new StorageFile(this);
    }
}
