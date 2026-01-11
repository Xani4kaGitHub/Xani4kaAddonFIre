package org.xani4kaaddonfire;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.xani4kaaddonfire.fire.FireManager;
import org.xani4kaaddonfire.region.RegionChecker;

public class FirezoneListener implements Listener {

    private final RegionChecker regionChecker;
    private final FireManager fireManager;

    public FirezoneListener(RegionChecker regionChecker, FireManager fireManager) {
        this.regionChecker = regionChecker;
        this.fireManager = fireManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        
        if (event.getFrom().getBlockX() == event.getTo().getBlockX() &&
            event.getFrom().getBlockY() == event.getTo().getBlockY() &&
            event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
            return;
        }

        boolean isInFirezone = regionChecker.isPlayerInFirezone(player);
        boolean isTrackedInFirezone = fireManager.isPlayerTrackedInFirezone(player.getUniqueId());

        if (isInFirezone && !isTrackedInFirezone) {
            fireManager.handlePlayerEnterFirezone(player);
        } else if (!isInFirezone && isTrackedInFirezone) {
            fireManager.handlePlayerLeaveFirezone(player);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        
        Player player = (Player) event.getEntity();
        
        if (fireManager.isPlayerTrackedInFirezone(player.getUniqueId()) && 
            (event.getCause() == EntityDamageEvent.DamageCause.FIRE ||
             event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
            
            if (regionChecker.isPlayerInFirezone(player)) {
                fireManager.relightPlayerDelayed(player);
            }
        }
    }
}