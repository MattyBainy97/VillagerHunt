package com.villagerhunt.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.bukkit.entity.Player;

public class Target {
    
    private static HashMap<String, String> targets = new HashMap<String, String>();
    
    public static String getTarget(Player p){
        return targets.get(p.getName());
    }
    
    public static void setTarget(Player p, Player t){
        targets.put(p.getName(), t.getName());
    }
    
    public static void removeTarget(Player p){
        targets.remove(p.getName());
    }
    
    public static boolean hasTarget(Player p){
        return targets.containsKey(p.getName());
    }
    
    public static ArrayList<String> getTargetters(Player p){
        
        ArrayList<String> allTargetters = new ArrayList<String>();
        
        for(Entry<String, String> e : targets.entrySet()){
            if(e.getValue().equals(p.getName())){
                allTargetters.add(e.getKey());
            }
        }
        
        return allTargetters;
        
    }
    
}
