package de.LPmitFelix.StarWars.Core.APIs;

import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_9_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;


public class setTabListVormat {

    private String header;
    private String footer;
    private Player player;

    public setTabListVormat(String Header, String Footer, Player p) {
        this.header = Header;
        this.footer = Footer;
        this.player = p;
        sendTabTitle();
    }

    private void sendTabTitle() {
        if (header == null) {
            header = "";
        }

        if (footer == null) {
            footer = "";
        }

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

        IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \"" + header + "\"}");

        IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);

        try {

            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            connection.sendPacket(headerPacket);

        }
    }
}
