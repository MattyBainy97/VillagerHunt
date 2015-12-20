package com.villagerhunt.listeners.player;

import com.villagerhunt.VillagerHunt;
import com.villagerhunt.handlers.Game;
import com.villagerhunt.listeners.VHListener;
import com.villagerhunt.GameState;
import static com.villagerhunt.handlers.Game.players;
import com.villagerhunt.handlers.Target;
import java.util.Random;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit extends VHListener {

    public PlayerQuit(VillagerHunt pl) {
        super(pl);
    }
    
    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent q) {
        Player p = q.getPlayer();
        q.setQuitMessage("");
        if (GameState.isState(GameState.IN_LOBBY)) {
            
            Game.players.remove(p.getUniqueId());
            Game.setCanStart(Bukkit.getOnlinePlayers().size() - 1 >= 1);
            
        }else if(GameState.isState(GameState.IN_GAME)){
            
            for(String playerName : Target.getTargetters(p)){
                Player targetter = Bukkit.getPlayer(playerName);
                Target.removeTarget(targetter);
                int rand = new Random().nextInt(players.size());
                Player playertarget = Bukkit.getPlayer(players.get(rand));
                while (playertarget.equals(targetter)) {
                    if(playertarget.isOnline()){
                        rand = new Random().nextInt(players.size());
                        playertarget = Bukkit.getPlayer(players.get(rand));
                    }
                }
                Target.setTarget(targetter, playertarget);
                IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"§4Target Disconnected\"}");
                PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                ((CraftPlayer) targetter).getHandle().playerConnection.sendPacket(title);
                String target = "{\"text\": \"§6Your new target is a: " + ChatColor.AQUA + Game.roles.get(rand) + "\"}";
                IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a(target);
                PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                ((CraftPlayer) targetter).getHandle().playerConnection.sendPacket(subtitle);
            }
            
        }
    }
}
