package com.villagerhunt.handlers;

import static com.villagerhunt.handlers.Disguises.DisguiseList;
import java.util.ArrayList;
import java.util.List;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.VillagerWatcher;
import org.bukkit.entity.Villager.Profession;

@SuppressWarnings("depreciation")
public class Disguises {
    
    public static List<MobDisguise> DisguiseList = new ArrayList<MobDisguise>();

    public static void initializeVillagers() {

        MobDisguise farmer = new MobDisguise(DisguiseType.VILLAGER);
        MobDisguise librarian = new MobDisguise(DisguiseType.VILLAGER);
        MobDisguise priest = new MobDisguise(DisguiseType.VILLAGER);
        MobDisguise blacksmith = new MobDisguise(DisguiseType.VILLAGER);
        MobDisguise butcher = new MobDisguise(DisguiseType.VILLAGER);
        VillagerWatcher farmerw = (VillagerWatcher) farmer.getWatcher();
        VillagerWatcher librarianw = (VillagerWatcher) librarian.getWatcher();
        VillagerWatcher priestw = (VillagerWatcher) priest.getWatcher();
        VillagerWatcher blacksmithw = (VillagerWatcher) blacksmith.getWatcher();
        VillagerWatcher butcherw = (VillagerWatcher) butcher.getWatcher();

        farmerw.setProfession(Profession.FARMER);
        librarianw.setProfession(Profession.LIBRARIAN);
        priestw.setProfession(Profession.PRIEST);
        blacksmithw.setProfession(Profession.BLACKSMITH);
        butcherw.setProfession(Profession.BUTCHER);
        
        DisguiseList.add(farmer);
        DisguiseList.add(librarian);
        DisguiseList.add(priest);
        DisguiseList.add(blacksmith);
        DisguiseList.add(butcher);

    }
    /*public static void setDisguise(){
    
     for(int i = 0; i < Bukkit.getOnlinePlayers().length; i++){
     //DisguiseAPI.disguiseToAll(players.get(0), farmer);
     DisguiseAPI.disguiseToAll(players.get(1), librarian);
     DisguiseAPI.disguiseToAll(players.get(2), priest);
     DisguiseAPI.disguiseToAll(players.get(3), blacksmith);
     DisguiseAPI.disguiseToAll(players.get(4), butcher);
     }
    
     }
    
     public static void disguiseAsFarmer(Player player){
     MobDisguise villager = new MobDisguise(EntityType.VILLAGER);
     VillagerWatcher villagerw = (VillagerWatcher) villager.getWatcher();
     villagerw.setProfession(Profession.FARMER);
     villager.setWatcher(villagerw);
     DisguiseAPI.disguiseToAll(player, villager);
     }
    
     public static void disguiseAsLibrarian(Player player){
     MobDisguise villager = new MobDisguise(EntityType.VILLAGER);
     VillagerWatcher villagerw = (VillagerWatcher) villager.getWatcher();
     villagerw.setProfession(Profession.LIBRARIAN);
     villager.setWatcher(villagerw);
     //DisguiseAPI.disguiseToAll(player, villager);
     ChatUtilities.broadcast(player.getName());
     }
    
     public static void disguiseAsPriest(Player player){
     MobDisguise villager = new MobDisguise(EntityType.VILLAGER);
     VillagerWatcher villagerw = (VillagerWatcher) villager.getWatcher();
     villagerw.setProfession(Profession.PRIEST);
     villager.setWatcher(villagerw);
     //DisguiseAPI.disguiseToAll(player, villager);
     }
    
     public static void disguiseAsBlacksmith(Player player){
     MobDisguise villager = new MobDisguise(EntityType.VILLAGER);
     VillagerWatcher villagerw = (VillagerWatcher) villager.getWatcher();
     villagerw.setProfession(Profession.BLACKSMITH);
     villager.setWatcher(villagerw);
     //DisguiseAPI.disguiseToAll(player, villager);
     }
    
     public static void disguiseAsButcher(Player player){
     MobDisguise villager = new MobDisguise(EntityType.VILLAGER);
     VillagerWatcher villagerw = (VillagerWatcher) villager.getWatcher();
     villagerw.setProfession(Profession.BUTCHER);
     villager.setWatcher(villagerw);
     //DisguiseAPI.disguiseToAll(player, villager);
     }*/
}