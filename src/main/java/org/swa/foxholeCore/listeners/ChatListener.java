package org.swa.foxholeCore.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.swa.foxholeCore.chat.ChatManager;
import org.swa.foxholeCore.chat.ChatMode;
import org.swa.foxholeCore.factions.Faction;
import org.swa.foxholeCore.factions.FactionManager;

import java.awt.*;

public class ChatListener implements Listener {
    private final ChatManager chatManager;
    private final FactionManager factionManager;

    public ChatListener(ChatManager chatManager, FactionManager factionManager) {
        this.chatManager = chatManager;
        this.factionManager = factionManager;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        String msg = PlainTextComponentSerializer.plainText().serialize(event.message());

        boolean force = msg.startsWith("!");
        if (force) {
            msg = msg.substring(1);
        }

        ChatMode mode = chatManager.getMode(player.getUniqueId());

        event.setCancelled(true);

        if (!force) {
            sendLocal(player,msg);
            return;
        }

        switch (mode) {
            case FACTION -> sendFaction(player,msg);
            case GLOBAL -> {
                sendGlobal(player,msg);

                TextChannel channel = DiscordSRV.getPlugin().getJda().getTextChannelById("1517897002619375757");

                EmbedBuilder embed = new EmbedBuilder().setColor(factionManager.getPlayerJColor(player.getUniqueId())).
                        setAuthor(player.getName() + " (" + factionManager.getFaction(player.getUniqueId()).name().toLowerCase()+ ")", null, "https://minotar.net/avatar/"+player.getName()+"/128")
                        .setDescription(msg);

                if (channel != null) {
                    channel.sendMessageEmbeds(embed.build()).queue();
                }
            }

        }
    }

    private void sendLocal(Player sender, String msg) {

        Faction faction = factionManager.getFaction(sender.getUniqueId());
        String color = factionManager.getFactionMColor(faction);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getWorld().equals(sender.getWorld()))
                continue;
            if (p.getLocation().distance(sender.getLocation()) > 100)
                continue;

            p.sendMessage("§7[L] " + color + sender.getName() +" ["+ faction.name()+ "]: §f" + msg);
        }
    }

    private void sendFaction(Player sender, String msg) {

        Faction faction = factionManager.getFaction(sender.getUniqueId());
        String color = factionManager.getFactionMColor(faction);

        if (faction == null) {
            sender.sendMessage("Вы не находитесь в фракции");
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            Faction targetFaction = factionManager.getFaction(p.getUniqueId());


            if (targetFaction == null)
                continue;
            if (faction != targetFaction)
                continue;

            p.sendMessage("§6[F] " + color + sender.getName() + ": " + msg);
        }
    }

    private void sendGlobal(Player sender, String msg) {

        Faction faction = factionManager.getFaction(sender.getUniqueId());
        String color = factionManager.getFactionMColor(faction);

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§e[G] §f"  + color + sender.getName() + " [" + faction.name() + "] : §7" + msg);
        }
    }
}
