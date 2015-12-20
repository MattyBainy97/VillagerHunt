package com.wanted.utils;

import org.bukkit.Bukkit;
import static org.bukkit.ChatColor.*;
import org.bukkit.entity.Player;

public class ChatUtilities {
    
    public static void broadcast(String msg){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(starter() + msg);
        }
    }
    
    public static void onePlayer(String msg, Player player){
        player.sendMessage(starter() + msg);
    }
    
    private static String starter(){
        return GRAY + "[" + DARK_AQUA + "VillagerHunt" + GRAY + "] " + GOLD;
    }
}
