package com.ancient.punish.listener;


import com.ancient.punish.model.Mute;
import com.ancient.punish.model.Punish;
import com.ancient.punish.registry.PunishRegistry;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@AllArgsConstructor
public class MutedListener implements Listener {

    private PunishRegistry punishRegistry;

    @EventHandler
    public void onMuted(AsyncPlayerChatEvent e) {

        final Punish punish = punishRegistry.getActivatedPunish(e.getPlayer().getName());

        if (punish == null) {
            return;
        }
        if (punish instanceof Mute) {
            e.getPlayer().sendMessage("§cVocê está mutado!");
            e.setCancelled(true);
        }
    }
}
