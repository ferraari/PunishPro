/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish.command;

import com.ancient.punish.registry.PunishRegistry;
import com.ancient.punish.registry.ReasonRegistry;
import com.ancient.punish.service.PunishService;
import lombok.AllArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class Commands {
    private PunishService punishService;

    @Command(
            name = "banir",
            aliases = {"ban"},
            permission = "ancientbans.commands.ban"
    )
    public void onBan(
            Context<CommandSender> context,
            @Optional String victimName,
            @Optional String evidenceReference,
            @Optional String proofReference
    ) {
        if (victimName == null) {
            context.sendMessage("§cO nome do jogador não pode ser nulo.");
            return;
        }

        final String evidence = evidenceReference == null ? "Sem motivo" : evidenceReference;
        final String proof = proofReference == null ? "Sem link" : proofReference;

        punishService.punish(
                context.getSender().getName(),
                victimName,
                evidence,
                proof
        );
        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(
                victimName,
                "§cVocê foi banido por " + evidence + "§8.\n",
                null,
                context.getSender().getName()
        );
        context.sendMessage(String.format(
                "§a%s foi punido permanentemente.", victimName
        ));
    }

    @Command(
            name = "mutar",
            aliases = {"mute"},
            permission = "ancientbans.commands.ban.reason"
    )
    public void onMute(
            Context<CommandSender> context,
            @Optional String victimName,
            @Optional String evidenceReference,
            @Optional String proofReference
    ) {
        if (victimName == null) {
            context.sendMessage("§cO nome do jogador não pode ser nulo.");
            return;
        }

        final String evidence = evidenceReference == null ? "Sem motivo" : evidenceReference;
        final String proof = proofReference == null ? "Sem link" : proofReference;

        punishService.punishMute(
                context.getSender().getName(),
                victimName,
                evidence,
                proof
        );

        Player victimPlayer = Bukkit.getPlayer(victimName);
        UUID uniqueId = victimPlayer.getUniqueId();
    }

    @Command(
            name = "punir",
            permission = "ancientbans.commands.punir"
    )
    public void onPunish(
            Context<CommandSender> context
    ) {
        Player player = (Player) context.getSender();
        TextComponent div = new TextComponent("§fDivulgação Impropria");
        TextComponent hack = new TextComponent("§fUso de Cliente Alternativo");
        TextComponent bug = new TextComponent("§fAbuso de Bugs");
        TextComponent mute = new TextComponent("§fSilenciamento Permanente");
        TextComponent flood = new TextComponent("§fFlood");
        TextComponent spam = new TextComponent("§fSpam");

        hack.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fTipo: Banimento \nDuração: Permanente \nCargo:§2 Moderador ").create()));
        bug.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fTipo: Banimento \nDuração: Permanente \nCargo:§2 Moderador ").create()));
        mute.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fTipo: Silenciamento \nDuração: 6H \nCargo:§e Ajudante ").create()));
        flood.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fTipo: Silenciamento \nDuração: 6H \nCargo:§f Ajudante ").create()));
        spam.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fTipo: Silenciamento \nDuração: 6H \nCargo:§f Ajudante ").create()));
        div.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fTipo: Silenciamento \nDuração: 6H \nCargo:§e Ajudante ").create()));

        hack.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banir usuario Divulgação"));
        bug.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banir usuario Hack"));
        // TODO Mute ClickEvents

        context.sendMessage("§eTipos de punições disponíveis:");
        player.spigot().sendMessage(div);
        player.spigot().sendMessage(hack);
        player.spigot().sendMessage(bug);
        player.spigot().sendMessage(mute);
        player.spigot().sendMessage(flood);
        player.spigot().sendMessage(spam);
    }

    @Command(
            name = "desmutar",
            permission = "ancientbans.commands.desmutar"
    )
    public void onUnmute(
            Context<CommandSender> context,
            @Optional String victimName
    ) {
        if (victimName == null) {
            context.sendMessage("§cO nome do jogador não pode ser nulo.");
            return;
        }
        punishService.unMute(context.getSender().getName(), victimName);
        context.sendMessage(String.format(
                "§a%s foi desmutado.", victimName
        ));
    }
    @Command(
            name = "desbanir",
            permission = "ancientbans.commands.desbanir"
    )
    public void onUnban(
            Context<CommandSender> context,
            @Optional String victimName
    ) {
        if (victimName == null) {
            context.sendMessage("§cO nome do jogador não pode ser nulo.");
            return;
        }
        punishService.unBan(context.getSender().getName(), victimName);
        context.sendMessage(String.format(
                "§a%s foi desbanido.", victimName
        ));
    }
}
