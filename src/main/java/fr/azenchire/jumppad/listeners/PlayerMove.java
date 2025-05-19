package fr.azenchire.jumppad.listeners;

import fr.azenchire.jumppad.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.LEFT_CLICK_BLOCK) {
            if (block != null) {
                if (block.getType() == Material.valueOf(Main.getInstance().getConfig().getString("jump-block"))) {
                    Vector direction = player.getLocation().getDirection();
                    Vector launch = (new Vector(direction.getX(), Main.getInstance().getConfig().getDouble("launch-vertical"), direction.getZ())).normalize().multiply(Main.getInstance().getConfig().getDouble("launch-multiplier"));
                    if (Main.getInstance().getConfig().getBoolean("enable-sound", true)) {
                        player.playSound(player.getLocation(), "entity.firework_rocket.launch", 1.0f, 1.0f);
                    }
                    if (Main.getInstance().getConfig().getBoolean("enable-message", true)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("message")));
                    }
                    player.setVelocity(launch);
                }
            }
        }
    }
}