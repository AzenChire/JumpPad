package fr.azenchire.jumppad.listeners;

import fr.azenchire.jumppad.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        FileConfiguration config = Main.getInstance().getConfig();
        Player player = event.getPlayer();
        Block from = event.getFrom().getBlock();
        Block to = event.getTo().getBlock();

        if (from.equals(to)) return;

        String launchBlockType = config.getString("launch-block", "SLIME_BLOCK").toUpperCase();
        Material launchMaterial = Material.matchMaterial(launchBlockType);

        if (launchMaterial == null) {
            Bukkit.getLogger().warning("Invalid launch-block in config: " + launchBlockType);
            return;
        }

        if (to.getType() != launchMaterial) return;

        // Sneak prevention
        if (config.getBoolean("prevent-sneak") && player.isSneaking()) {
            String sneakMessage = config.getString("sneak-message");
            if (sneakMessage != null && !sneakMessage.isEmpty()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', sneakMessage));
            }
            return;
        }

        // Play sound
        if (config.getBoolean("enable-sound", true)) {
            String soundName = config.getString("sound-name", "ENTITY_FIREWORK_ROCKET_LAUNCH").toUpperCase();
            try {
                Sound sound = Sound.valueOf(soundName);
                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            } catch (IllegalArgumentException e) {
                Bukkit.getLogger().warning("Invalid sound name in config: " + soundName);
            }
        }

        // Send message
        if (config.getBoolean("enable-message", true)) {
            String launchMessage = config.getString("launch-message");
            if (launchMessage != null && !launchMessage.isEmpty()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', launchMessage));
            }
        }

        // Show particles
        if (config.getBoolean("enable-particles", true)) {
            String particleType = config.getString("particle-type", "CLOUD").toUpperCase();
            try {
                Particle particle = Particle.valueOf(particleType);
                player.getWorld().spawnParticle(particle, player.getLocation(), 20, 0.3, 0.3, 0.3, 0);
            } catch (IllegalArgumentException e) {
                Bukkit.getLogger().warning("Invalid particle type in config: " + particleType);
            }
        }

        // Launch player
        double vertical = config.getDouble("launch-vertical", 1.0);
        double multiplier = config.getDouble("launch-multiplier", 1.5);
        Vector direction = player.getLocation().getDirection().setY(vertical).normalize().multiply(multiplier);
        player.setVelocity(direction);
    }
}
