package org.xani4kaaddonfire.flags;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.xani4kaaddonfire.Xani4kaAddonFIre;

public class FlagManager {
    
    private final Xani4kaAddonFIre plugin;
    private StateFlag firezoneFlag;
    
    public FlagManager(Xani4kaAddonFIre plugin) {
        this.plugin = plugin;
    }
    
    public void registerFlags() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("firezone", false);
            registry.register(flag);
            this.firezoneFlag = flag;
            plugin.getLogger().info("Флаг 'firezone' успешно зарегистрирован!");
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("firezone");
            if (existing instanceof StateFlag) {
                this.firezoneFlag = (StateFlag) existing;
                plugin.getLogger().info("Флаг 'firezone' уже существует, используем существующий.");
            } else {
                plugin.getLogger().severe("Конфликт флагов! Флаг 'firezone' уже существует но имеет другой тип!");
            }
        }
    }
    
    public StateFlag getFirezoneFlag() {
        return firezoneFlag;
    }
}