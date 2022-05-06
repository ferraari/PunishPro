package ferrari.github.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static ferrari.github.FBans.mutado;

public class UnMute implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s.hasPermission("fbans.desmutar")) {
            Player p = (Player) s;
            if (args.length == 1) {
                Player target = p.getServer().getPlayer(args[0]);

                if (target != null) {

                    mutado.remove(target.getName(), target.getName());
                    p.sendMessage("§cVocê desmutou " + target.getName());
                    target.sendMessage("§cVocê foi desmutado!");
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                    target.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);



                }
            } else {
                p.sendMessage("§cUse: /desmutar <jogador>");
            }

        }
        return false;
    }

}
