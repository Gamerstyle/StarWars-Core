package de.LPmitFelix.StarWars.Core.CMD;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.APIs.Ranks;
import de.LPmitFelix.StarWars.Core.BanManager.BanManager;
import de.LPmitFelix.StarWars.Core.BanManager.FileManager;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class unban_cmd implements CommandExecutor {

    public unban_cmd(main main) {
    }

    @SuppressWarnings({"static-access", "deprecation"})
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (sender instanceof Player) {
            Ranks ranks = new Ranks((Player) sender);
            if (!ranks.isMod()) {
                return true;
            }
        }
        if (args.length == 1) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            if (BanManager.isBanned(API.UUIDAPI().getUUID(player.getName()).toString())) {
                BanManager.unban(API.UUIDAPI().getUUID(player.getName()).toString());
                sender.sendMessage(FileManager.getPrefix() + "§aDu hast §6" + player.getName() + " §aEntbant");
                return true;
            }
            sender.sendMessage(FileManager.getPrefix() + "§cDieser Spieler ist nicht gebannt!");
            return true;
        }
        return false;
    }

}
