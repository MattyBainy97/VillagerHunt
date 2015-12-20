package com.villagerhunt.handlers;

import com.villagerhunt.GameState;
import com.villagerhunt.VillagerHunt;
import static com.villagerhunt.handlers.Game.players;
import static com.villagerhunt.handlers.Game.roles;
import com.villagerhunt.utils.LocationUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;
import me.libraryaddict.disguise.DisguiseAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Game {

    private static boolean canStart = false;
    private static boolean hasStarted = false;
    public static ArrayList<UUID> players = new ArrayList<UUID>();
    public static ArrayList<String> roles = new ArrayList<String>();
    private static int rand;

    public static void start() {
        VillagerHunt.plugin.getServer().getScheduler().scheduleSyncDelayedTask(VillagerHunt.plugin, new Runnable() {
            @Override
            public void run() {
                GameState.setState(GameState.IN_GAME);
                new Role("Farmer");
                new Role("Librarian");
                new Role("Priest");
                new Role("Blacksmith");
                new Role("Butcher");
                roles.add("Farmer");
                roles.add("Librarian");
                roles.add("Priest");
                roles.add("Blacksmith");
                roles.add("Butcher");
                Item.setMetas();
                LocationUtilities.spawnVillager(210, 245, -1935, -1906, 75);
                Disguises.initializeVillagers();
                Collections.shuffle(players);
                PointSB.showScoreboard();
                for (int i = 0; i < players.size(); i++) {
                    if (!Role.hasRole(Bukkit.getPlayer(players.get(i))) == true) {
                        Role.getRole(roles.get(i)).add(Bukkit.getPlayer(players.get(i)));
                        ArrayList<Player> allExceptPlayer = new ArrayList<Player>();
                        for(Player player : Bukkit.getOnlinePlayers()){
                            if(!player.equals(Bukkit.getPlayer(players.get(i)))){
                                allExceptPlayer.add(player);
                            }
                        }
                        DisguiseAPI.disguiseToPlayers(Bukkit.getPlayer(players.get(i)), Disguises.DisguiseList.get(i), allExceptPlayer);
                        if (i == 0) {
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(0, Item.hoe);
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(1, Item.wheat);
                            rand = new Random().nextInt(players.size());
                            Player playertarget = Bukkit.getPlayer(players.get(rand));
                            while (playertarget.getUniqueId().equals(players.get(i))) {
                                rand = new Random().nextInt(players.size());
                                playertarget = Bukkit.getPlayer(players.get(rand));
                            }
                            Target.setTarget(Bukkit.getPlayer(players.get(i)), playertarget);
                            IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§6Your role is: §4Farmer\"}");
                            PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(title);
                            String target = "{\"text\": \"§6Your target is a: " + ChatColor.AQUA + roles.get(rand) + "\"}";
                            IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                            PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(subtitle);
                            LocationUtilities.teleportToGame(Bukkit.getPlayer(players.get(i)));
                        } else if (i == 1) {
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(0, Item.book);
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(1, Item.paper);
                            rand = new Random().nextInt(players.size());
                            Player playertarget = Bukkit.getPlayer(players.get(rand));
                            while (playertarget.getName().equals(Bukkit.getPlayer(players.get(i)).getName())) {
                                rand = new Random().nextInt(players.size());
                                playertarget = Bukkit.getPlayer(players.get(rand));
                            }
                            Target.setTarget(Bukkit.getPlayer(players.get(i)), playertarget);
                            IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§6Your role is: §4Librarian\"}");
                            PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(title);
                            String target = "{\"text\": \"§6Your target is a: " + ChatColor.AQUA + roles.get(rand) + "\"}";
                            IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                            PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(subtitle);
                            LocationUtilities.teleportToGame(Bukkit.getPlayer(players.get(i)));
                        } else if (i == 2) {
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(0, Item.blaze);
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(1, Item.gold);
                            rand = new Random().nextInt(players.size());
                            Player playertarget = Bukkit.getPlayer(players.get(rand));
                            while (playertarget.getName().equals(Bukkit.getPlayer(players.get(i)).getName())) {
                                rand = new Random().nextInt(players.size());
                                playertarget = Bukkit.getPlayer(players.get(rand));
                            }
                            Target.setTarget(Bukkit.getPlayer(players.get(i)), playertarget);
                            IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§6Your role is: §4Priest\"}");
                            PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(title);
                            String target = "{\"text\": \"§6Your target is a: " + ChatColor.AQUA + roles.get(rand) + "\"}";
                            IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                            PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(subtitle);
                            LocationUtilities.teleportToGame(Bukkit.getPlayer(players.get(i)));
                        } else if (i == 3) {
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(0, Item.axe);
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(1, Item.coal);
                            rand = new Random().nextInt(players.size());
                            Player playertarget = Bukkit.getPlayer(players.get(rand));
                            while (playertarget.getName().equals(Bukkit.getPlayer(players.get(i)).getName())) {
                                rand = new Random().nextInt(players.size());
                                playertarget = Bukkit.getPlayer(players.get(rand));
                            }
                            Target.setTarget(Bukkit.getPlayer(players.get(i)), playertarget);
                            IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§6Your role is: §4Blacksmith\"}");
                            PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(title);
                            String target = "{\"text\": \"§6Your target is a: " + ChatColor.AQUA + roles.get(rand) + "\"}";
                            IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                            PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(subtitle);
                            LocationUtilities.teleportToGame(Bukkit.getPlayer(players.get(i)));
                        } else {
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(0, Item.knife);
                            Bukkit.getPlayer(players.get(i)).getInventory().setItem(1, Item.tknife);
                            rand = new Random().nextInt(players.size());
                            Player playertarget = Bukkit.getPlayer(players.get(rand));
                            while (playertarget.getName().equals(Bukkit.getPlayer(players.get(i)).getName())) {
                                rand = new Random().nextInt(players.size());
                                playertarget = Bukkit.getPlayer(players.get(rand));
                            }
                            Target.setTarget(Bukkit.getPlayer(players.get(i)), playertarget);
                            IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§6Your role is: §4Butcher\"}");
                            PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(title);
                            String target = "{\"text\": \"§6Your target is a: " + ChatColor.AQUA + roles.get(rand) + "\"}";
                            IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                            PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                            ((CraftPlayer) Bukkit.getPlayer(players.get(i))).getHandle().playerConnection.sendPacket(subtitle);
                            LocationUtilities.teleportToGame(Bukkit.getPlayer(players.get(i)));
                        }
                    }
                }
            }
        }, 5L);
        hasStarted = true;
    }

    public static void stop() {
        hasStarted = false;
    }

    public static boolean canStart() {
        return canStart;
    }

    public static boolean hasStarted() {
        return hasStarted;
    }

    public static void setCanStart(boolean b) {
        canStart = b;
    }
}
