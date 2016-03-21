package de.LPmitFelix.StarWars.Core.CMD;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.APIs.Ranks;
import de.LPmitFelix.StarWars.Core.APIs.UUIDAPI;
import de.LPmitFelix.StarWars.Core.BanManager.BanManager;
import de.LPmitFelix.StarWars.Core.BanManager.FileManager;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class check_cmd implements CommandExecutor {

    public check_cmd(main main) {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("static-access")
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (sender instanceof Player) {
            if (!API.RankAPI((Player) sender).isMod()) {
                return true;
            }
        }
        if (args.length == 1) {
            @SuppressWarnings("deprecation")
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

            if (BanManager.isBanned(API.UUIDAPI().getUUID(player.getName()).toString())) {
                sender.sendMessage("§a---------------" + FileManager.getPrefix() + "§a---------------");
                sender.sendMessage("");
                sender.sendMessage("§6Spieler§7: §a" + player.getName());
                sender.sendMessage("§6BanStatus§7: §4Gebannt");
                sender.sendMessage("§6Grund§7: §a" + BanManager.getReson(API.UUIDAPI().getUUID(player.getName()).toString()));
                sender.sendMessage("§6Gebannt von§7: §a" + BanManager.getBanner(API.UUIDAPI().getUUID(player.getName()).toString()));
                if(Ranks.getRank(API.UUIDAPI().getUUID(player.getName()).toString()) != null){
                    sender.sendMessage("§6Rang§7: §a" + Ranks.getRank(API.UUIDAPI().getUUID(player.getName()).toString()));
                }else{
                    sender.sendMessage("§6Rang§7: §adefault");
                }
                sender.sendMessage("");
                sender.sendMessage("§a---------------" + FileManager.getPrefix() + "§a---------------");
                return true;
            }
            sender.sendMessage("§a---------------" + FileManager.getPrefix() + "§a---------------");
            sender.sendMessage("");
            sender.sendMessage("§6Spieler§7: §a" + player.getName());
            sender.sendMessage("§6BanStatus§7: §aNicht gebannt");
            if(Ranks.getRank(API.UUIDAPI().getUUID(player.getName()).toString()) != null){
                sender.sendMessage("§6Rang§7: §a" + Ranks.getRank(API.UUIDAPI().getUUID(player.getName()).toString()));
            }else{
                sender.sendMessage("§6Rang§7: §adefault");
            }
            sender.sendMessage("");
            sender.sendMessage("§a---------------" + FileManager.getPrefix() + "§a---------------");
            return true;
        }
        sender.sendMessage("§c/check <Spieler>");
        return false;
    }

}
