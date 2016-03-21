package de.LPmitFelix.StarWars.Core.util;

import de.LPmitFelix.StarWars.Core.main;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class PermissionsManager {

    private Player p;


    public PermissionsManager(Player player) {
        this.p = player;
    }

    public void addPermission(String Permission) {
        PermissionAttachment attachment = p.addAttachment(main.getInstance());
        attachment.setPermission(Permission, true);
    }

    public void removePermission(String Permission) {
        PermissionAttachment attachment = p.addAttachment(main.getInstance());
        attachment.setPermission(Permission, false);
    }

}
