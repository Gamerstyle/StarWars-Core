package de.LPmitFelix.StarWars.Core.Listener;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerQuitEventListener implements Listener {

    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    Team team;
    private main Plugin;

    public PlayerQuitEventListener(main main) {
        this.Plugin = main;
        Plugin.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        API.setOnlinePlayers(API.getOnlinePlayers() - 1);
        team = board.getTeam(e.getPlayer().getName());
        if (team != null) {
            team.unregister();
        }
    }
}
