package de.LPmitFelix.StarWars.Core.APIs;

import com.mojang.authlib.GameProfile;
import de.LPmitFelix.StarWars.Core.main;
import net.minecraft.server.v1_9_R1.ChatComponentText;
import net.minecraft.server.v1_9_R1.Packet;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R1.WorldSettings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Felix on 21.03.2016.
 */
public class NickAPI {

    private Player p;
    private String name;
    private String tab;

    public NickAPI(Player p, String name){
        this.p=p;
        this.name=name;
        this.tab=name;
    }



    private void set(Object instance, String name, Object value) {
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void nick() {
        GameProfile gameProfile = ((CraftPlayer) p).getProfile();
        PacketPlayOutPlayerInfo remove = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        set(remove, "b", Arrays.asList(remove.new PlayerInfoData(gameProfile, 0, null, null)));
        set(gameProfile, "name", name);
        PacketPlayOutPlayerInfo add = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        set(add, "b", Arrays.asList(add.new PlayerInfoData(gameProfile, 0, WorldSettings.EnumGamemode.NOT_SET, new ChatComponentText("§6" + tab))));
        sendPacket(remove, add);

        for(Player po : Bukkit.getOnlinePlayers()){
            po.hidePlayer(p);
        }
        for(Player po : Bukkit.getOnlinePlayers()){
            po.showPlayer(p);
        }
    }

    private void sendPacket(Packet<?>... packet) {
        for (Player all : Bukkit.getOnlinePlayers()){
            for (Packet target : packet){
                ((CraftPlayer) all).getHandle().playerConnection.sendPacket(target);
            }
        }

    }
}
