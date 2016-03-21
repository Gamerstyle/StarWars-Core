package de.LPmitFelix.StarWars.Core.CMD;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.APIs.Ranks;
import de.LPmitFelix.StarWars.Core.MySQL.Ban_MySQL;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setrank_cmd implements CommandExecutor {

    public setrank_cmd(main main) {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Ranks rank = new Ranks(p);
            if (rank.isMod() || p.getName().equalsIgnoreCase("Gamerstyle")) {
                if (args.length == 2) {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
                    setRank(API.UUIDAPI().getUUID(player.getName()).toString(), player.getName(), args[1].toLowerCase());
                    p.sendMessage("§7[§cSystem§7]§aDu hast dem Spieler§6 " + player.getName() + " §aden §6" + args[1].toLowerCase() + " §aRang gegeben");
                    if (Bukkit.getPlayer(player.getName()) != null) {
                        Player ranked = Bukkit.getPlayer(player.getName());
                        API.setPlayerFormat(ranked);
                    }
                    return true;
                }
                p.sendMessage("§c/setrank <Spieler> <Rank>");
            }
        }
        return false;
    }

    private void setRank(String UUID, String Playername, String Rank) {
        Ban_MySQL.update("INSERT INTO Ranks (Spielername, UUID, Rank) VALUES ('" + Playername + "','" + UUID + "','" + Rank + "')");
    }

}