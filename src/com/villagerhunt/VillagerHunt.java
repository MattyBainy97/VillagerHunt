/*
 * Project: Wanted Plugin
 * Author: MattyBainy
 * Date Created: 13/11/14
 * Last Modified: 20/12/15
 */
package com.villagerhunt;

import com.villagerhunt.handlers.Game;
import com.villagerhunt.handlers.PointSB;
import com.villagerhunt.listeners.inventory.ClickSlot;
import com.villagerhunt.listeners.player.AsyncPlayerPreLogin;
import com.villagerhunt.listeners.player.PlayerDamageByEntity;
import com.villagerhunt.listeners.player.PlayerDeath;
import com.villagerhunt.listeners.player.PlayerDrop;
import com.villagerhunt.listeners.player.PlayerInteract;
import com.villagerhunt.listeners.player.PlayerInteractEntity;
import com.villagerhunt.listeners.player.PlayerJoin;
import com.villagerhunt.listeners.player.PlayerKick;
import com.villagerhunt.listeners.player.PlayerMove;
import com.villagerhunt.listeners.player.PlayerQuit;
import com.villagerhunt.listeners.player.PlayerRespawn;
import com.villagerhunt.threads.StartCountdown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagerHunt extends JavaPlugin{
    
    public static Plugin plugin;
    
    @Override
    public void onEnable(){
        plugin = this;
        GameState.setState(GameState.IN_LOBBY);
        Game.setCanStart(false);
        new Thread(new StartCountdown()).start();
        registerListeners();
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer(ChatColor.GREEN + "Reloading. Rejoin.");
        }
        for (World w : Bukkit.getServer().getWorlds()) {
            w.setTime(0);
            w.setStorm(false);
            w.setWeatherDuration(9999999);
                for (Entity e : w.getEntities()) {
                    e.remove();
                }
        }
        PointSB.initializeScoreboard();
    }
    
    @Override
    public void onDisable(){
        plugin = null;
    }
    
    public void registerListeners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerQuit(this), this);
        pm.registerEvents(new AsyncPlayerPreLogin(this), this);
        pm.registerEvents(new PlayerDamageByEntity(this), this);
        pm.registerEvents(new PlayerDeath(this), this);
        pm.registerEvents(new PlayerRespawn(this), this);
        pm.registerEvents(new PlayerInteract(this), this);
        pm.registerEvents(new PlayerMove(this), this);
        pm.registerEvents(new PlayerInteractEntity(this), this);
        pm.registerEvents(new PlayerDrop(this), this);
        pm.registerEvents(new ClickSlot(this), this);
        pm.registerEvents(new PlayerKick(this), this);
    }
}
