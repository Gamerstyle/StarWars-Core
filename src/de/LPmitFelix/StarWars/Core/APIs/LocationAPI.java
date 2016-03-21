package de.LPmitFelix.StarWars.Core.APIs;

import de.LPmitFelix.StarWars.Core.MySQL.Ban_MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationAPI {

    public static void setNewLocation(Location location, String LocationName) {

        int X = location.getBlockX();
        int Y = location.getBlockY();
        int Z = location.getBlockZ();
        String world = location.getWorld().getName();

        String loc = X + " :: " + Y + " :: " + Z + " :: " + world;
        if (isLocationExists(LocationName)) {
            Ban_MySQL.update("UPDATE Locations SET Location='" + loc + "'WHERE name='" + LocationName + "'");
            Ban_MySQL.update("UPDATE Locations SET Location='" + loc + "'WHERE name='" + LocationName + "'");
        }
        Ban_MySQL.update("INSERT INTO Locations (Location, name) VALUES ('" + loc + "','" + LocationName + "')");
        Ban_MySQL.update("INSERT INTO Locations (Location, name) VALUES ('" + loc + "','" + LocationName + "')");

    }

    public static Location getLocation(String LocationName) {
        Location loc = new Location(Bukkit.getWorld("world"), 1.0, 1.0, 1.0);

        ResultSet rs = Ban_MySQL.getResult("SELECT Location FROM Locations WHERE name='" + LocationName + "'");
        try {


            String[] locs = rs.getString("Location").split(" :: ");
            System.out.println(rs.getString("Location"));
            return new Location(Bukkit.getWorld(locs[3]), Integer.valueOf(locs[0]), Integer.valueOf(locs[1]), Integer.valueOf(locs[2]));


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return loc;

    }

    public static boolean isLocationExists(String LocationName) {

        ResultSet rs = Ban_MySQL.getResult("SELECT * FROM Locations WHERE name='" + LocationName + "'");
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
}
