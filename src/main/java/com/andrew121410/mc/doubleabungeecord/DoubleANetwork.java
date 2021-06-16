package com.andrew121410.mc.doubleabungeecord;

import com.andrew121410.mc.doubleabungeecord.commands.DiscordCMD;
import com.andrew121410.mc.doubleabungeecord.commands.HubCMD;
import com.andrew121410.mc.doubleabungeecord.listeners.OnServerConnectedEvent;
import net.md_5.bungee.api.plugin.Plugin;

public class DoubleANetwork extends Plugin {

    private static DoubleANetwork plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    public void registerListeners() {
        new OnServerConnectedEvent(this);
    }

    public void registerCommands() {
        new HubCMD(this);
        new DiscordCMD(this);
    }

    public static DoubleANetwork getInstance() {
        return plugin;
    }
}
