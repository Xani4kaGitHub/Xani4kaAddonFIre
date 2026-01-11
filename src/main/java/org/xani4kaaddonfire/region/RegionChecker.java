package org.xani4kaaddonfire.region;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.entity.Player;

public class RegionChecker {
    
    private final StateFlag firezoneFlag;
    
    public RegionChecker(StateFlag firezoneFlag) {
        this.firezoneFlag = firezoneFlag;
    }
    
    public boolean isPlayerInFirezone(Player player) {
        if (firezoneFlag == null) {
            return false;
        }
        
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
        
        return set.testState(localPlayer, firezoneFlag);
    }
}