package org.swa.foxholeCore.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.swa.foxholeCore.chat.ChatManager;
import org.swa.foxholeCore.chat.ChatMode;

public class FactionChatCommand implements CommandExecutor {

    private final ChatManager chatManager;

    public FactionChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only players can use this command");
            return true;
        }

        ChatMode current = chatManager.getMode(player.getUniqueId());

        ChatMode next;

        switch (current) {

            case FACTION -> next = ChatMode.GLOBAL;
            case GLOBAL -> next = ChatMode.FACTION;

            default -> next = ChatMode.GLOBAL;
        }

        chatManager.setMode(player.getUniqueId(), next);
        return true;
    }
}
