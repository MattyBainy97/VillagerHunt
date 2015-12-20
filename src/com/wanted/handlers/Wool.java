package com.wanted.handlers;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Wool {
    
    private Player player;
    private Player target;
    private double distance;
    private HashMap<String, ItemStack> assignedWool = new HashMap<String, ItemStack>();
    
    public Wool(Player player, Player target, double distance){
        
        this.player = player;
        this.target = target;
        this.distance = distance;
        
    }
    
    public void initialize(){
        if(distance >= 30){
            ItemStack wool = new ItemStack(Material.WOOL, 1);
            ItemMeta mwool = (ItemMeta) wool.getItemMeta();
            mwool.setDisplayName(ChatColor.GOLD + "Target: " + ChatColor.AQUA + target.getName() + " (" + Role.getRole(target).getName() + ")");
            wool.setItemMeta(mwool);
            assignedWool.put(player.getName(), wool);
        }else if(distance < 30 && distance >= 16){
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 5);
            ItemMeta mwool = (ItemMeta) wool.getItemMeta();
            mwool.setDisplayName(ChatColor.GOLD + "Target: " + ChatColor.AQUA + target.getName() + " (" + Role.getRole(target).getName() + ")");
            wool.setItemMeta(mwool);
            assignedWool.put(player.getName(), wool);
        }else if(distance < 16 && distance >= 6){
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 4);
            ItemMeta mwool = (ItemMeta) wool.getItemMeta();
            mwool.setDisplayName(ChatColor.GOLD + "Target: " + ChatColor.AQUA + target.getName() + " (" + Role.getRole(target).getName() + ")");
            wool.setItemMeta(mwool);
            assignedWool.put(player.getName(), wool);
        }else if(distance < 6){
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 14);
            ItemMeta mwool = (ItemMeta) wool.getItemMeta();
            mwool.setDisplayName(ChatColor.GOLD + "Target: " + ChatColor.AQUA + target.getName() + " (" + Role.getRole(target).getName() + ")");
            wool.setItemMeta(mwool);
            assignedWool.put(player.getName(), wool);
        }
    }
    
    public ItemStack getWool(){
        return assignedWool.get(player.getName());
    }
    
}
