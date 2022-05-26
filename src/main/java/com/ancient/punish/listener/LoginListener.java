/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish.listener;

import com.ancient.punish.model.*;
import com.ancient.punish.registry.PunishRegistry;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.time.Instant;

@AllArgsConstructor
public class LoginListener implements Listener {

    private PunishRegistry punishRegistry;

    @EventHandler(
      priority = EventPriority.LOWEST
    )
    void onLogin(
      AsyncPlayerPreLoginEvent event
    ) {
        final Punish punish = punishRegistry.getActivatedPunish(event.getName());

        if (punish == null) {
            return;
        }

        if (punish instanceof Mute || punish instanceof TemporaryMute) {
            return;
        }

        if (punish instanceof TemporaryPunish) {
            final TemporaryPunish temporaryPunish = (TemporaryPunish) punish;

            if (temporaryPunish.getTerminate().isBefore(Instant.ofEpochMilli(System.currentTimeMillis()))) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
                event.setKickMessage("uma mensagem mirabolante aqui dizendo que você foi banido temporariamente ^.^");

                return;
            }

            temporaryPunish.setStatus(Status.EXPIRED);
            // TODO: Update that reference on database

            return;
        }

        event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
        event.setKickMessage("uma mensagem mirabolante aqui dizendo que você foi banido permanentemente ^.^");
    }
}
