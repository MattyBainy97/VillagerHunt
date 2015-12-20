package com.wanted.listeners.player;

import com.wanted.Wanted;
import com.wanted.listeners.WListener;
import com.wanted.utils.ChatUtilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKick extends WListener {
    
    public PlayerKick(Wanted pl){
        
        super(pl);
        
    }
    
    @EventHandler
    public void onPlayerKick(PlayerKickEvent e){
        
        if(e.getReason().contains("-10")){
            ChatUtilities.broadcast(e.getPlayer().getName() + " was sent home to think about what they've done");
        }
        
    }
    
}
