package de.LPmitFelix.StarWars.Core.Listener;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.BanManager.BanManager;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerLoginEventListener implements Listener {

    private main Plugin;

    public PlayerLoginEventListener(main main) {
        this.Plugin = main;
        Plugin.getServer().getPluginManager().registerEvents(this, main);
    }

    @SuppressWarnings({"static-access"})
    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

        Player p = e.getPlayer();


        if (BanManager.isBanned(API.UUIDAPI().getUUID(p.getName()).toString())) {
            if (BanManager.getEnd(API.UUIDAPI().getUUID(p.getName()).toString()) == -1) {
                e.disallow(Result.KICK_BANNED, "§cDu wurdest vom Server gebannt!\n"
                        + "\n"
                        + "§6Grund: §a" + BanManager.getReson(API.UUIDAPI().getUUID(p.getName()).toString()) + "\n"
                        + "\n"
                        + "§6Verbleibende Zeit: §a" + BanManager.getBanTime(API.UUIDAPI().getUUID(p.getName()).toString()) + "\n"
                        + "\n"
                        + "§6Gebannt von: §a" + BanManager.getBanner(API.UUIDAPI().getUUID(p.getName()).toString()));
                return;
            }

        }

        addDefaultPermissions(e.getPlayer());

        if (API.RankAPI(p).isPremium()) {
            if (API.getOnlinePlayers() == API.getMaxPlayers()) {
                if (API.isPremiumkickOn()) {
                    for (Player po : Bukkit.getOnlinePlayers()) {
                        if (!po.hasPermission(API.RankAPI(po).getPremiumPermission())) {
                            po.kickPlayer("§cDu wurdest für ein höherrängigen Spieler gekickt!");
                            e.allow();
                            break;
                        }
                    }
                } else {
                    e.disallow(Result.KICK_FULL, "§cDer Server ist voll!");
                }
            }
        }
    }

    @SuppressWarnings("static-access")
    private void addDefaultPermissions(Player p) {
        if (API.RankAPI(p).getRank(API.UUIDAPI().getUUID(p.getName()).toString()).equalsIgnoreCase("admin")) {
            API.getPermisionsManager(p).addPermission("system.admin");
            API.getPermisionsManager(p).addPermission("system.developer");
            API.getPermisionsManager(p).addPermission("system.mod");
            API.getPermisionsManager(p).addPermission("system.builder");
            API.getPermisionsManager(p).addPermission("system.yt");
            API.getPermisionsManager(p).addPermission("system.premium");
            return;
        }
        if (API.RankAPI(p).getRank(API.UUIDAPI().getUUID(p.getName()).toString()).equalsIgnoreCase("dev")) {
            API.getPermisionsManager(p).addPermission("system.developer");
            API.getPermisionsManager(p).addPermission("system.builder");
            API.getPermisionsManager(p).addPermission("system.yt");
            API.getPermisionsManager(p).addPermission("system.premium");
            return;
        }
        if (API.RankAPI(p).getRank(API.UUIDAPI().getUUID(p.getName()).toString()).equalsIgnoreCase("mod")) {
            API.getPermisionsManager(p).addPermission("system.mod");
            API.getPermisionsManager(p).addPermission("system.builder");
            API.getPermisionsManager(p).addPermission("system.yt");
            API.getPermisionsManager(p).addPermission("system.premium");
            return;
        }
        if (API.RankAPI(p).getRank(API.UUIDAPI().getUUID(p.getName()).toString()).equalsIgnoreCase("builder")) {
            API.getPermisionsManager(p).addPermission("system.builder");
            API.getPermisionsManager(p).addPermission("system.yt");
            API.getPermisionsManager(p).addPermission("system.premium");
            return;
        }
        if (API.RankAPI(p).getRank(API.UUIDAPI().getUUID(p.getName()).toString()).equalsIgnoreCase("vip")) {
            API.getPermisionsManager(p).addPermission("system.yt");
            API.getPermisionsManager(p).addPermission("system.premium");
            return;
        }
        if (API.RankAPI(p).getRank(API.UUIDAPI().getUUID(p.getName()).toString()).equalsIgnoreCase("premium")) {
            API.getPermisionsManager(p).addPermission("system.premium");
            return;
        }
        API.getPermisionsManager(p).addPermission("nothing");
    }
}
