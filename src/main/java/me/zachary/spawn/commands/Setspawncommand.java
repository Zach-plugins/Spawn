package me.zachary.spawn.commands;

import me.zachary.spawn.Spawn;
import me.zachary.spawn.utils.Chat;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.theprogramsrc.supercoreapi.spigot.commands.CommandResult;
import xyz.theprogramsrc.supercoreapi.spigot.commands.SpigotCommand;
import xyz.theprogramsrc.supercoreapi.spigot.utils.SpigotConsole;

public class Setspawncommand extends SpigotCommand {
    private Spawn spawn;

    public Setspawncommand(Spawn spawn) {
        this.spawn = spawn;
    }

    @Override
    public String getCommand() {
        return "setspawnpoint";
    }

    @Override
    public CommandResult onPlayerExecute(Player player, String[] strings) {
        if (!player.hasPermission("spawn.setspawnpoint")) return CommandResult.NO_PERMISSION;

        Location location = player.getLocation();
        spawn.getConfig().set("Spawn locations", location);
        spawn.saveConfig();

        player.sendMessage(Chat.color(spawn.getConfig().getString("Set spawn successful")));
        return CommandResult.COMPLETED;
    }

    @Override
    public CommandResult onConsoleExecute(SpigotConsole spigotConsole, String[] strings) {
        return CommandResult.COMPLETED;
    }
}
