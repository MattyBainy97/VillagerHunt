package com.villagerhunt.listeners.player;

import com.villagerhunt.VillagerHunt;
import com.villagerhunt.handlers.Target;
import com.villagerhunt.listeners.VHListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath extends VHListener{
    
    public PlayerDeath(VillagerHunt pl) {
        super(pl);
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        e.setDeathMessage("");
        e.getDrops().clear();
        
        Target.removeTarget((Player) e.getEntity());
    }
    
}
