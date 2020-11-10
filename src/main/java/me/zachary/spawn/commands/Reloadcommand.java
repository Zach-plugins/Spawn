package me.zachary.spawn.commands;

import me.zachary.spawn.Spawn;
import me.zachary.spawn.utils.Chat;
import org.bukkit.entity.Player;
import xyz.theprogramsrc.supercoreapi.spigot.commands.CommandResult;
import xyz.theprogramsrc.supercoreapi.spigot.commands.SpigotCommand;
import xyz.theprogramsrc.supercoreapi.spigot.utils.SpigotConsole;

public class Reloadcommand extends SpigotCommand {
    private Spawn spawn;
    public Reloadcommand(Spawn spawn) {
        this.spawn = spawn;
    }

    @Override
    public String getCommand() {
        return "spawnreload";
    }

    @Override
    public CommandResult onPlayerExecute(Player player, String[] strings) {
        if(!player.hasPermission("spawn.reload")) return CommandResult.NO_PERMISSION;
        spawn.saveDefaultConfig();
        spawn.reloadConfig();
        player.sendMessage(Chat.color(spawn.getConfig().getString("Reload command")));
        return CommandResult.COMPLETED;
    }

    @Override
    public CommandResult onConsoleExecute(SpigotConsole spigotConsole, String[] strings) {
        return CommandResult.COMPLETED;
    }
}
