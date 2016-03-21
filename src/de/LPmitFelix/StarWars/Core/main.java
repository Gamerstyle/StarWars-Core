package de.LPmitFelix.StarWars.Core;

import de.LPmitFelix.StarWars.Core.BanManager.FileManager;
import de.LPmitFelix.StarWars.Core.CMD.*;
import de.LPmitFelix.StarWars.Core.Listener.PlayerChatEventListener;
import de.LPmitFelix.StarWars.Core.Listener.PlayerJoinEventListener;
import de.LPmitFelix.StarWars.Core.Listener.PlayerLoginEventListener;
import de.LPmitFelix.StarWars.Core.Listener.PlayerQuitEventListener;
import de.LPmitFelix.StarWars.Core.MySQL.Ban_MySQL;
import de.LPmitFelix.StarWars.Core.netty.NettyClient;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class main extends JavaPlugin {

    private static main instance;
    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    Team team;

    public static main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        FileManager.setStandart();
        FileManager.readConfig();

        API.setPremiumKick(true);

        new PlayerLoginEventListener(this);
        new PlayerChatEventListener(this);
        new PlayerJoinEventListener(this);
        new PlayerQuitEventListener(this);

        Ban_MySQL.connect();
        Ban_MySQL.createTable();

        getCommand("ban").setExecutor(new Ban_cmd(this));
        getCommand("check").setExecutor(new check_cmd(this));
        getCommand("unban").setExecutor(new unban_cmd(this));
        getCommand("setrank").setExecutor(new setrank_cmd(this));
        getCommand("nick").setExecutor(new getPermissions_cmd(this));


    }


}
