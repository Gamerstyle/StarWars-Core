package de.LPmitFelix.StarWars.Core.APIs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SetPlayerFormat {

    private Player p;

    public SetPlayerFormat(Player player) {
        this.p = player;
        setColor();
    }

    private void setColor() {
        Ranks rank = new Ranks(p);
        if (rank.isAdmin()) {
            p.setDisplayName("§4" + p.getName() + "§r");
            setTag(p, "§4Admin §7|§4 ", "admin");
            return;
        }
        if (rank.isMod()) {
            p.setDisplayName("§c" + p.getName() + "§r");
            setTag(p, "§cMod §7|§c ", "mod");
            return;
        }
        if (rank.isBuilder()) {
            p.setDisplayName("§b" + p.getName() + "§r");
            setTag(p, "§bBuilder §7|§b ", "builder");
            return;
        }
        if (rank.isYouTuber()) {
            p.setDisplayName("§5" + p.getName() + "§r");
            setTag(p, "§5", "vip");
            return;
        }
        if (rank.isPremium()) {
            p.setDisplayName("§6" + p.getName() + "§r");
            setTag(p, "§6", "premium");
            return;
        }
        p.setDisplayName("§a" + p.getName() + "§r");
        setTag(p, "§a", "default");

    }


    @SuppressWarnings("deprecation")
    private void setTag(Player p, String tag, String teamname) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.getTeam(p.getName());
        if (team == null) {
            team = board.registerNewTeam(p.getName());
        }
        team.setPrefix(tag);
        team.addPlayer(p);
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.setScoreboard(board);
        }
    }
}
