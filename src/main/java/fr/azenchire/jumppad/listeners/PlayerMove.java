package fr.azenchire.jumppad.listeners;

import fr.azenchire.jumppad.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.lang.module.Configuration;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        FileConfiguration config = Main.getInstance().getConfig();
        Block from = event.getFrom().getBlock();
        Block to = event.getTo().getBlock();
        Player player = event.getPlayer();

        if (from.equals(to)) return;

        if (to.getType() == Material.valueOf(config.getString("launch-block"))) {
            if (config.getBoolean("prevent-sneak") && player.isSneaking()){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("sneak-message")));
                return;
            }
            if (config.getBoolean("enable-sound", true)) {
                player.playSound(player.getLocation(), Sound.valueOf(config.getString("sound-name")), 1.0f, 1.0f);
            }
            if (config.getBoolean("enable-message", true)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("launch-message")));
            }
            if (config.getBoolean("enable-particles", true)) {
                try {
                    Particle particle = Particle.valueOf(config.getString("particle-type").toUpperCase());
                    player.getWorld().spawnParticle(particle, player.getLocation(), 20, 0.3, 0.3, 0.3, 0);
                } catch (IllegalArgumentException e) {
                    Bukkit.getLogger().warning("Invalid particle type in config.yml");
                }
            }

            Vector direction = player.getLocation().getDirection();
            Vector launch = (new Vector(direction.getX(), config.getDouble("launch-vertical"), direction.getZ())).normalize().multiply(config.getDouble("launch-multiplier"));
            player.setVelocity(launch);
        }
    }
}