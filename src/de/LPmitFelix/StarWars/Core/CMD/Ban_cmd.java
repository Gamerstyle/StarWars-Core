package de.LPmitFelix.StarWars.Core.CMD;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.APIs.Ranks;
import de.LPmitFelix.StarWars.Core.BanManager.FileManager;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban_cmd implements CommandExecutor {

    @SuppressWarnings("unused")
    private main Plugin;

    public Ban_cmd(main main) {
        this.Plugin = main;
    }

    @SuppressWarnings({"deprecation", "static-access"})
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        String bannedBy;
        if (sender instanceof Player) {
            Ranks ranks = new Ranks((Player) sender);
            if (!ranks.isMod()) {
                return true;
            }
            Player p = (Player) sender;
            bannedBy = p.getName();
        } else {
            bannedBy = "Console";
        }
        if (args.length == 2) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            if (API.banAPI(player).isBanned()) {
                sender.sendMessage(FileManager.getPrefix() + "§cDer Spieler ist Berreits vom Server Gebannt!");
                return true;
            }
            API.banAPI(player).Ban(args[1], bannedBy);
            sender.sendMessage(FileManager.getPrefix() + "§6Du hast §a" + player.getName() + " §cPermanent §6Gebannt!");
            return true;
        }
        sender.sendMessage("§c/ban <Spieler> <Grund>");
        return false;
    }


}
