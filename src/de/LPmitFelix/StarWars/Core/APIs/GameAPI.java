package de.LPmitFelix.StarWars.Core.APIs;


public class GameAPI {

    private String Prefix;
    private String GameName;
    private int minPlayers;
    private int maxPlayers;
    private int countdown = 0;

    public GameAPI(String Prefix, String GameName, int minPlayers, int maxPlayers) {
        this.Prefix = Prefix;
        this.GameName = GameName;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String GameName) {
        this.GameName = GameName;
    }

    public int getMinPlayer() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public void startGame() {
        if (countdown == 0) {
            countdown = 30;
        }

    }
}
