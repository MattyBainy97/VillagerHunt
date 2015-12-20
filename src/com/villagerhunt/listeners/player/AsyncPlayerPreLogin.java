package com.villagerhunt.listeners.player;

import com.villagerhunt.VillagerHunt;
import com.villagerhunt.handlers.Game;
import com.villagerhunt.listeners.VHListener;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class AsyncPlayerPreLogin extends VHListener{
    
    public AsyncPlayerPreLogin(VillagerHunt pl) {
        super(pl);
    }
    
    @EventHandler
    public void playerPreLogin(AsyncPlayerPreLoginEvent e){
        if(Game.hasStarted())
            e.disallow(Result.KICK_OTHER, ChatColor.RED + "A game is in progress!");
    }
    
}