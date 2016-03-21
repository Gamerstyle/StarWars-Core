package de.LPmitFelix.StarWars.Core.APIs;

import de.LPmitFelix.StarWars.Core.MySQL.Ban_MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ranks {

    private Player p;

    private String Admin;
    private String Dev;
    private String Mod;
    private String Builder;
    private String YouTuber;
    private String Premium;

    public Ranks(Player player) {
        this.p = player;

        this.setAdminPermission("system.admin");
        this.setDeveloperPermission("system.developer");
        this.setModeratorPermission("system.mod");
        this.setBuilderPermission("system.builder");
        this.setYouTuberPermission("system.yt");
        this.setPremiumPermission("system.premium");
    }

    public static String getRank(String UUID) {
        ResultSet rs = Ban_MySQL.getResult("SELECT * FROM Ranks WHERE UUID='" + UUID + "'");
        try {
            while (rs.next()) {
                return rs.getString("Rank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAdminPermission() {
        return Admin;
    }

    public void setAdminPermission(String Permission) {
        Admin = Permission;
    }

    public String getDeveloperPermission() {
        return Dev;
    }

    public void setDeveloperPermission(String Permission) {
        Dev = Permission;
    }

    public String getModeratorPermission() {
        return Mod;
    }

    public void setModeratorPermission(String Permission) {
        Mod = Permission;
    }

    public String getBuilderPermission() {
        return Builder;
    }

    public void setBuilderPermission(String Permission) {
        Builder = Permission;
    }

    public String getYouTuberPermission() {
        return YouTuber;
    }

    public void setYouTuberPermission(String Permission) {
        YouTuber = Permission;
    }

    public String getPremiumPermission() {
        return Premium;
    }

    public void setPremiumPermission(String Permission) {
        Premium = Permission;
    }

    public boolean isAdmin() {
        if (p.hasPermission(Admin)) {
            return true;
        }
        return false;
    }

    public boolean isDev() {
        if (p.hasPermission(Dev)) {
            return true;
        }
        return false;
    }

    public boolean isMod() {
        if (p.hasPermission(Mod)) {
            return true;
        }
        return false;
    }

    public boolean isBuilder() {
        if (p.hasPermission(Builder)) {
            return true;
        }
        return false;
    }

    public boolean isYouTuber() {
        if (p.hasPermission(YouTuber)) {
            return true;
        }
        return false;
    }

    public boolean isPremium() {
        if (p.hasPermission(Premium)) {
            return true;
        }
        return false;
    }
}
