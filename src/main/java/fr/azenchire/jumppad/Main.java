package fr.azenchire.jumppad;

import fr.azenchire.jumppad.commands.JumpPadReload;
import fr.azenchire.jumppad.commands.JumpPadTabCompleter;
import fr.azenchire.jumppad.listeners.PlayerMove;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getCommand("jumppad").setExecutor(new JumpPadReload(this));
        getCommand("jumppad").setTabCompleter(new JumpPadTabCompleter());

        getLogger().info("JumpPad is enabled!");
    }

    public static Main getInstance() {
        return instance;
    }
}