package com.villagerhunt.listeners.player;

import com.villagerhunt.VillagerHunt;
import com.villagerhunt.handlers.Game;
import com.villagerhunt.listeners.VHListener;
import com.villagerhunt.utils.ChatUtilities;
import com.villagerhunt.utils.LocationUtilities;
import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoin extends VHListener {

    public PlayerJoin(VillagerHunt pl) {
        super(pl);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Player p = e.getPlayer();
        Game.players.add(p.getUniqueId());
        p.setHealth(20.0);
        p.setExp(0);
        ChatUtilities.broadcast(e.getPlayer().getDisplayName() + " has joined!");
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        Game.setCanStart(Bukkit.getOnlinePlayers().size() >= 2);
        LocationUtilities.teleportToLobby(p);

        CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent header = ChatSerializer.a("{\"text\": \"   §3Villager Hunt   \"}");
        IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"   §4Development Test   \"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        connection.sendPacket(packet);
    }
}
