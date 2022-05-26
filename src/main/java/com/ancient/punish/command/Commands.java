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

import com.ancient.punish.registry.ReasonRegistry;
import com.ancient.punish.service.PunishService;
import lombok.AllArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class Commands {

    private ReasonRegistry reasonRegistry;
    private PunishService punishService;

    @Command(
      name = "banir",
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

        context.sendMessage(String.format(
          "§a%s foi punido permanentemente.", victimName
        ));
    }
    @Command(
        name = "mutar",
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
        
        context.sendMessage(String.format(
                "§a%s foi mutado permanentemente.", victimName
        ));
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

        hack.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banir usuario Divulgação" ));
        bug.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banir usuario Hack" ));
        // TODO Mute Events

        context.sendMessage("§eTodos tipos de punições habilitadas; ");

        player.spigot().sendMessage(div);
        player.spigot().sendMessage(hack);
        player.spigot().sendMessage(bug);
        player.spigot().sendMessage(mute);
        player.spigot().sendMessage(flood);
        player.spigot().sendMessage(spam);
    }
}
