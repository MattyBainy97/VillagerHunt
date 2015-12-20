package com.wanted.listeners.player;

import com.wanted.Wanted;
import com.wanted.handlers.Disguises;
import com.wanted.handlers.Game;
import static com.wanted.handlers.Game.players;
import com.wanted.handlers.Item;
import com.wanted.handlers.Role;
import com.wanted.handlers.Target;
import com.wanted.listeners.WListener;
import java.util.ArrayList;
import java.util.Random;
import me.libraryaddict.disguise.DisguiseAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn extends WListener {
    
    private int rand;

    public PlayerRespawn(Wanted pl) {
        super(pl);
    }

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent e) {
        
        Wanted.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Wanted.plugin, new Runnable() {
            @Override
            public void run() {
                final Player p = e.getPlayer();
            if (Role.getRole(p).equals(Role.getRole("Farmer"))) {
                p.getInventory().setItem(0, Item.hoe);
                p.getInventory().setItem(1, Item.wheat);
                ArrayList<Player> allExceptFarmer = new ArrayList<Player>();
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(!player.equals(p)){
                        allExceptFarmer.add(player);
                    }
                }
                DisguiseAPI.disguiseToPlayers(p, Disguises.DisguiseList.get(0), allExceptFarmer);
            } else if (Role.getRole(p).equals(Role.getRole("Librarian"))) {
                p.getInventory().setItem(0, Item.book);
                p.getInventory().setItem(1, Item.paper);
                ArrayList<Player> allExceptLibrarian = new ArrayList<Player>();
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(!player.equals(p)){
                        allExceptLibrarian.add(player);
                    }
                }
                DisguiseAPI.disguiseToPlayers(p, Disguises.DisguiseList.get(1), allExceptLibrarian);
            } else if (Role.getRole(p).equals(Role.getRole("Priest"))) {
                p.getInventory().setItem(0, Item.blaze);
                p.getInventory().setItem(1, Item.gold);
                ArrayList<Player> allExceptPriest = new ArrayList<Player>();
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(!player.equals(p)){
                        allExceptPriest.add(player);
                    }
                }
                DisguiseAPI.disguiseToPlayers(p, Disguises.DisguiseList.get(2), allExceptPriest);
            } else if (Role.getRole(p).equals(Role.getRole("Blacksmith"))) {
                p.getInventory().setItem(0, Item.axe);
                p.getInventory().setItem(1, Item.coal);
                ArrayList<Player> allExceptBlacksmith = new ArrayList<Player>();
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(!player.equals(p)){
                        allExceptBlacksmith.add(player);
                    }
                }
                DisguiseAPI.disguiseToPlayers(p, Disguises.DisguiseList.get(3), allExceptBlacksmith);
            } else if (Role.getRole(p).equals(Role.getRole("Butcher"))) {
                p.getInventory().setItem(0, Item.knife);
                p.getInventory().setItem(1, Item.tknife);
                ArrayList<Player> allExceptButcher = new ArrayList<Player>();
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(!player.equals(p)){
                        allExceptButcher.add(player);
                    }
                }
                DisguiseAPI.disguiseToPlayers(p, Disguises.DisguiseList.get(4), allExceptButcher);
            }
                rand = new Random().nextInt(players.size());
                Player playertarget = Bukkit.getPlayer(players.get(rand));
                while (playertarget.getName().equals(p.getName())) {
                    rand = new Random().nextInt(players.size());
                    playertarget = Bukkit.getPlayer(players.get(rand));
                }
                Target.setTarget(p, playertarget);
                IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§4Slain, Target Lost\"}");
                PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                String target = "{\"text\": \"§6Your new target is a: " + ChatColor.AQUA + Game.roles.get(rand) + "\"}";
                IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
            }
        }, 10L);
        
        Random r = new Random();

        int x = r.nextInt(245 - 210) + 210; 
        int z = r.nextInt(-1906 - -1935) + -1935;

        Location l = new Location(Bukkit.getServer().getWorld("VillagerHuntMap"), x, 4, z);
        e.setRespawnLocation(l);
    }
}
