package com.wanted.listeners.player;

import com.wanted.Wanted;
import com.wanted.handlers.Game;
import static com.wanted.handlers.Game.players;
import com.wanted.handlers.Item;
import com.wanted.handlers.PointSB;
import com.wanted.handlers.Target;
import com.wanted.listeners.WListener;
import com.wanted.utils.ChatUtilities;
import com.wanted.utils.LocationUtilities;
import java.util.Random;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageByEntity extends WListener {

    private int rand;

    public PlayerDamageByEntity(Wanted pl) {
        super(pl);
    }

    @EventHandler
    public void DamageByPlayer(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player d = (Player) e.getDamager();

            if (d.getItemInHand().equals(Item.hoe) || d.getItemInHand().equals(Item.book) || d.getItemInHand().equals(Item.blaze) || d.getItemInHand().equals(Item.axe) || d.getItemInHand().equals(Item.knife)) {
                p.setHealth(0.0);
                //ChatUtilities.onePlayer("You killed " + p.getName() + "! They were a " + Role.getRole(p).getName() + ".", d);
                //ChatUtilities.onePlayer("You were slain by " + d.getName() + "!", p);
                d.getItemInHand().setDurability((short) 0);
                if (Target.getTarget(d).equals(p.getName())) {
                    Target.removeTarget(d);
                    PointSB.addPoints(d, 2);
                    rand = new Random().nextInt(players.size());
                    Player playertarget = Bukkit.getPlayer(players.get(rand));
                    while (playertarget.getName().equals(d.getName()) && !playertarget.isOnline()) {
                        rand = new Random().nextInt(players.size());
                        playertarget = Bukkit.getPlayer(players.get(rand));
                    }
                    Target.setTarget(d, playertarget);
                    IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§2Target Eliminated\"}");
                    PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                    ((CraftPlayer) d).getHandle().playerConnection.sendPacket(title);
                    String target = "{\"text\": \"§6Your new target is a: " + ChatColor.AQUA + Game.roles.get(rand) + "\"}";
                    IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                    PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                    ((CraftPlayer) d).getHandle().playerConnection.sendPacket(subtitle);
                    ChatUtilities.onePlayer("Target Kill (" + ChatColor.GREEN + p.getName() + ChatColor.GOLD + "): " + ChatColor.GREEN + "+2", d);
                } else {
                    Target.removeTarget(d);
                    PointSB.removePoints(d, 2);
                    rand = new Random().nextInt(players.size());
                    Player playertarget = Bukkit.getPlayer(players.get(rand));
                    while (playertarget.getName().equals(d.getName()) && !playertarget.isOnline()) {
                        rand = new Random().nextInt(players.size());
                        playertarget = Bukkit.getPlayer(players.get(rand));
                    }
                    Target.setTarget(d, playertarget);
                    IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§4Wrong Player, Target Lost\"}");
                    PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                    ((CraftPlayer) d).getHandle().playerConnection.sendPacket(title);
                    String target = "{\"text\": \"§6Your new target is a: " + ChatColor.AQUA + Game.roles.get(rand) + "\"}";
                    IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                    PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                    ((CraftPlayer) d).getHandle().playerConnection.sendPacket(subtitle);
                    ChatUtilities.onePlayer("False Target Kill (" + ChatColor.DARK_RED + p.getName() + ChatColor.GOLD + "): " + ChatColor.DARK_RED + "-2", d);
                }
            } else {
                e.setCancelled(true);
            }
        } else if (e.getEntity() instanceof Villager && e.getDamager() instanceof Player) {
            final Villager v = (Villager) e.getEntity();
            Player d = (Player) e.getDamager();

            if (d.getItemInHand().equals(Item.hoe) || d.getItemInHand().equals(Item.book) || d.getItemInHand().equals(Item.blaze) || d.getItemInHand().equals(Item.axe) || d.getItemInHand().equals(Item.knife)) {
                d.getItemInHand().setDurability((short) 0);
                final Location l = v.getLocation();
                v.remove();
                LocationUtilities.spawnVillager(210, 245, -1935, -1906, 1);
                v.getWorld().playEffect(l, Effect.SMOKE, 20);

                Target.removeTarget(d);
                PointSB.removePoints(d, 1);
                rand = new Random().nextInt(players.size());
                Player playertarget = Bukkit.getPlayer(players.get(rand));
                while (playertarget.getName().equals(d.getName()) && !playertarget.isOnline()) {
                    rand = new Random().nextInt(players.size());
                    playertarget = Bukkit.getPlayer(players.get(rand));
                }
                Target.setTarget(d, playertarget);
                IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"§4NPC Kill, Target Lost\"}");
                PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle, 10, 40, 10);
                ((CraftPlayer) d).getHandle().playerConnection.sendPacket(title);
                String target = "{\"text\": \"§6Your new target is a: " + ChatColor.AQUA + Game.roles.get(rand) + "\"}";
                IChatBaseComponent chatSubTitle = ChatSerializer.a(target);
                PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                ((CraftPlayer) d).getHandle().playerConnection.sendPacket(subtitle);
                ChatUtilities.onePlayer("NPC Kill: " + ChatColor.DARK_RED + "-1", d);
            } else {
                e.setCancelled(true);
            }
        }
    }
}
