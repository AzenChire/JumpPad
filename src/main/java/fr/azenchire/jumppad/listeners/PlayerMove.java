package fr.azenchire.jumppad.listeners;

import fr.azenchire.jumppad.Main;
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
                if (block.getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
                    Vector direction = player.getLocation().getDirection();
                    Vector launch = (new Vector(direction.getX(), (double)0.3F, direction.getZ())).normalize().multiply(3);
                    if (Main.getInstance().getConfig().getBoolean("sound-enabled", true)) {
                        player.playSound(player.getLocation(), "entity.firework_rocket.launch", 1.0f, 1.0f);
                    }
                    player.setVelocity(launch);
                }
            }
        }
    }
}