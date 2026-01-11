package org.xani4kaaddonfire.fire;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.xani4kaaddonfire.Xani4kaAddonFIre;
import org.xani4kaaddonfire.region.RegionChecker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FireManager {
    
    private final Xani4kaAddonFIre plugin;
    private final RegionChecker regionChecker;
    private final Set<UUID> playersInFirezone = new HashSet<>();
    
    public FireManager(Xani4kaAddonFIre plugin, RegionChecker regionChecker) {
        this.plugin = plugin;
        this.regionChecker = regionChecker;
    }
    
    public void startFirezoneTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    UUID playerId = player.getUniqueId();
                    boolean isInFirezone = regionChecker.isPlayerInFirezone(player);
                    
                    if (isInFirezone && playersInFirezone.contains(playerId)) {
                        if (player.getFireTicks() <= 0) {
                            setPlayerOnFire(player);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }
    
    public void handlePlayerEnterFirezone(Player player) {
        UUID playerId = player.getUniqueId();
        if (!playersInFirezone.contains(playerId)) {
            playersInFirezone.add(playerId);
            setPlayerOnFire(player);
        }
    }
    
    public void handlePlayerLeaveFirezone(Player player) {
        UUID playerId = player.getUniqueId();
        if (playersInFirezone.contains(playerId)) {
            playersInFirezone.remove(playerId);
            extinguishPlayer(player);
        }
    }
    
    public boolean isPlayerTrackedInFirezone(UUID playerId) {
        return playersInFirezone.contains(playerId);
    }
    
    public void relightPlayerDelayed(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.isOnline() && regionChecker.isPlayerInFirezone(player)) {
                    setPlayerOnFire(player);
                }
            }
        }.runTaskLater(plugin, 1L);
    }
    
    private void setPlayerOnFire(Player player) {
        player.setFireTicks(Integer.MAX_VALUE);
    }
    
    private void extinguishPlayer(Player player) {
        player.setFireTicks(0);
    }
}