package com.andrew121410.mc.doubleabungeecord;

import com.andrew121410.mc.doubleabungeecord.commands.HubCMD;
import com.andrew121410.mc.doubleabungeecord.events.OnServerConnectEvent;
import net.md_5.bungee.api.plugin.Plugin;

public class DoubleANetwork extends Plugin {

    private static DoubleANetwork plugin;

    @Override
    public void onEnable() {
        plugin = this;
        regEvents();
        regCommands();
    }

    @Override
    public void onDisable() {
    }

    public void regEvents() {
        new OnServerConnectEvent(this);
    }

    public void regCommands() {
        new HubCMD(this);
    }
}
