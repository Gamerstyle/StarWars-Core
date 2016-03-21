package de.LPmitFelix.StarWars.Core.BanManager;

import de.LPmitFelix.StarWars.Core.MySQL.Ban_MySQL;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static String Prefix;

    public static File getConfigFile() {
        return new File("plugins/BanManager", "config.yml");
    }

    public static FileConfiguration getConfiguration() {
        return YamlConfiguration.loadConfiguration(getConfigFile());
    }

    public static void setStandart() {
        FileConfiguration cfg = getConfiguration();
        cfg.options().copyDefaults(true);
        cfg.addDefault("Prefix", "&6[&4Ban&6]");
        cfg.addDefault("MySQL.host", "host");
        cfg.addDefault("MySQL.port", 3306);
        cfg.addDefault("MySQL.user", "user");
        cfg.addDefault("MySQL.password", "Passwort");
        cfg.addDefault("MySQL.database", "Database");

        try {
            cfg.save(getConfigFile());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void readConfig() {
        FileConfiguration cfg = getConfiguration();
        Prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix"));
        Ban_MySQL.host = cfg.getString("MySQL.host");
        Ban_MySQL.port = cfg.getInt("MySQL.port");
        Ban_MySQL.username = cfg.getString("MySQL.user");
        Ban_MySQL.password = cfg.getString("MySQL.password");
        Ban_MySQL.database = cfg.getString("MySQL.database");
    }

    public static String getPrefix() {
        return Prefix;
    }
}
