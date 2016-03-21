package de.LPmitFelix.StarWars.Core.Listener;

import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEventListener implements Listener {


    private main Plugin;

    public PlayerChatEventListener(main main) {
        this.Plugin = main;
        Plugin.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        e.setCancelled(true);
        Bukkit.broadcastMessage(p.getDisplayName() + " §8> §7" + e.getMessage());

    }
}
