package com.andrew121410.mc.doubleabungeecord.listeners;

import com.andrew121410.mc.doubleabungeecord.DoubleANetwork;
import com.andrew121410.mc.doubleabungeecord.utils.Color;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnServerConnectedEvent implements Listener {

    private DoubleANetwork plugin;

    public OnServerConnectedEvent(DoubleANetwork plugin) {
        this.plugin = plugin;
        this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
    }

    @EventHandler
    public void onJoin(ServerConnectedEvent event) {
        ProxiedPlayer player = event.getPlayer();
        player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.color("&2You have joined the " + event.getServer().getInfo().getName() + " server!")));
    }
}
