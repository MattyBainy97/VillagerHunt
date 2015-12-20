package com.villagerhunt.listeners.player;

import com.villagerhunt.GameState;
import com.villagerhunt.VillagerHunt;
import com.villagerhunt.handlers.Target;
import com.villagerhunt.handlers.Wool;
import com.villagerhunt.listeners.VHListener;
import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove extends VHListener {

    public PlayerMove(VillagerHunt pl) {
        super(pl);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (GameState.isState(GameState.IN_GAME)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Target.hasTarget(p)) {
                    Player t = Bukkit.getPlayer(Target.getTarget(p));
                    if (!t.isDead() && t.isOnline()) {
                        if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getY() != e.getTo().getY() || e.getFrom().getZ() != e.getTo().getZ()) {
                            Location ploc = p.getLocation();
                            Location tloc = t.getLocation();
                            double dist = ploc.distance(tloc);

                            Wool wool = new Wool(p, t, dist);
                            wool.initialize();

                            p.getInventory().setItem(8, wool.getWool());
                            
                            if(dist < 6){
                                if(p.isSprinting()){
                                    CraftPlayer craftplayer = (CraftPlayer) t;
                                    PlayerConnection connection = craftplayer.getHandle().playerConnection;
                                    IChatBaseComponent warning = IChatBaseComponent.ChatSerializer.a("{\"text\": \"§4WARNING: §6A player is nearby who will kill you\"}");
                                    PacketPlayOutChat packet = new PacketPlayOutChat();

                                    try {
                                        Field field = packet.getClass().getDeclaredField("a");
                                        field.setAccessible(true);
                                        field.set(packet, warning);
                                        field.setAccessible(!field.isAccessible());

                                        Field Field2 = packet.getClass().getDeclaredField("b");
                                        Field2.setAccessible(true);
                                        Field2.set(packet, (byte) 2);
                                        Field2.setAccessible(!Field2.isAccessible());

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    connection.sendPacket(packet);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
