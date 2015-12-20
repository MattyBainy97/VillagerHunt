package com.villagerhunt.listeners.inventory;

import com.villagerhunt.VillagerHunt;
import com.villagerhunt.listeners.VHListener;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickSlot extends VHListener {

    public ClickSlot(VillagerHunt pl) {
        super(pl);
    }

    @EventHandler
    public void onClickSlot(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if(p.getGameMode() == GameMode.ADVENTURE){
            if (e.getSlot() == 0) {
                e.setResult(Event.Result.DENY);
                e.setCancelled(true);
            } else if (e.getSlot() == 1) {
                e.setResult(Event.Result.DENY);
                e.setCancelled(true);
            } else if (e.getSlot() == 8) {
                e.setResult(Event.Result.DENY);
                e.setCancelled(true);
            }
        }
    }
}
