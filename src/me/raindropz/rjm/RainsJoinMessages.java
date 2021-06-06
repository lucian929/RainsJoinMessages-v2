package me.raindropz.rjm;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RainsJoinMessages extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();
    @Override
    public void onEnable(){
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);

        Bukkit.getConsoleSender().sendMessage("RainsJoinMessages-v2 Version: " + config.getString("version") + " is now enabled!");
    }
    @Override
    public void onDisable(){
        saveConfig();

        Bukkit.getConsoleSender().sendMessage("RainsJoinMessages-v2 Version: " + config.getString("version") + " is now disabled.");
    }

    public class JoinListener implements Listener{
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlayerJoin(PlayerJoinEvent event) {
            String joinText = config.getString("join-message");

            joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
            event.setJoinMessage(joinText);
        }
    }
}
// The End!