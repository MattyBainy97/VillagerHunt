package com.villagerhunt.listeners.player;

import com.villagerhunt.VillagerHunt;
import com.villagerhunt.listeners.VHListener;
import com.villagerhunt.utils.ChatUtilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKick extends VHListener {
    
    public PlayerKick(VillagerHunt pl){
        
        super(pl);
        
    }
    
    @EventHandler
    public void onPlayerKick(PlayerKickEvent e){
        
        if(e.getReason().contains("-10")){
            ChatUtilities.broadcast(e.getPlayer().getName() + " was sent home to think about what they've done");
        }
        
    }
    
}
