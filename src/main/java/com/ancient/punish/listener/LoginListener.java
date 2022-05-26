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
package com.ancient.punish.listener;

import com.ancient.punish.model.*;
import com.ancient.punish.registry.PunishRegistry;
import lombok.AllArgsConstructor;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
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

        event.disallow(
                AsyncPlayerPreLoginEvent.Result.KICK_BANNED,
                "§cVocê foi banido por " + punish.getEvidence().getEvidence() + "§8.\n"
        );
    }


}

