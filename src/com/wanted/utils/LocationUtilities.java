package com.wanted.utils;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class LocationUtilities {

    public static Location lobby = new Location(Bukkit.getWorld("VillagerHuntMap"), 196.5, 4, -1919.5);
    public static Location game = new Location(Bukkit.getWorld("VillagerHuntMap"), 215.5, 4, -1930.5);

    public static void teleportToLobby(Player p) {
        p.teleport(lobby);
    }

    public static void teleportToGame(Player p) {
        Random r = new Random();

        int x = r.nextInt(245 - 210) + 210; 
        int z = r.nextInt(-1906 - -1935) + -1935;

        Location l = new Location(Bukkit.getServer().getWorld("VillagerHuntMap"), x, 4, z);
        p.teleport(l);
    }

    public static void spawnVillager(final int minx, final int maxx, final int minz, final int maxz, int amount) {
                for(int j = 1; j <= amount; j++){
                    Random r = new Random();
                    int x1 = minx;
                    int x2 = maxx;
                    
                    int z1 = minz;
                    int z2 = maxz;

                    int x = r.nextInt(x2 - x1) + x1; 
                    int z = r.nextInt(z2 - z1) + z1;

                    Location l = new Location(Bukkit.getServer().getWorld("VillagerHuntMap"), x, 4, z);
                    if(l.getBlock().getType() == Material.AIR){
                        Bukkit.getServer().getWorld("VillagerHuntMap").spawnEntity(l, EntityType.VILLAGER);
                    }
                }

    }
}
