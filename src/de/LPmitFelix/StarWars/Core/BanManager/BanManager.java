package de.LPmitFelix.StarWars.Core.BanManager;

import de.LPmitFelix.StarWars.Core.MySQL.Ban_MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanManager {

    public static void ban(String UUID, String Playername, String reson, int seconds, String BannedBy) {

        Ban_MySQL.update("INSERT INTO Banned (Spielername, UUID, Ende, Grund, Banner) VALUES ('" + Playername + "','" + UUID + "','" + seconds + "','" + reson + "','" + BannedBy + "')");
        if (Bukkit.getPlayer(Playername) != null) {

            Bukkit.getPlayer(Playername).kickPlayer("§cDu wurdest vom Server gebannt!\n"
                    + "\n"
                    + "§6Grund: §a" + getReson(UUID) + "\n"
                    + "\n"
                    + "§6Verbleibende Zeit: §a" + getBanTime(UUID) + "\n"
                    + "\n"
                    + "§6Gebannt von: §a" + getBanner(UUID));

        }
    }

    public static String getBanTime(String UUID) {
        return "Permanent";
    }

    public static void unban(String UUID) {
        Ban_MySQL.update("DELETE FROM Banned WHERE UUID='" + UUID + "'");
    }

    public static boolean isBanned(String UUID) {
        ResultSet rs = Ban_MySQL.getResult("SELECT * FROM Banned WHERE UUID='" + UUID + "'");
        try {
            while (rs.next()) {
                return rs.next();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static String getReson(String UUID) {
        ResultSet rs = Ban_MySQL.getResult("SELECT * FROM Banned WHERE UUID='" + UUID + "'");
        try {
            while (rs.next()) {
                return rs.getString("Grund");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getBanner(String UUID) {
        ResultSet rs = Ban_MySQL.getResult("SELECT * FROM Banned WHERE UUID='" + UUID + "'");
        try {
            while (rs.next()) {
                return rs.getString("Banner");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Long getEnd(String UUID) {
        ResultSet rs = Ban_MySQL.getResult("SELECT * FROM Banned WHERE UUID='" + UUID + "'");
        try {
            while (rs.next()) {
                return rs.getLong("Ende");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getBannedPlayers() {
        List<String> list = new ArrayList<String>();
        ResultSet rs = Ban_MySQL.getResult("SELECT * FROM Banned");
        try {
            while (rs.next()) {
                list.add(rs.getString("Spielername"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }
}
