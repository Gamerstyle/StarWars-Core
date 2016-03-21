package de.LPmitFelix.StarWars.Core.Listener;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

    private main Plugin;

    public PlayerJoinEventListener(main main) {
        this.Plugin = main;
        Plugin.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        API.setOnlinePlayers(API.getOnlinePlayers() + 1);
    }


}
