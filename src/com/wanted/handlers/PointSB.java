package com.wanted.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PointSB {
    
    private static Objective points = null;
    private static Score s = null;
    private static Scoreboard playerPoints = null;
    
    public static void initializeScoreboard(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        playerPoints = manager.getNewScoreboard();
        points = playerPoints.registerNewObjective("test", "dummy");
        points.setDisplaySlot(DisplaySlot.SIDEBAR);
        points.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "Villager Points");
    }
    
    public static void showScoreboard(){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.setScoreboard(playerPoints);
            s = points.getScore(ChatColor.GOLD + p.getName());
            s.setScore(0);
        }
    }
    
    public static void addPoints(Player p, int i){
        s = points.getScore(ChatColor.GOLD + p.getName());
        s.setScore(s.getScore() + i);
    }
    
    public static void removePoints(Player p, int i){
        s = points.getScore(ChatColor.GOLD + p.getName());
        s.setScore(s.getScore() - i);
        
        if(s.getScore() == -10){
            
            p.kickPlayer(ChatColor.DARK_RED + "KICKED: " + ChatColor.WHITE + "-10, Really!?\nI think you need to brush up on the rules...");
        
        }
    }
}
