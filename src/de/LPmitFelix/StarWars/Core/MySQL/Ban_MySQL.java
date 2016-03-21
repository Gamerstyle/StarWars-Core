package de.LPmitFelix.StarWars.Core.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ban_MySQL {

    public static String host;
    public static int port;
    public static String database;
    public static String username;
    public static String password;
    public static Connection con;

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("[MySQL]verbunden");
            } catch (SQLException e) {
                System.out.println("[MySQL]konte keine Verbindung herstellen");
            }
        }
    }

    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println("[MySQL]verbindung getrennt");
            } catch (SQLException e) {
                System.out.println("[MySQL]verbindung konnte nicht getrennt werden");
            }
        }
    }

    public static boolean isConnected() {
        if (con != null) {
            return true;
        }
        return false;
    }

    public static void createTable() {
        try {
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Banned (Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100), Banner VARCHAR(100))");
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Ranks (Spielername VARCHAR(100), UUID VARCHAR(100), Rank VARCHAR(100))");
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Locations (Location VARCHAR(100), name VARCHAR(100))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String qry) {
        try {
            con.createStatement().executeUpdate(qry);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String qry) {
        try {
            return con.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
