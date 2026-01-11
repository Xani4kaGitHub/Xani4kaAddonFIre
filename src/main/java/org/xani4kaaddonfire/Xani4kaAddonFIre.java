package org.xani4kaaddonfire;

import org.bukkit.plugin.java.JavaPlugin;
import org.xani4kaaddonfire.fire.FireManager;
import org.xani4kaaddonfire.flags.FlagManager;
import org.xani4kaaddonfire.region.RegionChecker;
import org.xani4kaaddonfire.utils.MessageUtils;

public final class Xani4kaAddonFIre extends JavaPlugin {

    private FlagManager flagManager;
    private RegionChecker regionChecker;
    private FireManager fireManager;

    @Override
    public void onLoad() {
        flagManager = new FlagManager(this);
        flagManager.registerFlags();
    }

    @Override
    public void onEnable() {
        MessageUtils.printStartupMessage(this);
        
        regionChecker = new RegionChecker(flagManager.getFirezoneFlag());
        fireManager = new FireManager(this, regionChecker);
        
        fireManager.startFirezoneTask();
        
        getServer().getPluginManager().registerEvents(
            new FirezoneListener(regionChecker, fireManager), this);
        
        getLogger().info("Плагин Xani4kaAddonFire успешно активирован!");
    }

    @Override
    public void onDisable() {
        MessageUtils.printShutdownMessage(this);
    }
    
    public FlagManager getFlagManager() {
        return flagManager;
    }
    
    public RegionChecker getRegionChecker() {
        return regionChecker;
    }
    
    public FireManager getFireManager() {
        return fireManager;
    }
}
