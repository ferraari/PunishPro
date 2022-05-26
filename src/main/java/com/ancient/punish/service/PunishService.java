/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish.service;

import com.ancient.punish.model.*;
import com.ancient.punish.registry.PunishRegistry;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class PunishService {

    private PunishRegistry punishRegistry;

    // TODO: Create another punishments types

    public void punish(String operator, String victim, String reason, String proof) {
        final Punish punish = new Punish(
          UUID.randomUUID(),
          operator,
          victim,
          Evidence.builder()
            .evidence(reason)
            .proof(proof)
            .build(),
          Type.BAN,
          Status.ALREADY_BANNED,
          Instant.now()
        );

        final Player victimPlayer = Bukkit.getPlayer(victim);

        if (victimPlayer != null) {
            victimPlayer.kickPlayer(
              "uma mensagem mirabolante aqui dizendo que o jogador foi banido permanentemente ^.^"
            );
        }

        punishRegistry.getPunishes().add(
          punish
        );


    }
    public void punishMute(String operator, String victim, String reason, String proof) {
        final Mute punish = new Mute(
                UUID.randomUUID(),
                operator,
                victim,
                Evidence.builder()
                        .evidence(reason)
                        .proof(proof)
                        .build(),
                Type.MUTE,
                Status.ALREADY_BANNED,
                Instant.now()
        );

        final Player victimPlayer = Bukkit.getPlayer(victim);;

        if (victimPlayer != null) {
            ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
            consoleSender.sendMessage("§c" + victim + " foi mutado por " + operator);

        }

        punishRegistry.getPunishes().add(
                punish
        );
    }
    // TODO: Input to database
}
