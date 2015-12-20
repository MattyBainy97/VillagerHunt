package com.wanted.listeners.player;

import com.wanted.Wanted;
import com.wanted.listeners.WListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDrop extends WListener{
    
    public PlayerDrop(Wanted pl){
        super(pl);
    }
    
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
    
}
