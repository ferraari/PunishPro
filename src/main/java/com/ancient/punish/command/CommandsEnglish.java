package com.ancient.punish.command;


import com.ancient.punish.registry.PunishRegistry;
import com.ancient.punish.service.PunishService;
import lombok.AllArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;

@AllArgsConstructor
public class CommandsEnglish {

    private PunishService punishService;

    @Command(
            name = "ban",
            permission = "ancientbans.commands.ban"
    )
    public void onBan(
            Context<CommandSender> context,
            @Optional String victimName,
            @Optional String evidenceReference,
            @Optional String proofReference
    ) {
        if (victimName == null) {
            context.sendMessage("§cThe player's name cannot be null.");
            return;
        }

        final String evidence = evidenceReference == null ? "No reason" : evidenceReference;
        final String proof = proofReference == null ? "No proof" : proofReference;

        punishService.punish(
                context.getSender().getName(),
                victimName,
                evidence,
                proof
        );

        context.sendMessage(String.format(
                "§a%s has been permantly banned", victimName
        ));
    }
}
