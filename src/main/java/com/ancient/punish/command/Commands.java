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
import org.bukkit.command.CommandSender;

@AllArgsConstructor
public class Commands {

    private ReasonRegistry reasonRegistry;
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
        name = "mute",
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
}
