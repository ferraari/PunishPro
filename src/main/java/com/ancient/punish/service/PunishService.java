/*
 * Copyright (c) 2022 Ancient Productions

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ancient.punish.service;

import com.ancient.punish.model.*;
import com.ancient.punish.registry.PunishRegistry;
import lombok.AllArgsConstructor;
import org.bukkit.BanList;
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
                    "§cVocê foi banido por " + reason + "§8.\n"
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

        final Player victimPlayer = Bukkit.getPlayer(victim);
        ;

        if (victimPlayer != null) {
            ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
            consoleSender.sendMessage("§c" + victim + " foi mutado por " + operator);

        }

        punishRegistry.getPunishes().add(
                punish
        );


    }
    public void unMute( String operator, String victim) {


        final Player victimPlayer = Bukkit.getPlayer(victim);
        ;

        if (victimPlayer != null) {
            ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
            consoleSender.sendMessage("§c" + victim + " foi desmutado por " + operator);

        }


        final Punish punish = punishRegistry.getActivatedPunish(victim);

        punishRegistry.getPunishes().remove(
                punish
        );
    }
    public void unBan( String operator, String victim) {

        final Player victimPlayer = Bukkit.getPlayer(victim);
        final Player operatorPlayer = Bukkit.getPlayer(operator);

        if (victimPlayer != null) {
            ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
            consoleSender.sendMessage("§c" + victim + " foi desbanido por " + operator);
            operatorPlayer.sendMessage("§cVocê desbanhou " + victim);

        }

        if (Bukkit.getBanList(BanList.Type.NAME).isBanned(victim)) {
            Bukkit.getBanList(BanList.Type.NAME).pardon(victim);
        }


    }
}
