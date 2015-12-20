package com.villagerhunt.handlers;

import static com.villagerhunt.handlers.Item.axe;
import static com.villagerhunt.handlers.Item.blaze;
import static com.villagerhunt.handlers.Item.book;
import static com.villagerhunt.handlers.Item.coal;
import static com.villagerhunt.handlers.Item.gold;
import static com.villagerhunt.handlers.Item.hoe;
import static com.villagerhunt.handlers.Item.knife;
import static com.villagerhunt.handlers.Item.paper;
import static com.villagerhunt.handlers.Item.tknife;
import static com.villagerhunt.handlers.Item.wheat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
    
    //farmer decleration
    public static ItemStack hoe = new ItemStack(Material.IRON_HOE);
    private static ItemMeta hoem = (ItemMeta) hoe.getItemMeta();
    public static ItemStack wheat = new ItemStack(Material.WHEAT);
    private static ItemMeta wheatm = (ItemMeta) wheat.getItemMeta();
    
    //librarian decleration
    public static ItemStack book = new ItemStack(Material.BOOK);
    private static ItemMeta bookm = (ItemMeta) book.getItemMeta();
    public static ItemStack paper = new ItemStack(Material.PAPER);
    private static ItemMeta paperm = (ItemMeta) paper.getItemMeta();
    
    //priest decleration
    public static ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
    private static ItemMeta blazem = (ItemMeta) blaze.getItemMeta();
    public static ItemStack gold = new ItemStack(Material.GOLD_INGOT);
    private static ItemMeta goldm = (ItemMeta) gold.getItemMeta();
    
    //blacksmith decleration
    public static ItemStack axe = new ItemStack(Material.IRON_AXE);
    private static ItemMeta axem = (ItemMeta) axe.getItemMeta();
    public static ItemStack coal = new ItemStack(Material.COAL);
    private static ItemMeta coalm = (ItemMeta) coal.getItemMeta();
    
    //butcher decleration
    public static ItemStack knife = new ItemStack(Material.IRON_SWORD);
    private static ItemMeta knifem = (ItemMeta) knife.getItemMeta();
    public static ItemStack tknife = new ItemStack(Material.WOOD_SWORD);
    private static ItemMeta tknifem = (ItemMeta) tknife.getItemMeta();
            
    public static void setMetas(){
        
        //farmer edit
        hoem.setDisplayName(ChatColor.DARK_RED + "Weapon:" + ChatColor.RED + " Iron Hoe");
        hoe.setItemMeta(hoem);
        wheatm.setDisplayName(ChatColor.DARK_RED + "Perk:" + ChatColor.RED + " Freezing Wheat");
        wheat.setItemMeta(wheatm);
        
        //librarian edit
        bookm.setDisplayName(ChatColor.DARK_RED + "Weapon:" + ChatColor.RED + " Book");
        book.setItemMeta(bookm);
        paperm.setDisplayName(ChatColor.DARK_RED + "Perk:" + ChatColor.RED + " Speed Read");
        paper.setItemMeta(paperm);
        
        //priest edit
        blazem.setDisplayName(ChatColor.DARK_RED + "Weapon:" + ChatColor.RED + " Blaze Rod");
        blaze.setItemMeta(blazem);
        goldm.setDisplayName(ChatColor.DARK_RED + "Perk:" + ChatColor.RED + " Influence");
        gold.setItemMeta(goldm);
        
        //blacksmith edit
        axem.setDisplayName(ChatColor.DARK_RED + "Weapon:" + ChatColor.RED + " Iron Axe");
        axe.setItemMeta(axem);
        coalm.setDisplayName(ChatColor.DARK_RED + "Perk:" + ChatColor.RED + " Smoke Bomb");
        coal.setItemMeta(coalm);
        
        //butcher edit
        knifem.setDisplayName(ChatColor.DARK_RED + "Weapon:" + ChatColor.RED + " Butcher's Knife");
        knife.setItemMeta(knifem);
        tknifem.setDisplayName(ChatColor.DARK_RED + "Perk:" + ChatColor.RED + "Invisibility Knife");
        tknife.setItemMeta(tknifem);
        
    }
}