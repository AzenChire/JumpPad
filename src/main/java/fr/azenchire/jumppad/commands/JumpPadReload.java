package fr.azenchire.jumppad.commands;

import fr.azenchire.jumppad.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class JumpPadReload implements CommandExecutor {

    private final Main plugin;

    public JumpPadReload(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("jumppad.reload")) {
                sender.sendMessage("§cYou don't have permission to do that.");
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage("§7[§eJumpPad§7] §aConfiguration reloaded successfully!");
            plugin.getLogger().info("Configuration reloaded by " + sender.getName());
            return true;
        }

        sender.sendMessage("§eUsage: /jumppad reload");
        return true;
    }
}