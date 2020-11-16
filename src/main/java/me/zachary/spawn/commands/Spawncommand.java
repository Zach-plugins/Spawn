package me.zachary.spawn.commands;

import me.zachary.spawn.Spawn;
import me.zachary.spawn.utils.Chat;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.theprogramsrc.supercoreapi.spigot.commands.CommandResult;
import xyz.theprogramsrc.supercoreapi.spigot.commands.SpigotCommand;
import xyz.theprogramsrc.supercoreapi.spigot.utils.SpigotConsole;

public class Spawncommand extends SpigotCommand {
    private Spawn spawn;

    public Spawncommand(Spawn spawn) {
        this.spawn = spawn;
    }

    @Override
    public String getCommand() {
        return "spawn";
    }

    @Override
    public CommandResult onPlayerExecute(Player player, String[] strings) {
        if (!player.hasPermission("spawn.use")) return CommandResult.NO_PERMISSION;

        try {
            if(spawn.getConfig().getBoolean("Cost money.Enable")){
                if(spawn.econ.getBalance(player) >= spawn.getConfig().getDouble("Cost money.Cost")) {
                    Location location = (Location) spawn.getStorageFile().get("Spawn locations");
                    player.teleport(location);
                    EconomyResponse r = spawn.econ.withdrawPlayer(player, spawn.getConfig().getDouble("Cost money.Cost"));
                    if(r.transactionSuccess()) {
                        player.sendMessage(Chat.color(spawn.getConfig().getString("Withdraw money").replace("%money%", spawn.econ.format(r.amount)).replace("%balance%", spawn.econ.format(r.balance))));
                        player.sendMessage(Chat.color(spawn.getConfig().getString("Teleport to spawn")));
                    }
                }else
                    player.sendMessage(Chat.color(spawn.getConfig().getString("Not enough money").replace("%money%", spawn.econ.format(spawn.getConfig().getDouble("Cost money.Cost")))));
            }else{
                Location location = (Location) spawn.getStorageFile().get("Spawn locations");
                player.teleport(location);
                player.sendMessage(Chat.color(spawn.getConfig().getString("Teleport to spawn")));
            }

        } catch (Exception e) {
            player.sendMessage(Chat.color(spawn.getConfig().getString("No spawn point create")));
            return CommandResult.COMPLETED;
        }

        return CommandResult.COMPLETED;
    }

    @Override
    public CommandResult onConsoleExecute(SpigotConsole spigotConsole, String[] strings) {
        return CommandResult.COMPLETED;
    }
}
