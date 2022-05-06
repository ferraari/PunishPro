package ferrari.github.commands;


import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import com.avaje.ebeaninternal.server.persist.dmlbind.FactoryBaseProperties;
import com.nickuc.chat.api.events.internal.MessageEvent;
import ferrari.github.FBans;
import net.minecraft.server.v1_8_R3.ChatMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static ferrari.github.FBans.mutado;

public class Mute implements Listener, CommandExecutor {





    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s.hasPermission("fbans.mutar")) {
            Player p = (Player) s;
            if (args.length == 1) {
                Player target = p.getServer().getPlayer(args[0]);

                if (target != null) {

                        mutado.put(target.getName(), target.getName());
                        p.sendMessage("§cVocê mutou " + target.getName());
                        target.sendMessage("§cVocê foi mutado! Expira em: Nunca");
                        p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                        target.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);



                }
            } else {
                p.sendMessage("§cUse: /mutar <jogador>");
            }

        }
        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (mutado.contains(p.getName())) {
            e.setCancelled(true);
            p.sendMessage("§cVocê está mutado!" +  " Expira em: Nunca");
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
        }


    }



}