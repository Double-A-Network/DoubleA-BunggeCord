package com.andrew121410.mc.doubleabungeecord.listeners;

import com.andrew121410.mc.doubleabungeecord.DoubleANetwork;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.concurrent.TimeUnit;

public class OnProxyPingEvent implements Listener {

    private final DoubleANetwork plugin;

    public OnProxyPingEvent(DoubleANetwork plugin) {
        this.plugin = plugin;
        this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
        pingHubServer();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProxyPing(net.md_5.bungee.api.event.ProxyPingEvent event) {
        ServerPing serverPing = event.getResponse();

        if (!isHubOnline) {
            TextComponent baseComponent = new TextComponent("Double-A Network \nThe hub is currently offline.");
            baseComponent.setColor(ChatColor.RED);

            ServerPing newServerPing = new ServerPing(serverPing.getVersion(), new ServerPing.Players(0, 0, new ServerPing.PlayerInfo[0]), baseComponent, serverPing.getFaviconObject());
            event.setResponse(newServerPing);
        }
    }

    private boolean isHubOnline = false;

    private void pingHubServer() {
        this.plugin.getProxy().getScheduler().schedule(this.plugin, () -> {
            this.plugin.getProxy().getServersCopy().get("hub").ping((serverPing, throwable) -> isHubOnline = throwable == null);
        }, 0, 30, TimeUnit.SECONDS);
    }
}
