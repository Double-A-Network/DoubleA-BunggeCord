package com.andrew121410.mc.doubleabungeecord.commands;

import com.andrew121410.mc.doubleabungeecord.DoubleANetwork;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DiscordCMD extends Command {

    private final DoubleANetwork plugin;

    public DiscordCMD(DoubleANetwork plugin) {
        super("discord");
        this.plugin = plugin;
        this.plugin.getProxy().getPluginManager().registerCommand(this.plugin, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer player)) {
            sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(ChatColor.RED).create());
            return;
        }

        ComponentBuilder componentBuilder = new ComponentBuilder("Discord: https://discord.gg/pbrueZB")
                .color(ChatColor.RED)
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("CLICK ME!!!")))
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/pbrueZB"));

        player.sendMessage(componentBuilder.create());
    }
}
