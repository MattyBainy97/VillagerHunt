package com.villagerhunt.listeners.player;

import com.villagerhunt.VillagerHunt;
import com.villagerhunt.listeners.VHListener;
import java.util.List;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteract extends VHListener {

    public PlayerInteract(VillagerHunt pl) {
        super(pl);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Action action = e.getAction();
        Player p = e.getPlayer();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(false);
            if (p.getItemInHand().getType() == Material.COAL) {
                p.getInventory().removeItem(p.getItemInHand());
                List<Entity> nearby = p.getNearbyEntities(4, 4, 4);
                for (Entity en : nearby) {
                    if (en instanceof Player) {
                        Player blinded = (Player) en;
                        blinded.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 2, false));
                        blinded.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 2, false));
                    }
                }
            } else if (p.getItemInHand().getType() == Material.PAPER) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2, false));
                p.getInventory().removeItem(p.getItemInHand());
            } else if (p.getItemInHand().getType() == Material.WOOD_SWORD) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 2, false));
                p.getInventory().removeItem(p.getItemInHand());
            } else if (p.getItemInHand().getType() == Material.GOLD_INGOT) {
                List<Entity> le = p.getNearbyEntities(2D, 2D, 2D);
                for (Entity en : le) {
                    if (en.getType() == EntityType.VILLAGER) {
                        Villager v = (Villager) en;
                        v.setProfession(Villager.Profession.PRIEST);
                    }
                }
                p.getInventory().removeItem(p.getItemInHand());
            } else if (p.getItemInHand().getType() == Material.WHEAT) {
                List<Entity> nearby = p.getNearbyEntities(4, 4, 4);
                for (Entity en : nearby) {
                    if (en instanceof Player) {
                        Player frozen = (Player) en;
                        frozen.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 10, false));
                        frozen.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 128, false));
                        IChatBaseComponent chatTitle2 = ChatSerializer.a("{\"text\": \"§bFROZEN\"}");
                        PacketPlayOutTitle title2 = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle2, 10, 40, 10);
                        ((CraftPlayer) frozen).getHandle().playerConnection.sendPacket(title2);
                        IChatBaseComponent chatSubTitle = ChatSerializer.a("{\"text\": \"§3You will be unfrozen in 5 seconds\"}");
                        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle, 10, 40, 10);
                        ((CraftPlayer) frozen).getHandle().playerConnection.sendPacket(subtitle);
                    }
                }
                p.getInventory().removeItem(p.getItemInHand());
            } else {
                if (!p.isOp()) {
                    e.setCancelled(true);
                }
            }
        } else {
            if (!p.isOp()) {
                e.setCancelled(true);
            }
        }
    }
}
