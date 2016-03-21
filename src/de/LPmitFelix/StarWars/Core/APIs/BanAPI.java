package de.LPmitFelix.StarWars.Core.APIs;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.BanManager.BanManager;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class BanAPI {

    private OfflinePlayer p;

    public BanAPI(OfflinePlayer p) {
        this.p = p;
    }

    @SuppressWarnings("static-access")
    public void Ban(String reson, String BannedBy) {
        BanManager.ban(API.UUIDAPI().getUUID(p.getName()).toString(), p.getName(), reson, -1, BannedBy);
        BanManager.ban(API.UUIDAPI().getUUID(p.getName()).toString(), p.getName(), reson, -1, BannedBy);
    }

    @SuppressWarnings("static-access")
    public void unBan() {
        BanManager.unban(API.UUIDAPI().getUUID(p.getName()).toString());
    }

    @SuppressWarnings("static-access")
    public boolean isBanned() {
        return BanManager.isBanned(API.UUIDAPI().getUUID(p.getName()).toString());
    }

    @SuppressWarnings("static-access")
    public String getReson() {
        return BanManager.getReson(API.UUIDAPI().getUUID(p.getName()).toString());
    }

    @SuppressWarnings("static-access")
    public String getBanedBy() {
        return BanManager.getBanner(API.UUIDAPI().getUUID(p.getName()).toString());
    }

    public List<String> bannedPlayers() {
        return BanManager.getBannedPlayers();
    }

}
