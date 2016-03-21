package de.LPmitFelix.StarWars.Core;

import de.LPmitFelix.StarWars.Core.APIs.*;
import de.LPmitFelix.StarWars.Core.util.PermissionsManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class API {
    private static boolean isPremiumkickOn;
    private static NickAPI nick;
    private static int onlinePlayers;
    private static int maxPlayers;
    private static GameAPI gameAPI;
    private static BanAPI banAPI;
    private static PermissionsManager perms;
    private static Ranks ranks;
    private static LocationAPI locAPI;
    private static UUIDAPI uuids;
    @SuppressWarnings("unused")
    private static SetPlayerFormat setPlayerFormat;
    @SuppressWarnings("unused")
    private static setTabListVormat setTabListFormat;

    public static GameAPI GameAPI(String Prefix, String GameName, int minPlayers, int maxPlayers) {
        gameAPI = new GameAPI(Prefix, GameName, minPlayers, maxPlayers);
        API.maxPlayers = maxPlayers;
        return gameAPI;
    }

    public static NickAPI getNickAPI(Player player, String name){
        nick= new NickAPI(player, name);
        return nick;
    }

    public static GameAPI getGameAPI() {
        return gameAPI;
    }

    public static PermissionsManager getPermisionsManager(Player player) {
        perms = new PermissionsManager(player);
        return perms;
    }

    public static BanAPI banAPI(OfflinePlayer player) {
        banAPI = new BanAPI(player);
        return banAPI;
    }

    public static UUIDAPI UUIDAPI() {
        return uuids;
    }

    public static Ranks RankAPI(Player player) {
        ranks = new Ranks(player);
        return ranks;
    }

    public static void setPlayerFormat(Player p) {
        setPlayerFormat = new SetPlayerFormat(p);
    }

    public static void setTabListFormat(String Header, String Footer, Player p) {
        setTabListFormat = new setTabListVormat(Header, Footer, p);
    }

    public static LocationAPI LocationAPI() {
        return locAPI;
    }

    public static boolean isPremiumkickOn() {
        return isPremiumkickOn;
    }

    public static void setPremiumKick(boolean on) {
        isPremiumkickOn = on;
    }

    public static int getOnlinePlayers() {
        return onlinePlayers;
    }

    public static void setOnlinePlayers(int onlinePlayers) {
        API.onlinePlayers = onlinePlayers;
    }

    public static int getMaxPlayers() {
        return maxPlayers;
    }

    public static void setMaxPlayers(int maxPlayers) {
        API.maxPlayers = maxPlayers;
    }
}
