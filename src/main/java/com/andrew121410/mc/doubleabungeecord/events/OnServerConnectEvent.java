package com.andrew121410.mc.doubleabungeecord.events;

import com.andrew121410.mc.doubleabungeecord.DoubleANetwork;
import com.andrew121410.mc.doubleabungeecord.utils.Color;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnServerConnectEvent implements Listener {

    private DoubleANetwork plugin;

    public OnServerConnectEvent(DoubleANetwork plugin) {
        this.plugin = plugin;
        this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
    }

    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.color("&2You have joined the " + event.getTarget().getName() + " server!")));
    }
}