package com.wanted.listeners.player;

import com.wanted.Wanted;
import com.wanted.handlers.Game;
import com.wanted.listeners.WListener;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class AsyncPlayerPreLogin extends WListener{
    
    public AsyncPlayerPreLogin(Wanted pl) {
        super(pl);
    }
    
    @EventHandler
    public void playerPreLogin(AsyncPlayerPreLoginEvent e){
        if(Game.hasStarted())
            e.disallow(Result.KICK_OTHER, ChatColor.RED + "A game is in progress!");
    }
    
}