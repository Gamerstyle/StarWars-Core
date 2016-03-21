package de.LPmitFelix.StarWars.Core.CMD;

import de.LPmitFelix.StarWars.Core.API;
import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getPermissions_cmd implements CommandExecutor {

    public getPermissions_cmd(main main) {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("static-access")
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (API.RankAPI(p).isYouTuber()) {
                if(args.length==1){
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    API.getNickAPI(p, "§6" + target.getName()).nick();
                    p.sendMessage("§7[§5NICK§7]§aDein nickname ist §7: §6" + target.getName());
                }
                p.sendMessage("§7[§5NICK§7]§cBenutze /nick <name>");
            }

        }
        return false;
    }
}
